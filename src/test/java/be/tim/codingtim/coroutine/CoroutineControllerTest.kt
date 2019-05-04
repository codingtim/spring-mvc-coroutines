package be.tim.codingtim.coroutine


import be.tim.codingtim.common.BlockingRepositoryImpl
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class CoroutineControllerTest {

    private val myController = CoroutineController(PersonalizationServiceImpl(BlockingRepositoryImpl()))
    private val mockMvc = MockMvcBuilders.standaloneSetup(myController).build()

    @Test
    internal fun call() {
        mockMvc.perform(get("/content").header("username", "blockingController"))
                .andExpect(status().isOk())
                .andExpect(jsonPath<String>("$.value", `is`<String>("blockingController")))
    }
}