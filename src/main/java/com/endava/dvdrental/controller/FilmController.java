package com.endava.dvdrental.controller;

import com.endava.dvdrental.entity.Film;
import com.endava.dvdrental.repository.FilmRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmRepository repository;

    public FilmController(FilmRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Film> getAll() {
        return repository.findAll();
    }
}
