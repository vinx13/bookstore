package me.vincentlin.bookstore.model.validator;

import java.math.BigDecimal;
import me.vincentlin.bookstore.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class Validators {
    @Bean
    Validator beforeSaveBookValidator() {
        return new Validator() {
            @Override public boolean supports(Class<?> clazz) {
                if (clazz.equals(Book.class))
                    return true;
                return false;
            }

            @Override public void validate(Object target, Errors errors) {
                Book book = (Book)target;
                if(book.getPrice().compareTo(new BigDecimal(0)) < 0 || book.getInventory() < 0) {
                    addValidationError(errors);
                }
            }
        };
    }


    private void addValidationError(Errors errors) {
        errors.reject("Validation error", "Validation error");
    }
}
