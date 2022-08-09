/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.ImagePath;
import com.ldn.service.ImagePathService;
import com.ldn.service.ImageSetService;
import com.ldn.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author three
 */
@Controller
public class AdminController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ImageSetService imgSetService;
    
    @Autowired
    private ImagePathService imgPathService;
    
    @GetMapping("/admin")
    public String adminIndexView() {
        
        return "adminIndex";
    }
    
    @GetMapping("/admin/product")
    public String adminProductView(@RequestParam(required = false) Map<String, String> params,
            Model model) {
        
        String keyword = params.getOrDefault("keyword", "");
        int page = 1;
        try {
            page = Integer.parseInt(params.getOrDefault("page", "1"));
        } catch (Exception ex) {
            
        }
        
        List<Object[]> listProd = productService.getCountAndListProduct(keyword, null, null, "", page);
        model.addAttribute("countList", listProd.get(0)[0]);
        model.addAttribute("ListProducts", listProd.get(1));
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "adminProduct";
    }
    
    @GetMapping("/admin/product/imageSet")
    public String adminImgSetView(Model model,
            @RequestParam(required = false) Map<String, String> params) {
        
        String keyword = params.getOrDefault("keyword", "");
        int page = 1;
        try {
            page = Integer.parseInt(params.getOrDefault("page", "1"));
        } catch (Exception ex) {
            
        }
        List<Object> Objs = this.imgSetService.search(keyword, page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("countList", Objs.get(0));
        model.addAttribute("ListImgSet", Objs.subList(1, Objs.size()));
        
        
        return "adminImgSet";
    }
    
    @GetMapping("/admin/product/imageSet/{imgSetId}")
    public String adminImgPathView(Model model,
            @PathVariable(name = "imgSetId") String imgSetId) {
        
        int tmp = 0;
        try {
            tmp = Integer.parseInt(imgSetId);
        } catch(Exception ex) {
            
        }
        List<ImagePath> Obj = this.imgPathService.getByImgSetId(tmp);
        model.addAttribute("ListImgPath", Obj);
        model.addAttribute("imgSetId", tmp);
        
        return "adminImgPath";
    }
}
