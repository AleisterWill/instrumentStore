/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.User;
import com.ldn.repository.UserRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root root = cq.from(User.class);

        cq.select(root);
        cq.where(cb.equal(root.get("email"), email));

        Query q = session.createQuery(cq);

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

    @Override
    public boolean updateUser(User user) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<User> cu = cb.createCriteriaUpdate(User.class);
        Root root = cu.from(User.class);

        cu.where(cb.equal(root.get("id"), user.getId()));

        if (user.getFirstName() != null) {
            cu.set(root.get("firstName"), user.getFirstName());
        }
        if (user.getLastName() != null) {
            cu.set(root.get("lastName"), user.getLastName());
        }
        if (user.getPhone() != null) {
            cu.set(root.get("phone"), user.getPhone());
        }
        if (user.getAvatar() != null) {
            cu.set(root.get("avatar"), user.getAvatar());
        }
        if (user.getPassword() != null) {
            cu.set(root.get("password"), user.getPassword());
        }
        
        Query q = session.createQuery(cu);
        q.executeUpdate();
        return true;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            return session.get(User.class, id);
        } catch (Exception e) {
            
        }
        return null;
    }

}
