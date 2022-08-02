/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.Order1;
import com.ldn.repository.Order1Repository;
import javax.persistence.criteria.CriteriaBuilder;
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
public class Order1RepositoryImpl implements Order1Repository{
    
    @Autowired
    private LocalSessionFactoryBean lsfb;
            
    @Override
    public Order1 getOrderById(int id) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            return session.get(Order1.class, id);
        } catch(HibernateException e) {
            
        }
        return null;
    }

    @Override
    public boolean addOrder(Order1 ord) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            session.save(ord);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
