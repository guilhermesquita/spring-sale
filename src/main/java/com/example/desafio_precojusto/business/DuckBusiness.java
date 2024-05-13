package com.example.desafio_precojusto.business;
import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.DuckRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class DuckBusiness {

    private DuckRepository duckRepository;

    public DuckBusiness(DuckRepository duckRepository) {
        this.duckRepository = duckRepository;
    }

    public String CreateDuck(CreateDuckDTO createDuckDTO){

        Optional<Duck> duckById = duckRepository.findById(createDuckDTO.parent_duck());
        Duck parentDuck = duckById.orElse(null);

        var duck = new Duck(createDuckDTO.name_duck(),
                "Disponível",
                70,
                parentDuck,
                Instant.now(),
                null);

        duckRepository.save(duck);
        return "Criado com sucesso!";
    }

    public Optional<Duck> getDuckById(Long id){
        return duckRepository.findById(id);
    };
}
