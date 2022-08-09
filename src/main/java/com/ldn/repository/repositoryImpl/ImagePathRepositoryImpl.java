/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.ImagePath;
import com.ldn.pojo.ImageSet;
import com.ldn.repository.ImagePathRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class ImagePathRepositoryImpl implements ImagePathRepository {

    @Autowired
    private LocalSessionFactoryBean lsfb;
    
    @Override
    public List<ImagePath> getByImgSetId(ImageSet imgSetId) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ImagePath> cq = cb.createQuery(ImagePath.class);
        
        //FROM
        Root rootImgP = cq.from(ImagePath.class);
        
        //WHERE
        cq.where(cb.equal(rootImgP.get("imageSetId"), imgSetId));
        
        //SELECT
        cq.select(rootImgP);
        
        Query q = session.createQuery(cq);
        
        List<ImagePath> result = q.getResultList();
        
        return result;
    }
}
