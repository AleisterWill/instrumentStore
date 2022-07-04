/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.Cart;
import com.ldn.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author three
 */
@RestController
public class ApiCartController {
    @PostMapping("/api/cart")
    public int addToCart(@RequestBody Cart params, HttpSession session) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        if (cart == null)
            cart = new HashMap<>();
        int productId = params.getProductId();
        if (cart.containsKey(productId)) {
            Cart c = cart.get(productId);
            c.setQuantity(c.getQuantity() + 1);
        } else {
            cart.put(productId, params);
        }
        
        session.setAttribute("cart", cart);
        return Utils.countCart(cart);
    }
}
