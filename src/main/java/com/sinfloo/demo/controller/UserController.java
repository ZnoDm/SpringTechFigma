package com.sinfloo.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController{
    
    @Autowired
    private com.sinfloo.demo.service.UserServicelmpl usuarioServicio;
    
    @GetMapping("/list")
    public String listarUsuario(Model model){

        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        return "admin/user/list-user";
    }
}
