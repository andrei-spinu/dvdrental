package com.endava.dvdrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "FilmCategory")
@Table(name = "film_category")
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FilmCategory implements Serializable {

    @EmbeddedId
    private FilmCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @MapsId("categoryId")
    @JsonManagedReference
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    @MapsId("filmId")
    @JsonManagedReference
    private Film film;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public FilmCategory() {
    }

    public FilmCategory(Category category, Film film) {
        this.category = category;
        this.film = film;
        this.id = new FilmCategoryId(category.getId(),film.getId());
    }
}
