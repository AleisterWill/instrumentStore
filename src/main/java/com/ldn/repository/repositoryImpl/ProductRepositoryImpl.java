/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.repository.repositoryImpl;

import com.ldn.pojo.Category;
import com.ldn.pojo.ImageSet;
import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.Product;
import com.ldn.pojo.SubCategory;
import com.ldn.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Order;
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
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private LocalSessionFactoryBean lsfb;

    @Override
    public List<Object[]> getCountAndListProduct(String keyword, Long minPrice, Long maxPrice, String sort, int page) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (!keyword.isEmpty()) {
            predicates.add(cb.like(root.get("name").as(String.class), String.format("%%%s%%", keyword.trim())));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price").as(Long.class), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("price").as(Long.class), maxPrice));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        List<Object[]> result = new ArrayList<>();

        Query q;

        //////////////////// Return countList
        cq.multiselect(cb.count(root.get("id")));
        q = session.createQuery(cq);
        List<Long> countList = q.getResultList();
        Long[] arrCount = new Long[countList.size()];
        arrCount = countList.toArray(arrCount);
        result.add(arrCount);

        //////////////////// Pagination
        int maxResults = 9;
        cq.multiselect(root);
        if (sort.equals("nameASC")) {
            cq.orderBy((cb.asc(root.get("name"))));

        } else if (sort.equals("nameDESC")) {
            cq.orderBy((cb.desc(root.get("name"))));
        } else if (sort.equals("priceASC")) {
            cq.orderBy(cb.asc(root.get("price")));
        } else if (sort.equals("priceDESC")) {
            cq.orderBy(cb.desc(root.get("price")));
        } else {
            cq.orderBy(cb.asc(root.get("id")));
        }

        q = session.createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult((page - 1) * maxResults);

        //////////////////// Return List
        List<Product> products = q.getResultList();
        Product[] arrProd = new Product[products.size()];
        arrProd = products.toArray(arrProd);
        result.add(arrProd);

        return result;
    }

    @Override
    public Product getProductById(int id) {
        Session session = this.lsfb.getObject().getCurrentSession();
        return session.get(Product.class, id);
    }

    @Override
    public List<Product> getListRandomRelateProducts(Product p, int maxResults) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root root = cq.from(Product.class);

        cq.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("subCategoryId"), p.getSubCategoryId()));
        predicates.add(cb.notEqual(root.get("id").as(Integer.class), p.getId()));

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        Query q = session.createQuery(cq);
        q.setMaxResults(maxResults);

        List<Product> result = q.getResultList();
        Collections.shuffle(result);

        List<Product> finalResult;
        if (result.size() < maxResults) {
            finalResult = result.subList(0, result.size());
        } else {
            finalResult = result.subList(0, maxResults);
        }

        return finalResult;
    }

    @Override
    public List<Product> getListBestSellers() {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> cq = cb.createQuery(OrderDetail.class);
        Root root = cq.from(OrderDetail.class);

        cq.select(root.get("productId"));
        cq.groupBy(root.get("productId"));
        cq.orderBy(cb.desc(cb.sum(root.get("quantity"))));

        Query q = session.createQuery(cq);
        List<Product> result = q.getResultList();

        return result;
    }

    @Override
    public List<Object[]> getByCateIdAndSubCateIdWithCount(int cateId, int subCateId, String sort, int page) {
        Session session = this.lsfb.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root rootP = cq.from(Product.class);
        Root rootSC = cq.from(SubCategory.class);
        Root rootC = cq.from(Category.class);

        cq.multiselect(
                rootP
        );

        List<Predicate> pWhere = new ArrayList<>();
        pWhere.add(cb.equal(rootP.get("subCategoryId"), rootSC.get("id")));
        pWhere.add(cb.equal(rootSC.get("categoryId"), rootC.get("id")));
        pWhere.add(cb.equal(rootC.get("id"), cateId));
        if (subCateId != 0) {
            pWhere.add(cb.equal(rootSC.get("id"), subCateId));
        }

        cq.where(cb.and(pWhere.toArray(new Predicate[pWhere.size()])));

        List<Object[]> result = new ArrayList<>();

        Query q;

        //////////////////// Return countList
        cq.multiselect(cb.count(rootP.get("id")));
        q = session.createQuery(cq);
        List<Long> countList = q.getResultList();
        Long[] arrCount = new Long[countList.size()];
        arrCount = countList.toArray(arrCount);
        result.add(arrCount);

        //////////////////// Pagination
        int maxResults = 9;
        cq.multiselect(rootP);
        if (sort.equals("nameASC")) {
            cq.orderBy((cb.asc(rootP.get("name"))));

        } else if (sort.equals("nameDESC")) {
            cq.orderBy((cb.desc(rootP.get("name"))));
        } else if (sort.equals("priceASC")) {
            cq.orderBy(cb.asc(rootP.get("price")));
        } else if (sort.equals("priceDESC")) {
            cq.orderBy(cb.desc(rootP.get("price")));
        }

        q = session.createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult((page - 1) * maxResults);

        //////////////////// Return List
        List<Product> products = q.getResultList();
        Product[] arrProd = new Product[products.size()];
        arrProd = products.toArray(arrProd);
        result.add(arrProd);

        return result;
    }

    @Override
    public boolean addProduct(Product p) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            session.save(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Product p) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            session.remove(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product p) {
        Session session = this.lsfb.getObject().getCurrentSession();
        try {
            session.update(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean undefineImgSet(ImageSet imgSetId) {
        try {
            Session session = this.lsfb.getObject().getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Product> cu = cb.createCriteriaUpdate(Product.class);

            Root rootP = cu.from(Product.class);
            cu.where(cb.equal(rootP.get("imageSetId"), imgSetId));
            cu.set(rootP.get("imageSetId"), new ImageSet(999999999));

            Query q = session.createQuery(cu);
            q.executeUpdate();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
