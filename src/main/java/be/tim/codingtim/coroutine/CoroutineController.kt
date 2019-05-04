package be.tim.codingtim.coroutine

import be.tim.codingtim.common.PersonalisedContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.util.concurrent.CompletableFuture

@Controller
@RequestMapping("/content")
class CoroutineController(private val service: NonBlockingPersonalizationService) {

    private val logger = LoggerFactory.getLogger(CoroutineController::class.java)

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPersonalisedContent(@RequestHeader username: String): CompletableFuture<ResponseEntity<PersonalisedContent>> {
        logger.info("Before launch")
        return GlobalScope.async(Dispatchers.Default) {
            getContent(username)
        }.asCompletableFuture()
    }

    suspend fun getContent(username: String): ResponseEntity<PersonalisedContent> {
        logger.info("Launched")
        val content = service.getContentFor(username)
        logger.info("Returning result in controller")
        return ResponseEntity.ok(content)
    }
}