package by.it.academy.ishop.integration;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/createTable.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/dropTable.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Integration test for getting user from DB, when all data is valid")
    @WithMockUser
    public void checkResponseFor_GetProduct_WhenProductIsExist() throws Exception {
        Long id = 153L;
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(id.intValue())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Integration test for getting user from DB, when user is anonymous")
    public void checkResponseFor_GetProduct_WhenUserAnonymous() throws Exception {
        Long id = 153L;
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }


}
