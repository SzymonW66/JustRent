package com.JustRent.validators;

import jakarta.validation.Payload;

public @interface ValidEmail {
    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //dodaje oznaczenie @ValidEmail
}
