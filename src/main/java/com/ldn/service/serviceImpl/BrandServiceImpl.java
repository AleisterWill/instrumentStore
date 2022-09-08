/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.Brand;
import com.ldn.repository.BrandRepository;
import com.ldn.service.BrandService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author three
 */
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandRepository brandRepository;
    
    @Override
    public List<Brand> getListBrand() {
        return this.brandRepository.getListBrand();
    }
    
}
