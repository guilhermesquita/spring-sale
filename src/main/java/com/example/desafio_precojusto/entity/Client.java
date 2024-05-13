package com.example.desafio_precojusto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tbl_client")
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_client", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private UUID idClient;

    @Column(name = "name_client", nullable = true, unique = true)
    private String nameClient;

    @Column(name = "type_client", nullable = false, unique = false)
    private String typeClient;

    @CreationTimestamp
    private Instant created_at;

    @UpdateTimestamp
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

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
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

    public Client(UUID idClient, String nameClient, String typeClient, Instant created_at, Instant updated_at) {
        this.idClient = idClient;
        this.nameClient = nameClient;
        this.typeClient = typeClient;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
