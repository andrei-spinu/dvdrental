package com.endava.dvdrental.controller;

import com.endava.dvdrental.entity.Rental;
import com.endava.dvdrental.repository.RentalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    private final RentalRepository repository;

    public RentalController(RentalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Rental> getAll(){
        return repository.findAll();
    }

}


