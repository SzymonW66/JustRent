package com.JustRent.repositories;

import com.JustRent.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
//Repo- klasa odpowiedzialna za komunikacje z bazą danych (JPA Repository ułatwia znacznie)