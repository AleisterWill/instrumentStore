/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.Category;
import com.ldn.repository.CategoryRepository;
import java.util.List;
import javax.persistence.Query;
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
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private LocalSessionFactoryBean lsfb;

    @Override
    public List<Category> getListCategory() {
        Session session = this.lsfb.getObject().getCurrentSession();
        Query q = session.createNamedQuery("Category.findAll");
        List<Category> result = q.getResultList();
        return result;
    }

    @Override
    public Category getCategoryById(int id) {
        Session session = this.lsfb.getObject().getCurrentSession();
        Query q = session.createNamedQuery("Category.findById");
        return (Category) q.getSingleResult();
    }
    
    
}
