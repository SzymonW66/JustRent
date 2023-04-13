package com.JustRent.controllers;

import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.JustRent.services.UserService;
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto, Errors errors) {
        ModelAndView mav = new ModelAndView();

        try {
            User registered = userService.registerNewUserAccount(userDto);
            mav.setViewName("successRegister");
            mav.addObject("user", userDto);
            return mav;
        } catch (UserAlreadyExistException uaeEx) {
            mav.setViewName("registration");
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
    }
}


