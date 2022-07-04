/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.User;
import com.ldn.repository.UserRepository;
import java.util.List;
import javax.persistence.Query;
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
public class UserRepositoryImpl implements UserRepository {
    
    @Autowired
    private LocalSessionFactoryBean lsfb;

    @Override
    public List<User> getUsers(String email) {
        Session session = this.lsfb.getObject().getCurrentSession();
        Query q = session.createNamedQuery("User.findByEmail");
        q.setParameter("email", email);
        
        List<User> result = q.getResultList();
        return result;
    }

    @Override
    public boolean addUser(User user) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            session.save(user);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
}
