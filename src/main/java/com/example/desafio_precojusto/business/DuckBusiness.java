package com.example.desafio_precojusto.business;
import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.DTOs.UpdateDuckDTO;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.DuckRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DuckBusiness {

    private DuckRepository duckRepository;

    public DuckBusiness(DuckRepository duckRepository) {
        this.duckRepository = duckRepository;
    }

    public String createDuck(CreateDuckDTO createDuckDTO){
        UUID parentId = createDuckDTO.parent_duck();
        Duck parentDuck = null;
        if (parentId != null) {
            Optional<Duck> duckById = duckRepository.findById(parentId);
            parentDuck = duckById.orElse(null);
            parentDuck.setValueDuck(50);
            if (parentDuck == null) {
                throw new IllegalArgumentException("Pato pai não encontrado.");
            }
        }

        var duck = new Duck(
                UUID.randomUUID(),
                createDuckDTO.name_duck(),
                "Disponível",
                70,
                parentDuck,
                Instant.now(),
                null);

        duckRepository.save(duck);
        return "Criado com sucesso!";
    }

    public Optional<Duck> getDuckById(UUID id){
        var duckId = duckRepository.findById(id);
        if (duckId.isPresent()) {
            return duckId;
        }else {
            throw new IllegalArgumentException("Pato não encontrado.");
        }
    };

    public List<Duck> listDuck(){
        return duckRepository.findAll();
    }

    public void updateById(UUID id, UpdateDuckDTO updateUserDTO){
        var duckExists = duckRepository.findById(id);
        if(duckExists.isPresent()){
            var duck = duckExists.get();

            if(updateUserDTO.name_duck() != null){
                duck.setNameDuck(updateUserDTO.name_duck());
            }
            if(updateUserDTO.parent_duck() != null){
                Duck parentDuck = null;

                Optional<Duck> duckById = duckRepository.findById(updateUserDTO.parent_duck());
                parentDuck = duckById.orElse(null);
                duck.setParentDuck(parentDuck);
            }
            duckRepository.save(duck);
        }else {
            throw new IllegalArgumentException("Pato não encontrado.");
        }
    }

    public void deleteById(UUID id){
        var duckExists = duckRepository.existsById(id);
        if(duckExists){
            duckRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Pato não encontrado.");
        }
    }
}
