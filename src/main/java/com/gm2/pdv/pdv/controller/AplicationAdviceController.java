package com.gm2.pdv.pdv.controller;

import com.gm2.pdv.pdv.dto.ResponseDTO;
import com.gm2.pdv.pdv.exceptions.InvalidOperationInvalidException;
import com.gm2.pdv.pdv.exceptions.NoItemException;
import com.gm2.pdv.pdv.exceptions.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AplicationAdviceController {
    @ExceptionHandler(NoItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleNoItemException(NoItemException ex) {
        String messageError = ex.getMessage();
        return new ResponseDTO(messageError);
    }

    @ExceptionHandler(InvalidOperationInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleInvalidOperationInvalidException(InvalidOperationInvalidException ex) {
        String messageError = ex.getMessage();
        return new ResponseDTO(messageError);
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDTO handleNotFoundUserException(NotFoundUserException ex) {
        String messageError = ex.getMessage();
        return new ResponseDTO(messageError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> erros = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            erros.add(errorMessage);
        });

        return new ResponseDTO(erros);
    }
}
