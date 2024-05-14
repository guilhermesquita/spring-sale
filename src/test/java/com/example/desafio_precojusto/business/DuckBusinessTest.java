package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.DuckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DuckBusinessTest {

    @Mock
    private DuckRepository duckRepository;

    private DuckBusiness duckBusiness;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        duckBusiness = new DuckBusiness(duckRepository);
    }

    @Test
    public void testCreateDuck() {
        UUID parentId = UUID.randomUUID();
        CreateDuckDTO createDuckDTO = new CreateDuckDTO("DuckName", parentId);
        Duck parentDuck = new Duck(parentId, "ParentDuck", "Available", 50, null, Instant.now(), null);
        Duck savedDuck = new Duck(UUID.randomUUID(), "DuckName", "Available", 70, parentDuck, Instant.now(), null);

        when(duckRepository.findById(parentId)).thenReturn(Optional.of(parentDuck));
        when(duckRepository.save(any(Duck.class))).thenReturn(savedDuck);

        UUID duckId = duckBusiness.createDuck(createDuckDTO);

        verify(duckRepository, times(1)).findById(parentId);
        verify(duckRepository, times(1)).save(any(Duck.class));

        assertEquals(savedDuck.getIdDuck(), duckId);
    }
}