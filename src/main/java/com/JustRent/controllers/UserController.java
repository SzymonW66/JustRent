package com.JustRent.controllers;

import com.JustRent.configurations.SecurityConfiguration;
import com.JustRent.configurations.WebMvcConfiguration;
import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/start")
    public String home() {
             return "start";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password.");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping("/homepage")
    public String homepage() {
        return "homepage";
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
    public String login(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/homepage";
    }
}



