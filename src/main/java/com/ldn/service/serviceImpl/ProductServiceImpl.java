/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.Brand;
import com.ldn.pojo.ImageSet;
import com.ldn.pojo.Product;
import com.ldn.pojo.SubCategory;
import com.ldn.repository.ProductRepository;
import com.ldn.service.ProductService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author three
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Object[]> getCountAndListProduct(String keyword, Long minPrice, Long maxPrice, String sort, int page) {
        return this.productRepository.getCountAndListProduct(keyword, minPrice, maxPrice, sort, page);
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }

    @Override
    public List<Product> getListRandomRelateProducts(Product p, int maxResults) {
        return this.productRepository.getListRandomRelateProducts(p, maxResults);
    }

    @Override
    public List<Product> getListBestSellers() {
        return this.productRepository.getListBestSellers();
    }

    @Override
    public List<Object[]> getByCateIdAndSubCateIdWithCount(int cateId, int subCateId, String sort, int page) {
        return this.productRepository.getByCateIdAndSubCateIdWithCount(cateId, subCateId, sort, page);
    }

    @Override
    public boolean addProduct(Product p) {
        try {
            Brand brand = null;
            try {
                brand = new Brand(Integer.parseInt(p.getBrandid()));
            } catch (Exception ex) {
                
            }
            
            ImageSet imgSet = null;
            try {
                imgSet = new ImageSet(Integer.parseInt(p.getImgsetid()));
            } catch (Exception ex) {
                
            }
            
            SubCategory subCat = null;
            try {
                subCat = new SubCategory(Integer.parseInt(p.getSubcatid()));
            } catch (Exception ex) {
                
            }
            p.setBrandId(brand);
            p.setImageSetId(imgSet);
            p.setSubCategoryId(subCat);
            Date d = new Date();
            p.setCreatedDate(d);
            return this.productRepository.addProduct(p);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Integer p) {
        Product prd = new Product(p);
        return this.productRepository.deleteProduct(prd);
    }

    @Override
    public boolean updateProduct(Product p) {
        try {
            Brand brand = null;
            try {
                brand = new Brand(Integer.parseInt(p.getBrandid()));
            } catch (Exception ex) {
                
            }
            
            ImageSet imgSet = null;
            try {
                imgSet = new ImageSet(Integer.parseInt(p.getImgsetid()));
            } catch (Exception ex) {
                
            }
            
            SubCategory subCat = null;
            try {
                subCat = new SubCategory(Integer.parseInt(p.getSubcatid()));
            } catch (Exception ex) {
                
            }

            p.setBrandId(brand);
            p.setImageSetId(imgSet);
            p.setSubCategoryId(subCat);
            Date d = new Date();
            p.setCreatedDate(d);
            return this.productRepository.updateProduct(p);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    
}
