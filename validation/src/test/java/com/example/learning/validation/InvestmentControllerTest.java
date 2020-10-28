package com.example.learning.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("LOCAL") //z tym profilem uruchomi mi się test ("LOCAL")
@AutoConfigureMockMvc //moge uruchamiac metody http do moich metod kontrolera
    //symuluje klienta
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
// żeby moja klasa nie przechowywała stanu przed każdym testem (czyści stan)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
  //automatycznie konfiguruje baze danych
public class InvestmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldStatusBe200() throws Exception {
        mockMvc.perform(get("/api/investments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldStatusBe201AndProperContentWhenSendingProperData() throws Exception {
        Investment investment = new Investment();
        investment.setEmail("email@gmail.com");
        investment.setInitialCapital(new BigDecimal(1000));
        investment.setDuration(20L);
        investment.setReturnRate(new BigDecimal(3));
        mockMvc.perform(post("/api/investments")
                .content(objectMapper.writeValueAsString(investment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("email@gmail.com"))
                .andExpect(jsonPath("$.initialCapital").value(1000))
                .andExpect(jsonPath("$.duration").value(20))
                .andExpect(jsonPath("$.returnRate").value(3));
    }
}
