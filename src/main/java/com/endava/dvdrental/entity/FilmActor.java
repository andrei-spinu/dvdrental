package com.endava.dvdrental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@EqualsAndHashCode(exclude = {"film", "actor"})
public class FilmActor implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    @JsonIgnoreProperties({"inventorySet", "language", "filmActorSet", "filmCategorySet"})
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    @JsonIgnoreProperties("filmActorSet")
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
