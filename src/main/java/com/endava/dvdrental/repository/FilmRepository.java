package com.endava.dvdrental.repository;

import com.endava.dvdrental.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
