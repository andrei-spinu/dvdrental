package com.endava.dvdrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "language")
@Getter
@Setter
@EqualsAndHashCode(exclude = "filmSet")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Language implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer id;

    @NotNull
    @Column(name = "name",
            nullable = false)
    private String name;

    @NotNull
    @Column(name = "last_update",
            nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime lastUpdate;

    @OneToMany(
            mappedBy = "language",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"inventorySet", "language", "filmActorSet", "filmCategorySet"})
    private Set<Film> filmSet = new HashSet<>();

    public Language() {
        this.setLastUpdate();
    }

    private void setLastUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
