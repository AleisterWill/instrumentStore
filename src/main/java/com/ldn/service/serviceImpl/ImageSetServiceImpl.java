/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.repository.ImageSetRepository;
import com.ldn.service.ImageSetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author three
 */
@Service
public class ImageSetServiceImpl implements ImageSetService {
    
    @Autowired
    private ImageSetRepository imgSetRepository;
    
    @Override
    public List<Object> search(String keyword, int page) {
        return this.imgSetRepository.search(keyword, page);
    }
    
}
