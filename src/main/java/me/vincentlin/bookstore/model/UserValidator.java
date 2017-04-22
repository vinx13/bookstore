package me.vincentlin.bookstore.model;

import me.vincentlin.bookstore.model.User;
import me.vincentlin.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Vincent on 2017/3/31.
 */
@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", null, "Invalid username");
        } else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", null, "Invalid username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", null, "Username exists");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", null, "Invalid password");
        } else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "Invalid password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", null, "Inconsistent password");
        }
    }
}