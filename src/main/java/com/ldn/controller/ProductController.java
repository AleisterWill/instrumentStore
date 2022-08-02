/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.Product;
import com.ldn.service.CommentService;
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
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/product/{productId}")
    public String productDetailsView(Model model,
            @PathVariable(value = "productId") int productId) {
        
        Product product = this.productService.getProductById(productId);
        model.addAttribute("product", product);
        
        int maxResults = 9;
        model.addAttribute("RelateProducts", this.productService.getListRandomRelateProducts(product, maxResults));
        
        model.addAttribute("ListComments", this.commentService.getListCommentsByProductId(productId));
        return "productDetails";
    }
    
    @GetMapping("/cate{categoryId}")
    public String productsBySubcateView(Model model,
            @PathVariable(value = "categoryId") int categoryId,
            @RequestParam(required = false) Map<String, String> params) {
        
        String s = params.getOrDefault("subCate", "0");
        String sort = params.getOrDefault("sort", "nameASC");
        int subCateId = 0;
        try {
            subCateId = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            
        }
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        
        model.addAttribute("cateId", categoryId);
        model.addAttribute("subCateId", subCateId);
        model.addAttribute("sort", sort);
        model.addAttribute("page", page);
        
        List<Object[]> objs = this.productService.getByCateIdAndSubCateIdWithCount(categoryId, subCateId, sort, page);
        model.addAttribute("ListProducts", objs.get(1));
        model.addAttribute("countProducts", objs.get(0)[0]);
        return "productsList";
    }

}
