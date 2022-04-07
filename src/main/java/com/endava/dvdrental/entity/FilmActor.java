package com.endava.dvdrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FilmActor implements Serializable {

    @EmbeddedId
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    @JsonManagedReference
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    @JsonManagedReference
    private Actor actor;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate = LocalDateTime.now();

    public FilmActor() {
    }

    public FilmActor(Film film, Actor actor) {
        this.film = film;
        this.actor = actor;
    }
}
