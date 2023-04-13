package com.JustRent.validators;

import jakarta.validation.Payload;

public @interface PasswordMatches {
    String message() default "Passwords don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //dodaje oznaczenie dotyczące zgodności obydwóch haseł
}
