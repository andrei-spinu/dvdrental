package com.endava.dvdrental.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"staffSet", "inventorySet", "customerSet", "address"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonIgnoreProperties({"film", "store", "rentalSet"})
    private Set<Inventory> inventorySet = new HashSet<>();

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"address", "store", "rentalSet", "paymentSet"})
    private Set<Staff> staffSet = new HashSet<>();

    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"store", "address", "rentalSet", "paymentSet"})
    private Set<Customer> customerSet = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties({"staffSet", "customerSet", "storeSet", "city"})
    private Address address;

    public Store() {
    }
}
