/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.service.ProductService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author three
 */
@Controller
public class HomeController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model,
            @RequestParam(required = false) Map<String, String> params) {
        
        model.addAttribute("BestSellers", this.productService.getListBestSellers());
        return "index";
    }
}
