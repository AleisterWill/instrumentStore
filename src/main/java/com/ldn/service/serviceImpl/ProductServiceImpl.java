/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.Product;
import com.ldn.repository.ProductRepository;
import com.ldn.service.ProductService;
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
    public List<Object[]> getCountAndListProduct(String keyword, Long minPrice, Long maxPrice, int page) {
        return this.productRepository.getCountAndListProduct(keyword, minPrice, maxPrice, page);
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
    public List<Product> getByCateIdAndSubCateId(int cateId, int subCateId) {
        return this.productRepository.getByCateIdAndSubCateId(cateId, subCateId);
    }
    
}
