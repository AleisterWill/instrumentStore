/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.Order1;
import com.ldn.repository.Order1Repository;
import com.ldn.repository.UserRepository;
import com.ldn.service.Order1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author three
 */
@Service
public class Order1ServiceImpl implements Order1Service {
    
    @Autowired
    private Order1Repository order1Repository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Order1 getOrderById(int id) {
        return this.order1Repository.getOrderById(id);
    }

    @Override
    public boolean addOrder(Order1 ord) {
        ord.setUserId(userRepository.getUserById(ord.getUid()));
        return this.order1Repository.addOrder(ord);
    }
    
}
