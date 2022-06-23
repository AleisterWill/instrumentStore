/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.Product;
import com.ldn.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class ProductRepositoryImpl implements ProductRepository {
    
    @Autowired
    private LocalSessionFactoryBean lsfb;

    @Override
    public List<Product> getListProduct(String keyword, Long minPrice, Long maxPrice, int page) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root root = cq.from(Product.class);
        
        List<Predicate> predicates = new ArrayList<>();
        if (!keyword.isBlank() || keyword != null)
            predicates.add(cb.like(root.get("name").as(String.class), String.format("%%%s%%", keyword.trim())));
        if (minPrice != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("price").as(Long.class), minPrice));
        if (maxPrice != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("price").as(Long.class), maxPrice));
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        
        Query q = session.createQuery(cq);
        
        int maxResults = 9;
        q.setMaxResults(maxResults);
        q.setFirstResult((page - 1) * maxResults);
        
        List<Product> result = q.getResultList();

        return result;
    }
    
}
