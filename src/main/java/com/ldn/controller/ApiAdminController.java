/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.ImagePath;
import com.ldn.service.ImagePathService;
import com.ldn.service.ImageSetService;
import com.ldn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author three
 */
@RestController
public class ApiAdminController {
    
    @Autowired
    private ImagePathService imgPathService;
    
    @Autowired
    private ImageSetService imgSetService;
    
    @Autowired
    private ProductService productService;
    
    @DeleteMapping("/api/imgPath/{id}")
    public ResponseEntity deleteImgPath(@PathVariable(name = "id") Integer imgPathId) {
        return new ResponseEntity(this.imgPathService.deleteImg(imgPathId), HttpStatus.OK);
    }
    
    @DeleteMapping("/api/imgSet/{id}")
    public ResponseEntity deleteImgSet(@PathVariable(name = "id") Integer imgSetId) {
        return new ResponseEntity(this.imgSetService.deleteImgSet(imgSetId), HttpStatus.OK);
    }
    
    @DeleteMapping("/api/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable(name = "id") Integer productId) {
        return new ResponseEntity(this.productService.deleteProduct(productId), HttpStatus.OK);
    }
}
