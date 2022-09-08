/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ldn.pojo.ImagePath;
import com.ldn.pojo.ImageSet;
import com.ldn.repository.ImagePathRepository;
import com.ldn.service.ImagePathService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<ImagePath> getByImgSetId(int imgSetId) {
        ImageSet imgSet = new ImageSet();
        imgSet.setId(imgSetId);
        return this.imgPathRepository.getByImgSetId(imgSet);
    }

    @Override
    public boolean addImg(ImagePath img) {
        try {
            Map r = this.cloudinary.uploader().upload(img.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            
            img.setPath((String) r.get("secure_url"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this.imgPathRepository.addImg(img);
    }

    @Override
    public boolean deleteImg(int imgPathId) {
        ImagePath imgPath = new ImagePath(imgPathId);
        return this.imgPathRepository.deleteImg(imgPath);
    }

    @Override
    public boolean deleteBySetId(Integer imgsetId) {
        return this.imgPathRepository.deleteBySetId(new ImageSet(imgsetId));
    }
    
    
}