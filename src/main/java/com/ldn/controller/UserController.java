/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.User;
import com.ldn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author three
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userDetailsService;
    
    @GetMapping("/accounts")
    public String accountsView(Model model) {
        
        model.addAttribute("newUser", new User());
        return "accounts";
    }
    
    @PostMapping("/accounts/signup")
    public String signup(Model model,
            @ModelAttribute(value = "newUser") User newUser) {
        
        if (!newUser.getConfirmPW().equals(newUser.getPassword())) {
            model.addAttribute("error", "Mat khau khong khop");
        } else if (this.userDetailsService.addUser(newUser)) {
            return "redirect:/accounts";
        }
        model.addAttribute("error", "Da co loi xay ra");
        return "accounts";
    }
}
