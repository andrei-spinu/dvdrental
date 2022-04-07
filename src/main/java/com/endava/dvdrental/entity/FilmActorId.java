package com.endava.dvdrental.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class FilmActorId implements Serializable {

    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "actor_id")
    private Integer actorId;

    public FilmActorId() {
    }

    public FilmActorId(Integer filmId, Integer actorId) {
        this.filmId = filmId;
        this.actorId = actorId;
    }




}
