package com.endava.dvdrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Integer id;

    @Length(max = 45)
    @NotNull
    @Column(name = "first_name",
            length = 45,
            nullable = false)
    private String firstName;

    @Length(max = 45)
    @NotNull
    @Column(name = "last_name",
            length = 45,
            nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "last_update",
            nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(
            mappedBy = "actor",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonBackReference
    private Set<FilmActor> films = new HashSet<>();

    public Actor() {
    }
}
