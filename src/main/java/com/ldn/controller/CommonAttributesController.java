/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.Cart;
import com.ldn.pojo.User;
import com.ldn.service.CategoryService;
import com.ldn.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
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
    public void commonAttr(Model model, HttpSession session) {
        model.addAttribute("ListCategory", this.categoryService.getListCategory());
        model.addAttribute("cartCounter", Utils.countCart((Map<Integer, Cart>) session.getAttribute("cart")));
        model.addAttribute("currentUser", (User) session.getAttribute("currentUser"));
    }
}
