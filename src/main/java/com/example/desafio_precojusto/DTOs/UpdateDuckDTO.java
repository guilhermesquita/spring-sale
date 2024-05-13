package com.example.desafio_precojusto.DTOs;

import java.util.UUID;

public record UpdateDuckDTO(String name_duck, String status_duck, Integer value_duck, UUID parent_duck) {
}
