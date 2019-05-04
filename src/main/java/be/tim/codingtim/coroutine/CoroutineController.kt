package be.tim.codingtim.coroutine

import be.tim.codingtim.common.PersonalisedContent
import be.tim.codingtim.common.PersonalizationService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/content")
class CoroutineController(private val service: PersonalizationService) {

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPersonalisedContent(@RequestHeader username: String): ResponseEntity<PersonalisedContent> {
        val content = service.getContentFor(username)
        return ResponseEntity.ok(content)
    }
}