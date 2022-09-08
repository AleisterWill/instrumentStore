/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.controller;

import com.ldn.pojo.ImagePath;
import com.ldn.pojo.ImageSet;
import com.ldn.pojo.Product;
import com.ldn.service.BrandService;
import com.ldn.service.ImagePathService;
import com.ldn.service.ImageSetService;
import com.ldn.service.ProductService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @Autowired
    private BrandService brandService;
    
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
        model.addAttribute("newSet", new ImageSet());
        
        
        return "adminImgSet";
    }
    
    @PostMapping("/admin/product/imageSet")
    public String adminImgSetAdd(Model model,
            @ModelAttribute(value = "newSet") ImageSet newSet) {
        
        if (this.imgSetService.addImgSet(newSet)) {
            
        }
        
        return "redirect:/admin/product/imageSet";
    }
        
    
    @GetMapping("/admin/product/imageSet/{imgSetId}")
    public String adminImgPathView(Model model,
            @PathVariable(name = "imgSetId") String imgSetId,
            HttpSession httpSession) {
        
        Integer tmp = null;
        try {
            tmp = Integer.parseInt(imgSetId);
        } catch(Exception ex) {
            
        }
        ImagePath newImg = new ImagePath();
        ImageSet newImgSet = new ImageSet();
        newImgSet.setId(tmp);
        newImg.setImageSetId(newImgSet);
        
        List<ImagePath> Obj = this.imgPathService.getByImgSetId(tmp);
        model.addAttribute("ListImgPath", Obj);
        model.addAttribute("imgSetId", tmp);
        model.addAttribute("newImg", newImg);
        
        httpSession.setAttribute("currentImgSetId", tmp);
        
        return "adminImgPath";
    }
    
    @PostMapping("/admin/product/imageSet/{imgSetId}/addImg")
    public String adminImgPathAdd(Model model,
            @PathVariable(name = "imgSetId") String imgSetId,
            @ModelAttribute(value = "newImg") ImagePath newImg,
            HttpSession httpSession) {
            
        Integer currentImgSetId = (Integer) httpSession.getAttribute("currentImgSetId");
        ImageSet newImgSet = new ImageSet();
        newImgSet.setId(currentImgSetId);
        newImg.setImageSetId(newImgSet);
        
        if (imgPathService.addImg(newImg)) {
            
            
        }
        
        return "redirect:/admin/product/imageSet/" + currentImgSetId;
    }
    
    @GetMapping("/admin/product/add")
    public String adminProductAddView(Model model) {
        
        model.addAttribute("ListImgSet", this.imgSetService.getListImgSet());
        model.addAttribute("newProduct", new Product());
        model.addAttribute("ListBrand", this.brandService.getListBrand());
        model.addAttribute("mode", "add");
        
        return "adminProductAdd";
    }
    
    @PostMapping("/admin/product/add")
    public String adminProductAdd(@ModelAttribute(value = "newProduct") @Valid Product newProduct,
            BindingResult br,
            Model model) {
        
        if (!br.hasErrors()) {
            if (this.productService.addProduct(newProduct))
                return "redirect:/admin/product";
            model.addAttribute("error", "violation");
        }
        
        model.addAttribute("ListImgSet", this.imgSetService.getListImgSet());
        model.addAttribute("ListBrand", this.brandService.getListBrand());
        model.addAttribute("mode", "add");
        
        return "adminProductAdd";
    }
    
    @GetMapping("/admin/product/edit/{productId}")
    public String adminProductEditView(@PathVariable(name = "productId") String productId,
            Model model) {
        
        Integer pid = null;
        try {
            pid = Integer.parseInt(productId);
        } catch (Exception ex) {
            
        }
        Product p = this.productService.getProductById(pid);
        p.setBrandid(null);
        try {
            p.setBrandid(p.getBrandId().getId().toString());
        } catch (NullPointerException ex) {
        }
        
        p.setImgsetid(null);
        try {
            p.setImgsetid(p.getImageSetId().getId().toString());
        } catch (NullPointerException ex) {
        }
        
        p.setSubcatid(null);
        try {
            p.setSubcatid(p.getSubCategoryId().getId().toString());
        } catch (NullPointerException ex) {
        }
        
        
        model.addAttribute("newProduct", p);
        model.addAttribute("ListImgSet", this.imgSetService.getListImgSet());
        model.addAttribute("ListBrand", this.brandService.getListBrand());
        model.addAttribute("mode", "edit");
        
        return "adminProductAdd";
    }
    
    @PostMapping("/admin/product/edit/{productId}")
    public String adminProductEdit(@ModelAttribute(value = "newProduct") @Valid Product newProduct,
            @PathVariable(name = "productId") Integer productId,
            BindingResult br,
            Model model) {
        
        if (!br.hasErrors()) {
            newProduct.setId(productId);
            if (this.productService.updateProduct(newProduct))
                return "redirect:/admin/product";
            model.addAttribute("error", "violation");
        }
        
        model.addAttribute("ListImgSet", this.imgSetService.getListImgSet());
        model.addAttribute("ListBrand", this.brandService.getListBrand());
        model.addAttribute("mode", "edit");
        
        return "adminProductAdd";
    }
}
