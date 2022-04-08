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
@Table(name = "address")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"staffSet", "customerSet", "storeSet", "city"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    @NotNull
    @Column(name = "address",
            nullable = false)
    private String address;

    @Column(name = "address2")
    private String secondAddress;

    @NotNull
    @Column(name = "district",
            nullable = false)
    private String district;

    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @Column(name = "phone",
            nullable = false)
    private String phone;

    @NotNull
    @Column(name = "last_update",
            nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime lastUpdate;

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"address", "store", "rentalSet","paymentSet"})
    private Set<Staff> staffSet = new HashSet<>();

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonIgnoreProperties({"store", "address", "rentalSet", "paymentSet"})
    private Set<Customer> customerSet = new HashSet<>();

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonIgnoreProperties({"address", "inventorySet", "staffSet", "customerSet"})
    private Set<Store> storeSet = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties({"addressSet", "country"})
    private City city;

    public Address() {
        this.setLastUpdate();
    }

    private void setLastUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
