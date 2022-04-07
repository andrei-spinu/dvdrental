package com.endava.dvdrental.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;

    @NotNull
    @Column(name = "title",
            nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @NotNull
    @Column(name = "rental_duration",
            nullable = false)
    private Short rental_duration;

    @NotNull
    @Column(name = "rental_rate",
            nullable = false,
            length = 4,
            precision = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @NotNull
    @Column(name = "replacement_cost",
            nullable = false,
            length = 5,
            precision = 2)
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @NotNull
    @Column(name = "last_update",
            nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(
            mappedBy = "film",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<FilmCategory> categories = new HashSet<>();

    @OneToMany(
            mappedBy = "film",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<FilmActor> actors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    @JsonManagedReference
    private Language language;

    @OneToMany(
            mappedBy = "film",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<Inventory> inventories = new HashSet<>();

    public Film() {
    }
}
