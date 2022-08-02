/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.utils;

import com.ldn.pojo.Cart;
import java.util.Map;

/**
 *
 * @author three
 */
public class Utils {

    public static int countCart(Map<Integer, Cart> cart) {
        int q = 0;
        if (cart != null) {
            for (Cart c : cart.values()) {
                q += c.getQuantity();
            }
        }
        return q;
    }
    
    public static long cartSubTotal(Map<Integer, Cart> cart) {
        long subTotal = 0;
        if (cart != null) {
            for (Cart c : cart.values()) {
                subTotal += c.getQuantity()*c.getProductPrice();
            }
        }
        return subTotal;
    }
}
