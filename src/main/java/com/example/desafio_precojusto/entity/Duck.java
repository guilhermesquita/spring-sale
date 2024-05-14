package com.example.desafio_precojusto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tbl_duck")
@NoArgsConstructor
@Getter
@Setter
public class Duck {
    @Id
    @Column(name = "id_duck", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private UUID idDuck;

    @Column(name = "name_duck", nullable = false, unique = true)
    private String nameDuck;

    @Column(name = "status_duck", nullable = false, unique = false)
    private String statusDuck;

    @Column(name = "value_duck", nullable = false, unique = false)
    private Integer valueDuck;

    @ManyToOne
    @JoinColumn(name = "parent_duck", nullable = true, referencedColumnName = "id_duck")
    private Duck parentDuck;
    private Instant created_at;
    private Instant updated_at;

    public UUID getIdDuck() {
        return idDuck;
    }

    public String getNameDuck() {
        return nameDuck;
    }

    public void setNameDuck(String nameDuck) {
        this.nameDuck = nameDuck;
    }

    public String getStatusDuck() {
        return statusDuck;
    }

    public void setStatusDuck(String statusDuck) {
        this.statusDuck = statusDuck;
    }

    public Integer getValueDuck() {
        return valueDuck;
    }

    public void setValueDuck(Integer valueDuck) {
        this.valueDuck = valueDuck;
    }

    public Duck getParentDuck() {
        return parentDuck;
    }

    public void setParentDuck(Duck parentDuck) {
        this.parentDuck = parentDuck;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }

    public Duck(UUID idDuck, String nameDuck, String statusDuck, Integer valueDuck, Duck parentDuck, Instant created_at, Instant updated_at) {
        this.idDuck = idDuck;
        this.nameDuck = nameDuck;
        this.statusDuck = statusDuck;
        this.valueDuck = valueDuck;
        this.parentDuck = parentDuck;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
