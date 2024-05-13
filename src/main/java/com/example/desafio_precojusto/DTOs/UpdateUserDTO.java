package com.example.desafio_precojusto.DTOs;

import com.example.desafio_precojusto.entity.Duck;

import java.util.UUID;

public record UpdateUserDTO(String name_duck, String status_duck, Integer value_duck, UUID parent_duck) {
}
