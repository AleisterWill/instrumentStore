/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.ldn.pojo.ImagePath;
import com.ldn.pojo.ImageSet;
import com.ldn.repository.ImagePathRepository;
import com.ldn.service.ImagePathService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author three
 */
@Service
public class ImagePathServiceImpl implements ImagePathService {
    
    @Autowired
    private ImagePathRepository imgPathRepository;

    @Override
    public List<ImagePath> getByImgSetId(int imgSetId) {
        ImageSet imgSet = new ImageSet();
        imgSet.setId(imgSetId);
        return this.imgPathRepository.getByImgSetId(imgSet);
    }
    
}
