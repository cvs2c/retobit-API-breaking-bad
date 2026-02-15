package dev.marshallBits.breakingBadApi.services;

import dev.marshallBits.breakingBadApi.dto.CharacterDTO;
import dev.marshallBits.breakingBadApi.dto.CreateCharacterDTO;
import dev.marshallBits.breakingBadApi.models.Character;
import dev.marshallBits.breakingBadApi.models.CharacterStatus;
import dev.marshallBits.breakingBadApi.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<CharacterDTO> findAll() {
        return characterRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterDTO createCharacter(CreateCharacterDTO createCharacterDTO) {
        Character character = convertToEntity(createCharacterDTO);
        character = characterRepository.save(character);
        return convertToDTO(character);
    }

    // TODO: Obtener personaje por ID
    @Override
    public CharacterDTO findById(Long id) {
        // PISTA: Usar characterRepository.findById(id)
        return convertToDTO( characterRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no encontrado")));

    }

    // TODO: Cambiar estado de Alive a Dead
    @Override
    public CharacterDTO updateStatusToDead(Long id) {
        // PISTA: Buscar personaje por ID, cambiar estado a DEAD, guardar cambios

        Optional<Character> characterOptional = characterRepository.findById(id);

        if(characterOptional.isPresent()){

            Character characterData =  characterOptional.get();

            characterData.setStatus(CharacterStatus.DEAD);

             return convertToDTO(characterRepository.save(characterData));

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no encontrado");
    }

    private CharacterDTO convertToDTO(Character character) {
        return new CharacterDTO(
                character.getId(),
                character.getName(),
                character.getOccupation(),
                character.getStatus(),
                character.getImageUrl()
        );
    }

    private Character convertToEntity(CreateCharacterDTO dto) {
        Character character = new Character();
        character.setName(dto.getName());
        character.setOccupation(dto.getOccupation());
        character.setStatus(dto.getStatus());
        character.setImageUrl(dto.getImageUrl());
        return character;
    }
}