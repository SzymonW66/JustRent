package com.JustRent.controllers;

import com.JustRent.dto.CarDto;
import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.Car;
import com.JustRent.models.User;
import com.JustRent.repositories.UserRepository;
import com.JustRent.services.CarService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import com.JustRent.services.UserService;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final CarService carService;

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
        if (user == null) {
            return "redirect:/login?error";
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext());
        session.setAttribute("userId", user.getId());
        System.out.println(user.getId());
        System.out.println(model);
        return "redirect:/homepage";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute("car") CarDto carDto, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        if (loggedInUserId == null) {
            System.out.println("Unable to get user ID from session.");
            return "redirect:/homepage";
        }
        carService.addNewCarToRent(carDto, loggedInUserId);
        return "redirect:/homepage";
    }

    @GetMapping("/add-car")
    private String addNewCar(Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        System.out.println(loggedInUserId + " " + "loggedInUserID");
        if (loggedInUserId == null) {
            System.out.println("Unable to get user ID for id ");
            return "redirect:/homepage";
        }
        model.addAttribute("userId", loggedInUserId);
        model.addAttribute("car", new CarDto());
        return "addNewCarToRent";
    }

    @GetMapping("/car-list")
    public String displayCarList(Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        System.out.println(loggedInUserId + "homepage");
        List<Car> cars = carService.getAllCars();
        for (Car car : cars) {
            byte[] imageBytes = car.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            car.setPhotoBase64(base64Image);
        }
        model.addAttribute("cars", cars);
        model.addAttribute("userId", loggedInUserId);
        return "car-list";
    }
    @GetMapping("/car-details/{id}")
    public String getCarDetails(@PathVariable("id") Long id, Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        System.out.println(loggedInUserId + "homepage");
        Car car = carService.getCarById(id);
        String base64Image = Base64.getEncoder().encodeToString(car.getImage());
        car.setPhotoBase64(base64Image);
        model.addAttribute("car", car);
        model.addAttribute("userId", loggedInUserId);
        return "car-details";
    }

    @GetMapping("/your-cars")
    public String getYourCarList(Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        List<Car> cars = carService.getCarsByUserId(loggedInUserId);
        for (Car car : cars) {
            byte[] imageBytes = car.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            car.setPhotoBase64(base64Image);
        }
        model.addAttribute("cars", cars);
        model.addAttribute("userId", loggedInUserId);
        return "user-cars";
    }

    @GetMapping("/car-details-delete/{id}")
    public String getCarDetailsDelete(@PathVariable("id") Long id, Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        System.out.println(loggedInUserId + "homepage");
        Car car = carService.getCarById(id);
        String base64Image = Base64.getEncoder().encodeToString(car.getImage());
        car.setPhotoBase64(base64Image);
        model.addAttribute("car", car);
        model.addAttribute("userId", loggedInUserId);
        return "car-details-delete";
    }

    @PostMapping("/car/{id}/delete")
    public String deleteCar(@PathVariable("id") Long id) {
        carService.deleteById(id);
        return "redirect:/your-cars";
    }

    @GetMapping("/logout")
    public String logout() {
        return "start";
    }
}


