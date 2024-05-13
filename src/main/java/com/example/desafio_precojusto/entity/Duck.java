package com.example.desafio_precojusto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Optional;

@Entity
@Table(name = "tbl_duck")
@NoArgsConstructor
@Getter
@Setter
public class Duck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_duck", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long idDuck;

    @Column(name = "name_duck", nullable = false, unique = true)
    private String nameDuck;

    @Column(name = "status_duck", nullable = false, unique = false)
    private String statusDuck;

    @Column(name = "value_duck", nullable = false, unique = false)
    private Integer valueDuck;

    @ManyToOne
    @JoinColumn(name = "parent_duck", nullable = true, referencedColumnName = "id_duck")
    private Duck parentDuck;

    @CreationTimestamp
    private Instant created_at;

    @UpdateTimestamp
    private Instant updated_at;

    public Duck(String nameDuck, String statusDuck, Integer valueDuck, Duck parentDuck, Instant created_at, Instant updated_at) {
        this.nameDuck = nameDuck;
        this.statusDuck = statusDuck;
        this.valueDuck = valueDuck;
        this.parentDuck = parentDuck;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
