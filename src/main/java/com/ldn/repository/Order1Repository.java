/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.repository;

import com.ldn.pojo.Order1;
import com.ldn.pojo.User;
import java.util.List;

/**
 *
 * @author three
 */
public interface Order1Repository {
    public Order1 getOrderById(int id);
    public boolean addOrder(Order1 ord);
    public List<Object[]> search(User userId, int ordId, int page);
}
