package com.pacozabala.taskmanager.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //implemented from phase 7, task validation
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        BindingResult bindingResult = ex.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for(FieldError error: fieldErrors) {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        }

        return ResponseEntity.badRequest().body(errors);
    }
    
}
