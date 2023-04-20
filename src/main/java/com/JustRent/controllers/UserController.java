package com.JustRent.controllers;

import com.JustRent.configurations.SecurityConfiguration;
import com.JustRent.configurations.WebMvcConfiguration;
import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.JustRent.services.UserService;
@Controller
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;
   private final WebMvcConfiguration webMvcConfiguration;
   private final SecurityConfiguration securityConfiguration;

   @GetMapping("/home")
   public String home1() {
       return "home";
   }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto) {
        ModelAndView mav = new ModelAndView();

        try {
            User registered = userService.registerNewUserAccount(userDto);
            mav.setViewName("home");
            mav.addObject("user", userDto);
            return mav;
        } catch (UserAlreadyExistException uaeEx) {
            mav.setViewName("registration");
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }
    }
}

