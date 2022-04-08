package com.endava.dvdrental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"film", "store", "rentalSet"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @NotNull
    @Column(name = "last_update")
    @Setter(AccessLevel.NONE)
    private LocalDateTime lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    @JsonIgnoreProperties({"inventorySet", "language", "filmActorSet", "filmCategorySet"})
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties({"address", "inventorySet", "staffSet", "customerSet"})
    private Store store;

    @OneToMany(mappedBy = "inventory",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"inventory", "customer", "staff", "paymentSet"})
    private Set<Rental> rentalSet = new HashSet<>();

    public Inventory() {
        this.setLastUpdate();
    }

    private void setLastUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
