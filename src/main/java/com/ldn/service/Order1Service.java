/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.service;

import com.ldn.pojo.Order1;

/**
 *
 * @author three
 */
public interface Order1Service {
    public Order1 getOrderById(int id);
    public boolean addOrder(Order1 ord);
}
