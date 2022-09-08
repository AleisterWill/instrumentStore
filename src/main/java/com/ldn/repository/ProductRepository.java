/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.repository;

import com.ldn.pojo.ImageSet;
import com.ldn.pojo.Product;
import java.util.List;

/**
 *
 * @author three
 */
public interface ProductRepository {
    public List<Object[]> getCountAndListProduct(String keyword, Long minPrice, Long maxPrice, String sort, int page);
    public Product getProductById(int id);
    public List<Product> getListRandomRelateProducts(Product p, int maxResults);
    public List<Product> getListBestSellers();
    public List<Object[]> getByCateIdAndSubCateIdWithCount(int cateId, int subCateId, String sort, int page);
    
    public boolean addProduct(Product p);
    public boolean deleteProduct(Product p);
    public boolean updateProduct(Product p);
    
    
    ///private use only
    public boolean undefineImgSet(ImageSet imgSetId);  //// Update all products with same imgSetId to imgSetId = 999999999
}
