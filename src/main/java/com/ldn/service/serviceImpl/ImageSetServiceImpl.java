/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.ImageSet;
import com.ldn.repository.ImagePathRepository;
import com.ldn.repository.ImageSetRepository;
import com.ldn.repository.ProductRepository;
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
    
    @Autowired
    private ImagePathRepository imgPathRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Object> search(String keyword, int page) {
        return this.imgSetRepository.search(keyword, page);
    }

    @Override
    public boolean addImgSet(ImageSet imageSet) {
        return this.imgSetRepository.addImgSet(imageSet);
    }

    @Override
    public boolean deleteImgSet(Integer imgSetId) {
        ImageSet imgSet = new ImageSet(imgSetId);
        return this.imgSetRepository.deleteImgSet(imgSet);
    }

    @Override
    public List<ImageSet> getListImgSet() {
        return this.imgSetRepository.getListImgSet();
    }
    
    
    
}
