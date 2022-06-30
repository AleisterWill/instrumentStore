/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.service;

import com.ldn.pojo.Category;
import java.util.List;

/**
 *
 * @author three
 */
public interface CategoryService {
    public List<Category> getListCategory();
    public Category getCategoryById(int id);
}
