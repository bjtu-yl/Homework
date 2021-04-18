package com.crud.bookshop.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.crud.bookshop.controller.dto.UserRegistrationDto;
import com.crud.bookshop.domain.User;
import com.crud.bookshop.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        User existing = userService.findByUsername(userDto.getUsername());
        if (existing != null){
            result.rejectValue("username", null, "用户名已存在！");
        }
        if (result.hasErrors()){
            return "registration";
        }
        userService.save(userDto);
        return "redirect:/login?success";
    }
}

