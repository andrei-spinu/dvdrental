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
@Table(name = "staff")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"rentalSet", "paymentSet", "store", "address"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;

    @Length(max = 45)
    @Column(name = "first_name",
            length = 45,
            nullable = false)
    private String firstName;

    @Length(max = 45)
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
    @Column(name = "active",
            nullable = false)
    private Boolean active;

    @NotNull
    @Length(max = 16)
    @Column(name = "username",
            length = 16,
            nullable = false)
    private String username;

    @NotNull
    @Length(min = 8,
            max = 40)
    @Column(name = "password",
            nullable = false,
            length = 40)
    private String password;

    @Column(name = "last_update",
            nullable = false)
    private LocalDateTime lastUpdate;

    @Column(name = "picture")
    private byte[] picture;

    @OneToMany(
            mappedBy = "staff",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"inventory", "customer", "staff", "paymentSet"})
    private Set<Rental> rentalSet = new HashSet<>();

    @OneToMany(
            mappedBy = "staff",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonIgnoreProperties({"rental", "staff", "customer"})
    private Set<Payment> paymentSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @JsonIgnoreProperties({"address", "inventorySet", "staffSet", "customerSet"})
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties({"staffSet", "customerSet", "storeSet", "city"})
    private Address address;

    public Staff() {
    }
}
