package com.gm2.pdv.pdv.controller;

import com.gm2.pdv.pdv.dto.ResponseDTO;
import com.gm2.pdv.pdv.dto.UserDTO;
import com.gm2.pdv.pdv.exceptions.NotFoundUserException;
import com.gm2.pdv.pdv.model.User;
import com.gm2.pdv.pdv.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UseController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody UserDTO user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody UserDTO user) {
        try {

            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        } catch (NotFoundUserException error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("Usuário removido com sucesso"), HttpStatus.OK);
        } catch (NotFoundUserException error) {
            return new ResponseEntity<>(new ResponseDTO("Usuário nao encotrado"), HttpStatus.NOT_FOUND);
        }   catch (Exception error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
