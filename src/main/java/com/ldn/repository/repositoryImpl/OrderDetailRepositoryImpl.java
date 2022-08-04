/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.Order1;
import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.Product;
import com.ldn.pojo.User;
import com.ldn.repository.OrderDetailRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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

    @Override
    public List<Object> search(User userId, int ordId) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        
        Root rootP = cq.from(Product.class);
        Root rootO = cq.from(Order1.class);
        Root rootOD = cq.from(OrderDetail.class);
        Root rootU = cq.from(User.class);
        
        //Result
        List<Object> result = new ArrayList<>();
        
        //Building Predicates
        List<Predicate> preds = new ArrayList<>();
        
            //Join Tables
        preds.add(cb.equal(rootO.get("userId"), rootU));
        preds.add(cb.equal(rootO, rootOD.get("orderId")));
        preds.add(cb.equal(rootOD.get("productId"), rootP));
        preds.add(cb.equal(rootO.get("id"), ordId));
        preds.add(cb.equal(rootU, userId));
        
        cq.where(preds.toArray(new Predicate[preds.size()]));
        
        //SELECT
        cq.multiselect(
                rootP.get("id"),
                rootP.get("name"),
                rootP.get("price"),
                rootOD.get("quantity"),
                cb.prod(rootP.get("price"), rootOD.get("quantity"))
        );
        
        //Hibernate
        Query q = session.createQuery(cq);
        result = q.getResultList();
        
        return result;
    }
}
