package com.example.desafio_precojusto.business;
import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.DuckRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class DuckBusiness {

    private DuckRepository duckRepository;

    public DuckBusiness(DuckRepository duckRepository) {
        this.duckRepository = duckRepository;
    }

    public String createDuck(CreateDuckDTO createDuckDTO){
        Long parentId = createDuckDTO.parent_duck();
        Duck parentDuck = null;
        if (parentId != null) {
            Optional<Duck> duckById = duckRepository.findById(parentId);
            parentDuck = duckById.orElse(null);
        }

        var duck = new Duck(
                createDuckDTO.name_duck(),
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

    public List<Duck> listUser(){
        return duckRepository.findAll();
    }

    public void deleteById(Long id){
        var duckExists = duckRepository.existsById(id);
        if(duckExists){
            duckRepository.deleteById(id);
        }
    }
}
