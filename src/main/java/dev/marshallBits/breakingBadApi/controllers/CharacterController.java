package dev.marshallBits.breakingBadApi.controllers;

import dev.marshallBits.breakingBadApi.dto.CharacterDTO;
import dev.marshallBits.breakingBadApi.dto.CreateCharacterDTO;
import dev.marshallBits.breakingBadApi.services.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterServiceImpl characterService;

    // ✅ IMPLEMENTADO: Obtener todos los personajes
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterDTO> getAllCharacters() {
        return characterService.findAll();
    }

    // ✅ IMPLEMENTADO: Crear nuevo personaje
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDTO createCharacter(@Valid @RequestBody CreateCharacterDTO createCharacterDTO) {
        return characterService.createCharacter(createCharacterDTO);
    }

    // 🔧 TODO: Obtener personaje por ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDTO getCharacterById(@PathVariable Long id) {
        return characterService.findById(id);
    }

    // 🔧 TODO: Cambiar estado de Alive a Dead
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDTO updateCharacterStatus(@PathVariable Long id) {
        // Usar characterService.updateStatusToDead(id)
        return characterService.updateStatusToDead(id);
    }
}