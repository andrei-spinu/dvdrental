package com.endava.dvdrental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    @NotNull
    @Length(min = 3,
            max = 45)
    @Column(name = "first_name",
            length = 45,
            nullable = false)
    private String firstName;

    @NotNull
    @Length(min = 3,
            max = 45)
    @Column(name = "last_name",
            length = 45,
            nullable = false)
    private String lastName;

    @Length(max = 50)
    @Email
    @Column(name = "email",
            length = 50)
    private String email;

    @NotNull
    @Column(name = "activebool",
            nullable = false)
    private Boolean active;

    @NotNull
    @Column(name = "create_date",
            nullable = false)
    private LocalDateTime createDate;

    @NotNull
    @Column(name = "last_update",
            nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonBackReference
    private Set<Rental> rentals = new HashSet<>();

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonBackReference
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonManagedReference
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;

    public Customer() {
    }
}
