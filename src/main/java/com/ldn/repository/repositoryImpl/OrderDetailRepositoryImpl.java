/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.OrderDetail;
import com.ldn.repository.OrderDetailRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author three
 */
@Repository
@Transactional
public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    @Autowired
    private LocalSessionFactoryBean lsfb;
    
    @Override
    public boolean addOrderDetail(OrderDetail ordDtl) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            session.save(ordDtl);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
