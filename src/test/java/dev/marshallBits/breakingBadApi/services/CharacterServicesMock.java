package dev.marshallBits.breakingBadApi.services;

import dev.marshallBits.breakingBadApi.dto.CharacterDTO;
import dev.marshallBits.breakingBadApi.models.Character;
import dev.marshallBits.breakingBadApi.models.CharacterStatus;
import dev.marshallBits.breakingBadApi.repositories.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterServicesMock {

    //Mock significa falso o imitación
    //Se creará una imitación del characterRepository
    @Mock
    CharacterRepository characterRepository;

    @InjectMocks
    CharacterServiceImpl characterService;

    Character walter;

    @BeforeEach
    void setUp(){
        walter = Character
                .builder()
                .name("Walter White")
                .status(CharacterStatus.ALIVE)
                .occupation("Teacher")
                .build();
    }

    @Test
    @DisplayName("Obtener a Walter White")
    public void obtenerAWalterById(){

        Long characterId = 1L;
        //Con when se logra finjir que el repository devuelve a walter
        when(characterRepository.findById(characterId)).thenReturn(Optional.of(walter));

        CharacterDTO result = characterService.findById(characterId);
        //System.out.println("Esto es el result: " + result);

        assertEquals("Walter White", result.getName());

        //Con esta línea de código me aseguro de que el service llame al repository
        verify(characterRepository).findById(characterId);

    }

}
