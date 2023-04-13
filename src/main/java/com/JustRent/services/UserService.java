package com.JustRent.services;

import com.JustRent.dto.UserDto;
import com.JustRent.exceptions.UserAlreadyExistException;
import com.JustRent.models.User;
import com.JustRent.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserService implements IUserServices {
    @Autowired
    private UserRepository repository;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }

        User user = new User();
        user.setName(userDto.getFirstName());
        user.setSurname(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(String.valueOf(Arrays.asList("ROLE_USER")));

        return repository.save(user);
        //rejestruje nowego usera
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email) != null;
    }
}
