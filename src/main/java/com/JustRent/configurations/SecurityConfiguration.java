package com.JustRent.configurations;

import ch.qos.logback.classic.Logger;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Configuration
@EnableMethodSecurity
@NoArgsConstructor(force = true)
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/start").permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/registration/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage", true)
                .failureUrl("/loginError");
        return http.build();
    }
    @ControllerAdvice
    public class ErrorController {
        private static Logger logger = (Logger) LoggerFactory.getLogger(ErrorController.class);
        @ExceptionHandler(Throwable.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String exception(final Throwable throwable, final Model model) {
            logger.error("Exception during execution of SpringSecurity application", throwable);
            String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
            model.addAttribute("errorMessage", errorMessage);
            return "error";
        }
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
