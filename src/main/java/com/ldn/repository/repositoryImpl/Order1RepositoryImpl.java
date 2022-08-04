/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.Order1;
import com.ldn.pojo.User;
import com.ldn.repository.Order1Repository;
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
public class Order1RepositoryImpl implements Order1Repository {

    @Autowired
    private LocalSessionFactoryBean lsfb;

    @Override
    public Order1 getOrderById(int id) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            return session.get(Order1.class, id);
        } catch (HibernateException e) {

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

    @Override
    public List<Object[]> search(User userId, int ordId, int page) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root rootOrd = cq.from(Order1.class);
        
        List<Object[]> result = new ArrayList<>();
        
        //Building Predicates
        List<Predicate> preds = new ArrayList<>();
        preds.add(cb.equal(rootOrd.get("userId"), userId));
        if (ordId != 0) {
            preds.add(cb.equal(rootOrd.get("id"), ordId));
        }

        //Count Records
        cq.multiselect(cb.count(rootOrd.get("id")));
        cq.where(preds.toArray(new Predicate[preds.size()]));
        
        Query q = session.createQuery(cq);
        result.addAll(q.getResultList());

        //Get Record
        cq.multiselect(rootOrd);

        //Pagination
        int maxResults = 9;
        q.setMaxResults(maxResults);
        q.setFirstResult((page - 1) * maxResults);
        q = session.createQuery(cq);
        result.addAll(q.getResultList());

        return result;
    }

}
