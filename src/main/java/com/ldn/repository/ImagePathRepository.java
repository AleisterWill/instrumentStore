/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.repository;

import com.ldn.pojo.ImagePath;
import com.ldn.pojo.ImageSet;
import java.util.List;

/**
 *
 * @author three
 */
public interface ImagePathRepository {
    public List<ImagePath> getByImgSetId(ImageSet imgSetId);
    public boolean addImg(ImagePath img);
    public boolean deleteImg(ImagePath img);
    public boolean deleteBySetId(ImageSet imgSetId);
}
