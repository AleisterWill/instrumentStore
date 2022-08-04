/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.service;

import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.User;
import java.util.List;

/**
 *
 * @author three
 */
public interface OrderDetailService {
    public boolean addOrderDetail(OrderDetail ordDtl);
    public List<Object> search(int userId, int ordId);
}
