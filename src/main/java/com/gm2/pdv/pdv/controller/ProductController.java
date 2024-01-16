package com.gm2.pdv.pdv.controller;

import com.gm2.pdv.pdv.dto.Product.ProductDTO;
import com.gm2.pdv.pdv.dto.ResponseDTO;
import com.gm2.pdv.pdv.exceptions.NotFoundUserException;
import com.gm2.pdv.pdv.model.Product;
import com.gm2.pdv.pdv.repository.ProductRepository;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    private ModelMapper mapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ProductDTO product) {
        return new ResponseEntity<>(productRepository.save(mapper.map(product, Product.class)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody ProductDTO product) {
        try {
            return new ResponseEntity<>(productRepository.save(mapper.map(product, Product.class)), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        try {
            Optional<Product> productToEdit = productRepository.findById(id);
            if (!productToEdit.isPresent()) {
                throw new NotFoundUserException("Usuário não encontrado");
            }

            productRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("Product deletado com sucesso"), HttpStatus.OK);
        } catch (NotFoundUserException error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.NOT_FOUND);
        }catch (Exception error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
