package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DateConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDate_validDate() throws Exception {
        mockMvc.perform(get("/students/date-string/2024-04-30")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$", startsWith("2024-04-")));  // Adjust the expected format if necessary
    }

    @Test
    public void testConvertStringToNumber_ValidInput() throws Exception {
        mockMvc.perform(get("/students/convert-string-to-number/0010123.890"))
                .andExpect(status().isOk());
               // .andExpect(content().string("10123"));
    }
}
