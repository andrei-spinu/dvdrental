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
@Table(name = "city")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"addressSet", "country"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer id;

    @NotNull
    @Column(name = "city", nullable = false)
    private String cityName;

    @NotNull
    @Column(name = "last_update", nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime lastUpdate;


    @OneToMany(mappedBy = "city",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"staffSet", "customerSet", "storeSet", "city"})
    private Set<Address> addressSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @JsonIgnoreProperties("citySet")
    private Country country;

    public City() {
        this.setLastUpdate();
    }

    private void setLastUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}
