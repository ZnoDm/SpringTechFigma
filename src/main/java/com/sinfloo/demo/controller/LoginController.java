package com.sinfloo.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sinfloo.demo.controller.dto.UserRegistroDTO;
import com.sinfloo.demo.model.User;
import com.sinfloo.demo.service.UserService;
@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(Model model) {

        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserRegistroDTO  UserRegistroDTO = new  UserRegistroDTO();
        model.addAttribute("userRegistrationDto", UserRegistroDTO);

        return "auth/register";
    }
    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("userRegistrationDto")  UserRegistroDTO userRegistrationDto, BindingResult result, Model model) {
        model.addAttribute("userRegistrationDto", userRegistrationDto);

        User userExists = userService.findByUsername(userRegistrationDto.getUserName());

        //System.out.println("user-->"+userRegistrationDto.getUserName());
        //System.out.println("userExists-->"+userExists);

        if (userExists != null) {
            return "redirect:/register?username";
        }
        if(result.hasErrors()){
            return "auth/register";
        }
        userService.save(userRegistrationDto);
        return "redirect:/register?success";
    }
}
