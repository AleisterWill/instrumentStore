/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.repository;

import java.util.List;

/**
 *
 * @author three
 */
public interface ImageSetRepository {
    public List<Object> search(String keyword, int page);
}
