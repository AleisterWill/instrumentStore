/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.service;

import com.ldn.pojo.ImagePath;
import java.util.List;

/**
 *
 * @author three
 */
public interface ImagePathService {
    public List<ImagePath> getByImgSetId(int imgSetId);
    public boolean addImg(ImagePath img);
    public boolean deleteImg(int imgPathId);
    public boolean deleteBySetId(Integer imgSetId);
}
