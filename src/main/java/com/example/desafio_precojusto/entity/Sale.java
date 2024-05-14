package com.example.desafio_precojusto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tbl_sale")
@NoArgsConstructor
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_sale", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private UUID idSale;

    @ManyToOne
    @JoinColumn(name = "duck_id", nullable = false, referencedColumnName = "id_duck")
    private Duck idDuck;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "id_client")
    private Client idClient;

    @Column(name = "value_sale", nullable = false, unique = false)
    private Integer valueSale;

    @CreationTimestamp
    private Instant created_at;

    public UUID getIdSale() {
        return idSale;
    }

    public Duck getIdDuck() {
        return idDuck;
    }

    public void setIdDuck(Duck idDuck) {
        this.idDuck = idDuck;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Instant getCreated_at() {
        return created_at;
    }


    public Sale(UUID idSale, Duck idDuck, Client idClient, Integer valueSale, Instant created_at) {
        this.idSale = idSale;
        this.idDuck = idDuck;
        this.idClient = idClient;
        this.valueSale = valueSale;
        this.created_at = created_at;
    }

    public Integer getValueSale() {
        return valueSale;
    }

    public void setValueSale(Integer valueSale) {
        this.valueSale = valueSale;
    }
}
