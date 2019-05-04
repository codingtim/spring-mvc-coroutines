package be.tim.codingtim.start;

import be.tim.codingtim.common.BlockingRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MyControllerTest {

    private MyController myController = new MyController(new PersonalizationServiceImpl(new BlockingRepositoryImpl()));
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(myController).build();

    @Test
    void getPersonalisedContent() throws Exception {
        mockMvc.perform(get("/content").header("username", "blockingController"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", is("blockingController")));
    }

}