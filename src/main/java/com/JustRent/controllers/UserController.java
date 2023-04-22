package com.JustRent.controllers;

import com.JustRent.configurations.SecurityConfiguration;
import com.JustRent.configurations.WebMvcConfiguration;
import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import com.JustRent.services.UserService;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WebMvcConfiguration webMvcConfiguration;
    private final SecurityConfiguration securityConfiguration;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String home1() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) throws UserAlreadyExistException {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/registration";
        }


        userService.registerNewUserAccount(userDto);
        return "redirect:/registration?success";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpServletRequest request) {

        if (error != null) {
            model.addAttribute("error", "Invalid email and password.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/home";
        } catch (AuthenticationException ex) {
            model.addAttribute("error", "Invalid email and password.");
            return "login";
        }
    }
}



