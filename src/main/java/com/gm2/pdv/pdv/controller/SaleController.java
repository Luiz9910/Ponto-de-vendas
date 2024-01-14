package com.gm2.pdv.pdv.controller;

import com.gm2.pdv.pdv.dto.ResponseDTO;
import com.gm2.pdv.pdv.dto.SaleDTO;
import com.gm2.pdv.pdv.dto.SaleinfoDTO;
import com.gm2.pdv.pdv.exceptions.InvalidOperationInvalidException;
import com.gm2.pdv.pdv.exceptions.NoItemException;
import com.gm2.pdv.pdv.exceptions.NotFoundUserException;
import com.gm2.pdv.pdv.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping()
    public ResponseEntity getAll() {
        return new ResponseEntity<>(saleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(saleService.getById(id), HttpStatus.OK);
        } catch (NoItemException error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody SaleDTO saleDTO) {
        try {
            long id = saleService.save(saleDTO);
            return new ResponseEntity<>(new ResponseDTO("Venda realizada com sucesso"), HttpStatus.CREATED);
        } catch (NotFoundUserException error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.NOT_FOUND);
        } catch (NoItemException | InvalidOperationInvalidException error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception error) {
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
