package com.example.desafio_precojusto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tbl_client")
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "id_client", nullable = false, unique = true)
    private UUID idClient;

    @Column(name = "name_client", nullable = true, unique = true)
    private String nameClient;

    @Column(name = "descont", nullable = false, unique = false)
    private boolean descont;

    private Instant created_at;

    private Instant updated_at;

    public UUID getIdClient() {
        return idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public Boolean getDescont() {
        return descont;
    }

    public void setDescont(Boolean descont) {
        this.descont = descont;
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

    public Client(UUID idClient, String nameClient, boolean descont, Instant created_at, Instant updated_at) {
        this.idClient = idClient;
        this.nameClient = nameClient;
        this.descont = descont;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
