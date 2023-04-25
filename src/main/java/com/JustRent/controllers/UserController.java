package com.JustRent.controllers;

import com.JustRent.configurations.SecurityConfiguration;
import com.JustRent.configurations.WebMvcConfiguration;
import com.JustRent.dto.CarDto;
import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.User;
import com.JustRent.repositories.UserRepository;
import com.JustRent.services.CarService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WebMvcConfiguration webMvcConfiguration;
    private final SecurityConfiguration securityConfiguration;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CarService carService;
    private final HttpSession httpSession;

    @GetMapping("/start")
    public String home() {
             return "start";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout, HttpSession session) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password.");
        }
        if (logout != null) {
            model.addAttribute("logout", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model, HttpSession session) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping("/homepage")
    public String homepage(Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        System.out.println(loggedInUserId + "homepage");
        model.addAttribute("userId", loggedInUserId);
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
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userRepository.findByEmailAndPassword(username, password);

        // jeśli użytkownik nie został znaleziony, zwróć błąd logowania
        if (user == null) {
            return "redirect:/login?error";
        }

        // jeśli użytkownik został znaleziony, utwórz listę ról i dodaj rolę "user"
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // utwórz obiekt Authentication z danymi użytkownika i ustaw go jako zalogowanego w SecurityContextHolder
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext());
        session.setAttribute("userId", user.getId());
        System.out.println(user.getId());
        System.out.println(model);
        // przekieruj na stronę główną po udanej autentykacji
        return "redirect:/homepage";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute("car") CarDto carDto, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        if (loggedInUserId == null) {
            System.out.println("Unable to get user ID from session.");
            return "redirect:/homepage"; // nie udało się pobrać ID użytkownika, przekieruj na stronę główną
        }

        carService.addNewCarToRent(carDto, loggedInUserId);
        return "redirect:/homepage";
    }

    @GetMapping("/add-car")
    private String addNewCar(Model model, HttpSession session ) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        System.out.println(loggedInUserId);
        if (loggedInUserId == null) {
            System.out.println("Unable to get user ID for id ") ;
            return "redirect:/homepage"; // nie udało się pobrać ID użytkownika, przekieruj na stronę główną
        }

        model.addAttribute("userId", loggedInUserId);
        model.addAttribute("car", new CarDto());
        return "addNewCarToRent";
    }
}

//Mam przekazane nr Id po logowaniu
// Przycisk logout będzie po prosty przypisywał null dla  i tyle

