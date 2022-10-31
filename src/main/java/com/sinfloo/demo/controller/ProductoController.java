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

import com.sinfloo.demo.model.Producto;
import com.sinfloo.demo.model.Categoria;
@Controller
@RequestMapping("/product")
public class ProductoController {

	@Autowired
    private com.sinfloo.demo.service.ProductoService productService;

    @Autowired
    private com.sinfloo.demo.service.CategoriaService productTypeService;

    private String add_edit_template="admin/product/add-edit-product";
    private String list_template="admin/product/list-product";
    private String list_redirect="redirect:/product/list";


    @GetMapping("/add")
    public String addProduct(Producto product, Model model){
        model.addAttribute("product", product);
        List<Categoria> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Producto product = productService.get(id);
        model.addAttribute("product", product);

        List<Categoria> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Producto product, BindingResult result, Model model){
        model.addAttribute("product", product);
        List<Categoria> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        if(result.hasErrors()){
            return add_edit_template;
        }

        productService.save(product);
        return list_redirect;
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.delete(id);

        return list_redirect;
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        List<Categoria> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        List<Producto> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);

        return list_template;
    }
}
