package com.gm2.pdv.pdv.controller;

import com.gm2.pdv.pdv.model.Product;
import com.gm2.pdv.pdv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getall() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }
}
