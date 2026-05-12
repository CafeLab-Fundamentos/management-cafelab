package com.acme.management.cafelab.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pe.managementcafelab.ManagementCafeLabApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ManagementCafeLabApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class CoffeeLotsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCoffeeLotShouldReturnCreated() throws Exception {

        Map<String, Object> body = new HashMap<>();

        body.put("supplierId", 1);
        body.put("userId", 1);
        body.put("lotName", "Lote Premium");
        body.put("coffeeType", "Arábica");
        body.put("origin", "Cusco");

        // AQUÍ
        body.put("status", "Disponible");

        body.put("altitudeMeters", 1500);
        body.put("processingMethod", "Lavado");
        body.put("certifications", List.of());
        body.put("initialWeight", 20.0);

        mockMvc.perform(post("/api/v1/coffee-lots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllCoffeeLotsShouldReturnOk() throws Exception {

        mockMvc.perform(get("/api/v1/coffee-lots"))
                .andExpect(status().isOk());
    }
}