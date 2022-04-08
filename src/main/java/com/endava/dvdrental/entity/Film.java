package com.endava.dvdrental.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
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
@EqualsAndHashCode(exclude = {"filmCategorySet", "filmActorSet", "inventorySet", "language"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @NotNull
    @Column(name = "rental_duration", nullable = false)
    private Short rental_duration;

    @NotNull
    @Column(name = "rental_rate", nullable = false, length = 4, precision = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @NotNull
    @Column(name = "replacement_cost", nullable = false, length = 5, precision = 2)
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @NotNull
    @Column(name = "last_update",
            nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "film",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"film"})
    private Set<FilmCategory> filmCategorySet = new HashSet<>();

    @OneToMany(mappedBy = "film",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"film"})
    private Set<FilmActor> filmActorSet = new HashSet<>();


    @OneToMany(mappedBy = "film",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"film", "store", "rentalSet"})
    private Set<Inventory> inventorySet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    @JsonIgnoreProperties("filmSet")
    private Language language;

    public Film() {
        this.setLastUpdate();
    }

    private void setLastUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
