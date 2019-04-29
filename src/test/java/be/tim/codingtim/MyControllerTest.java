package be.tim.codingtim;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MyControllerTest {

    private MyController myController = new MyController(new DummyPersonalizationService());
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();

    @Test
    void getPersonalisedContent() throws Exception {
        mockMvc.perform(get("/content").header("username", "tim"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", is("x")));
    }

    private static class DummyPersonalizationService implements PersonalizationService {

        @Override
        public PersonalisedContent getContentFor(String username) {
            return new PersonalisedContent("x");
        }
    }
}