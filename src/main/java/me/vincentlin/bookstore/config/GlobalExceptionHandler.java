package me.vincentlin.bookstore.config;


import me.vincentlin.bookstore.common.OrderPermissionException;
import me.vincentlin.bookstore.common.UnloginedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnloginedException.class)
    public void handleUnlogin() {
        // Nothing to do
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(OrderPermissionException.class)
    public void handlePermission() {
        // Nothing to do
    }
}
