/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.Cart;
import com.ldn.pojo.User;
import com.ldn.service.UserService;
import com.ldn.utils.Utils;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author three
 */
@Controller
public class UserController {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder pwEncoder;

    @GetMapping("/accounts")
    public String accountsView(Model model,
            @RequestParam(required = false) Map<String, String> params,
            HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            model.addAttribute("signupSuccess", params.getOrDefault("signupSuccess", ""));
            model.addAttribute("newUser", new User());
            return "accounts";
        }
        return "redirect:/";
    }

    @PostMapping("/accounts/signup")
    public String signup(Model model,
            @ModelAttribute(value = "newUser") @Valid User newUser,
            BindingResult br) {

        if (!br.hasErrors()) {
            if (!newUser.getConfirmPW().equals(newUser.getPassword())) {
                model.addAttribute("errPW", "Mat khau khong khop");
            } else if (!this.userDetailsService.getUsers(newUser.getEmail()).isEmpty()) {
                model.addAttribute("errExisted", "Email da su dung");
            } else if (this.userDetailsService.addUser(newUser)) {
                model.addAttribute("signupSuccess", "success");
                return "redirect:/accounts";
            } else {
                model.addAttribute("error", "Da co loi xay ra");
            }
        }

        return "accounts";
    }

    @GetMapping("/accounts/myCart")
    public String myCartView(Model model, HttpSession httpSession) {

        Map<Integer, Cart> myCart = (Map<Integer, Cart>) httpSession.getAttribute("cart");
        if (myCart != null) {
            model.addAttribute("myCart", myCart.values());
        } else {
            model.addAttribute("myCart", null);
        }
        model.addAttribute("subTotal", Utils.cartSubTotal(myCart));

        return "myCart";
    }

    @GetMapping("/accounts/setting")
    public String accSettingView(Model model, HttpSession httpSession) {

        User u = (User) httpSession.getAttribute("currentUser");
        model.addAttribute("user", u);
        return "setting";
    }

    @PostMapping("/accounts/setting/changeProfile")
    public String changeProfile(Model model,
            @ModelAttribute(value = "user") User u,
            HttpSession httpSession) {

        User currentUser = (User) httpSession.getAttribute("currentUser");
        u.setId(currentUser.getId());
        if (this.userDetailsService.updateUser(u)) {
            model.addAttribute("updateStatus", "success");
            User updated = this.userDetailsService.getUsers(currentUser.getEmail()).get(0);
            httpSession.setAttribute("currentUser", updated);
            return "redirect:/accounts/setting";
        } else {
            model.addAttribute("err", "Da co loi xay ra");
        }
        return "setting";
    }

    @PostMapping("/accounts/setting/changePW")
    public String changePW(Model model,
            HttpSession httpSession,
            @ModelAttribute(value = "user") User u) {

        User currentUser = (User) httpSession.getAttribute("currentUser");
        if (!u.getConfirmPW().isEmpty()) {
            if (!pwEncoder.matches(u.getConfirmPW(), currentUser.getPassword())) {
                model.addAttribute("err", "confirmPW");
            } else if (u.getConfirmNewPW().matches(u.getNewPW())) {
                u.setId(currentUser.getId());
                if (this.userDetailsService.updateUser(u)) {
                    model.addAttribute("updateSuccess", "");
                    httpSession.removeAttribute("currentUser");
                    return "redirect:/accounts";
                } else {
                    model.addAttribute("err", "unknown");
                }
            } else {
                model.addAttribute("err", "confirmNewPW");
            }
        } else {
            model.addAttribute("err", "currentPW");
        }
        model.addAttribute("user", currentUser);
        return "setting";
    }
}
