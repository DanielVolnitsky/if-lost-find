package com.daniel.iflostfind.util.validation;

import com.daniel.iflostfind.util.annotation.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    //Ruby option: /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
    private static final String EMAIL_REGEX =
        "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
