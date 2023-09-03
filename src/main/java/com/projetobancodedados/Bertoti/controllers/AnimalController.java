package com.projetobancodedados.Bertoti.controllers;

import com.projetobancodedados.Bertoti.animal.Animal;
import com.projetobancodedados.Bertoti.animal.AnimalRepository;
import com.projetobancodedados.Bertoti.animal.AnimalRequestDTO;
import com.projetobancodedados.Bertoti.animal.AnimalResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    private AnimalRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public Animal saveAnimal(@RequestBody AnimalRequestDTO data) {
        try {
            Animal animalData = new Animal(data);
            return repository.save(animalData);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível Cadastrar o seu animal, por favor verifique as informações " + e.getMessage());
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<AnimalResponseDTO> getAll() {
        try {
            List<AnimalResponseDTO> animalList = repository.findAll().stream().map(AnimalResponseDTO::new).toList();
            return animalList;
        } catch (Exception e) {
            throw new RuntimeException("Não há animais disponíveis");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getOne(@PathVariable Long id) {
        Optional<Animal> optionalAnimal = repository.findById(id);

        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            return ResponseEntity.ok(animal);
        } else {
            throw new RuntimeException("Animal não encontrado");
        }
    }




    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody AnimalRequestDTO partialData) {
        Animal animal = repository.findById(id).orElseThrow(() -> new RuntimeException("Animal não encontrado com o ID: " + id));

        try {
            if (partialData.nome() != null) {
                animal.setNome(partialData.nome());
            }
            if (partialData.cidade() != null) {
                animal.setCidade(partialData.cidade());
            }
            if (partialData.especie() != null) {
                animal.setEspecie(partialData.especie());
            }
            if (partialData.descricao() != null) {
                animal.setDescricao(partialData.descricao());
            }
            if (partialData.idade_meses() != null) {
                animal.setIdade_meses(partialData.idade_meses());
            }
            if (partialData.cor() != null) {
                animal.setCor(partialData.cor());
            }
            if (partialData.linkFoto() != null) {
                animal.setLinkFoto(partialData.linkFoto());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o animal: " + e.getMessage());
        }

        repository.save(animal);
        return animal;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAnimal(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok(Collections.emptyMap());
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erro ao deletar animal");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
