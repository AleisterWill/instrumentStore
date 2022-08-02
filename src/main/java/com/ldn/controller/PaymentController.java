/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ldn.pojo.Cart;
import com.ldn.pojo.Order1;
import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.User;
import com.ldn.service.Order1Service;
import com.ldn.service.OrderDetailService;
import com.ldn.service.ProductService;
import com.ldn.utils.Utils;
import com.vnpay.common.Config;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author three
 */
@Controller
public class PaymentController {

    @Autowired
    private Order1Service order1Service;

    @Autowired
    private OrderDetailService ordDtlService;

    @Autowired
    private ProductService productService;

    @GetMapping("/accounts/myCart/checkout/result")
    public String payResult(HttpServletRequest request,
            HttpSession httpSession,
            Model model) throws UnsupportedEncodingException, ParseException {

        //Begin process return from VNPAY
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }

        String signValue = Config.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            // If OrderId doesn't exist in DB
            if (this.order1Service.getOrderById(Integer.parseInt(fields.get("vnp_TxnRef").toString())) == null) {
                model.addAttribute("resp", fields);
                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {

                    //Tao Order
                    User u = (User) httpSession.getAttribute("currentUser");
                    Order1 ord = new Order1();
                    ord.setId(Integer.parseInt(fields.get("vnp_TxnRef").toString()));
                    ord.setUid(u.getId());
                    ord.setTotalPrice(Long.parseLong(fields.get("vnp_Amount").toString()) / 100);
                    ord.setStatus("Paid");
                    ord.setCreatedDate(new SimpleDateFormat("yyyyMMddHHmmss").parse(fields.get("vnp_PayDate").toString()));
                    //Them Order
                    if (this.order1Service.addOrder(ord)) {
                        System.out.println(" ============ Order Added ============");
                    }

                    Map<Integer, Cart> cart = (Map<Integer, Cart>) httpSession.getAttribute("cart");
                    //Tao Order Detail
                    OrderDetail od = new OrderDetail();
                    od.setOrderId(ord);
                    for (Cart item : cart.values()) {
                        od.setProductId(this.productService.getProductById(item.getProductId()));
                        od.setQuantity(item.getQuantity());

                        //Them OrderDetail moi item
                        this.ordDtlService.addOrderDetail(od);
                        System.out.println(" ============ Order Detail Added ============");
                    }
                    
                    //Clear cart
                    httpSession.removeAttribute("cart");
                }
            } else {
                model.addAttribute("err", "Transaction ID existed in database");
            }
        } else {
            model.addAttribute("err", "Checksum Error");
        }
        return "vnpayreturn";
    }

    @PostMapping("/accounts/myCart/checkout")
    public void payUrl(HttpSession httpSession,
            HttpServletRequest req,
             HttpServletResponse resp) throws ServletException, IOException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = "Thanh Toan";
        String orderType = "140000";
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<Integer, Cart> cart = (Map<Integer, Cart>) httpSession.getAttribute("cart");
        long amount = Utils.cartSubTotal(cart) * 100;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        com.google.gson.JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(job));

        resp.sendRedirect(paymentUrl);
    }
}
