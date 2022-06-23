/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.repository;

import com.ldn.pojo.Product;
import java.util.List;

/**
 *
 * @author three
 */
public interface ProductRepository {
    public List<Product> getListProduct(String keyword, Long minPrice, Long maxPrice, int page);
}
