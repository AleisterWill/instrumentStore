/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ldn.service;

import com.ldn.pojo.ImageSet;
import java.util.List;

/**
 *
 * @author three
 */
public interface ImageSetService {
    public List<ImageSet> getListImgSet();
    public List<Object> search(String keyword, int page);
    public boolean addImgSet(ImageSet imgSet);
    public boolean deleteImgSet(Integer imgSetId);
}
