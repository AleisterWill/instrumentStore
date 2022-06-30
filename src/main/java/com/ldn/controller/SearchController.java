/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.Product;
import com.ldn.service.ProductService;
import java.util.List;
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
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public String SearchView(Model model,
            @RequestParam(required = false) Map<String, String> params) {

        String keyword = params.getOrDefault("kw", "");
        Long minPrice = null, maxPrice = null;
        try {
            minPrice = Long.parseLong(params.getOrDefault("minPrice", null));
        } catch (NumberFormatException ex) {
        } // Do nothing
        try {
            maxPrice = Long.parseLong(params.getOrDefault("maxPrice", null));
        } catch (NumberFormatException ex) { 
        } // Do nothing
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        
        model.addAttribute("kw", keyword);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("page", page);
        
        List<Object[]> Objs = this.productService.getCountAndListProduct(keyword, minPrice, maxPrice, page);
        
        model.addAttribute("countProducts", Objs.get(0)[0]);
        model.addAttribute("ListProducts", Objs.get(1));

        return "searchView";
    }
}
