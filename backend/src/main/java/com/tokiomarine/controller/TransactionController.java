package com.tokiomarine.controller;

import com.tokiomarine.exception.BadRequestException;
import com.tokiomarine.model.Transaction;
import com.tokiomarine.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {



    @Autowired
    TransactionService service;

    @PostMapping
    public ResponseEntity<?> schedule(@RequestBody Transaction request){
        try {
            service.schedule(request, request.getOperation().getCode());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (BadRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
