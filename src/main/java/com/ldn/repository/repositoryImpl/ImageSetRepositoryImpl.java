/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.ImageSet;
import com.ldn.repository.ImageSetRepository;
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
public class ImageSetRepositoryImpl implements ImageSetRepository {

    @Autowired
    private LocalSessionFactoryBean lsfb;

    @Override
    public List<Object> search(String keyword, int page) {
        List<Object> result = new ArrayList<>();

        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);

        //FROM
        Root rootImgS = cq.from(ImageSet.class);

        //WHERE
        List<Predicate> preds = new ArrayList<>();
        if (!keyword.isEmpty()) {
            preds.add(cb.like(rootImgS.get("id").as(String.class), keyword));
            preds.add(cb.like(rootImgS.get("description"), String.format("%%%s%%", keyword)));
            cq.where(cb.or(preds.toArray(new Predicate[preds.size()])));
        }

        //return COUNT LIST
        cq.multiselect(
                cb.count(rootImgS.get("id"))
        );
        Query q = session.createQuery(cq);

        result.add(q.getSingleResult());

        //Pagination
        int maxResults = 9;
        q.setMaxResults(maxResults);
        q.setFirstResult((page - 1) * maxResults);

        //return ListImgSet
        cq.multiselect(
                rootImgS.get("id").as(String.class),
                rootImgS.get("description")
        );
        q = session.createQuery(cq);

        result.addAll(q.getResultList());

        return result;
    }

}
