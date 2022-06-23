/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author three
 */
@Controller
@ControllerAdvice
public class CommonAttributesController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("ListCategory", this.categoryService.getListCategory());
    }
}