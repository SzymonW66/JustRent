package com.JustRent.configurations;

import ch.qos.logback.classic.Logger;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Configuration
@EnableMethodSecurity
@NoArgsConstructor(force = true)
public class SecurityConfiguration {

    private final CustomAuthenticationFilter customAuthenticationFilter;
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password("user1Pass")
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password("user2Pass")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("adminPass")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
    //dodanie wbudowanych dwóch userów i admina do testów 1


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().and()
                .authorizeRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/registration").permitAll()
//                .requestMatchers("/admin/**").permitAll()
//                .requestMatchers("/anonymous*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/home.", true)
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");
        return http.build();
    }
  //  filtrowanie kto ma jaką role i na co wchodzi

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

}
