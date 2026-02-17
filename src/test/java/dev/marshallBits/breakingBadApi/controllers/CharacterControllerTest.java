package dev.marshallBits.breakingBadApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marshallBits.breakingBadApi.dto.CharacterDTO;
import dev.marshallBits.breakingBadApi.dto.CreateCharacterDTO;
import dev.marshallBits.breakingBadApi.models.CharacterStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterControllerTest {
//MockMVC(Modelo-Vista-Controlador)
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Recibimos 10 characters en /api/characters")
     void getAllCharacrters() throws Exception {
        mockMvc.perform(get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10));
    }


    @Test
    @DisplayName("POST un nuevo character funciona correcatamente")
    void postNewCharacter() throws Exception{

        CreateCharacterDTO saul = CreateCharacterDTO
                .builder()
                .name("Saul")
                .occupation("Lawyer")
                .status(CharacterStatus.ALIVE)
                .build();

        mockMvc.perform(post("/api/characters").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(saul)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Saul"));
    }

}
