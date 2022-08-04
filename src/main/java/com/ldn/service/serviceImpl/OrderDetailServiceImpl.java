/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.User;
import com.ldn.repository.OrderDetailRepository;
import com.ldn.service.OrderDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author three
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository ordDtlRepository;
    
    @Override
    public boolean addOrderDetail(OrderDetail ordDtl) {
        return this.ordDtlRepository.addOrderDetail(ordDtl);
    }

    @Override
    public List<Object> search(int userId, int ordId) {
        User u = new User();
        u.setId(userId);
        return this.ordDtlRepository.search(u, ordId);
    }
    
}
