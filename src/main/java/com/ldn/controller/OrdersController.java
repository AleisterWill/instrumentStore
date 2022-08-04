/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.Order1;
import com.ldn.pojo.User;
import com.ldn.service.Order1Service;
import com.ldn.service.OrderDetailService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author three
 */
@Controller
public class OrdersController {
    
    @Autowired
    private Order1Service ordService;
    
    @Autowired
    private OrderDetailService ordDtlService;
    
    @GetMapping("/accounts/orderHistory") 
    public String ordHistoryView(Model model,
            @RequestParam(required = false) Map<String, String> params,
            HttpSession httpSession) {
        
        User u = (User) httpSession.getAttribute("currentUser");
        int ordId = 0, page = 1;
        try {
            ordId = Integer.parseInt(params.getOrDefault("ordId", "0"));
        } catch(Exception ex) {
            
        }
        
        try {
            page = Integer.parseInt(params.getOrDefault("page", "1"));
        } catch (Exception ex) {
            
        }
        
        List<Object[]> listOrders = ordService.search(u.getId(), ordId, page);
        
        model.addAttribute("countList", listOrders.get(0));
        model.addAttribute("ListOrders", listOrders.subList(1, listOrders.size()));
        
        model.addAttribute("ordId", ordId);
        model.addAttribute("page", page);
        
        return "ordHistory";
    }
    
    @GetMapping("/accounts/orderHistory/{ordId}")
    public String ordDtlView(@PathVariable(value = "ordId") int ordId,
            Model model,
            HttpSession httpSession) {
        
        User u = (User) httpSession.getAttribute("currentUser");
        List<Object> ordDtlFields = ordDtlService.search(u.getId(), ordId);
        model.addAttribute("OrderDetail", ordDtlFields);
        return "ordDetail";
    }
}