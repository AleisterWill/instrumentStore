/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.service;

import com.ldn.pojo.Product;
import java.util.List;

/**
 *
 * @author three
 */
public interface ProductService {
    public List<Object[]> getCountAndListProduct(String keyword, Long minPrice, Long maxPrice, String sort, int page);
    public Product getProductById(int id);
    public List<Product> getListRandomRelateProducts(Product p, int maxResults);
    public List<Product> getListBestSellers();
    public List<Object[]> getByCateIdAndSubCateIdWithCount(int cateId, int subCateId, String sort, int page);
    
    public boolean addProduct(Product p);
    public boolean deleteProduct(Integer p);
    public boolean updateProduct(Product p);
}
