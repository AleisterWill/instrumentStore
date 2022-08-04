/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.Order1;
import com.ldn.pojo.User;
import com.ldn.repository.Order1Repository;
import com.ldn.repository.UserRepository;
import com.ldn.service.Order1Service;
import java.util.List;
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

    @Override
    public List<Object[]> search(int uId, int ordId, int page) {
        User u = new User();
        u.setId(uId);
        return this.order1Repository.search(u, ordId, page);
    }
    
}
