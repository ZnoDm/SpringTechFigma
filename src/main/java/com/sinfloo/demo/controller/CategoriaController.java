package com.sinfloo.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinfloo.demo.model.Categoria;
import com.sinfloo.demo.service.CategoriaService;


@Controller
@RequestMapping("/producttype")
public class CategoriaController {

    @Autowired
    private CategoriaService productTypeService;

    private String add_edit_template="/admin/producttype/add-edit-producttype";
    private String list_template="/admin/producttype/list-producttype";
    private String list_redirect="redirect:/producttype/list";

    @GetMapping("/add")
    public String addProductType(Categoria producttype, Model model){
        model.addAttribute("producttype", producttype);
        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editProductType(@PathVariable("id") int id, Model model){
        Categoria producttype = productTypeService.get(id);

        model.addAttribute("producttype", producttype);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProductType(@Valid @ModelAttribute("producttype") Categoria producttype, BindingResult result, Model model){
        model.addAttribute("producttype", producttype);

        if(result.hasErrors()){
            return add_edit_template;
        }
        productTypeService.save(producttype);

        return list_redirect;
    }

    @GetMapping("/delete/{id}")
    public String deleteProductType(@PathVariable("id") int id, Model model) {
        productTypeService.delete(id);

        return list_redirect;
    }

    @GetMapping("/list")
    public String listProductType(Model model) {
        List<Categoria> listProductTypes = productTypeService.listAll();
        model.addAttribute("listProductTypes", listProductTypes);

        return list_template;
    }
}
