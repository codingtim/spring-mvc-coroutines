package be.tim.codingtim.coroutine


import be.tim.codingtim.common.BlockingRepositoryImpl
import kotlinx.coroutines.*
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class CoroutineControllerTest {

    private val controller = CoroutineController(PersonalizationServiceImpl(BlockingRepositoryImpl()))
    private val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    @Test
    internal fun call() {
        val result = mockMvc.perform(get("/content").header("username", "coroutineController"))
                .andExpect(request().asyncStarted())
                .andReturn()
        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk)
                .andExpect(jsonPath<String>("$.value", `is`<String>("coroutineController")))
    }

    @Test
    internal fun callDirectly() {
        runBlocking {
            //https://medium.com/@elizarov/structured-concurrency-722d765aa952
            //coroutineScope {
                async (Dispatchers.Default) { controller.getContent("test1") }
                async (Dispatchers.Default) { controller.getContent("test2") }
            //}
        }
    }
}