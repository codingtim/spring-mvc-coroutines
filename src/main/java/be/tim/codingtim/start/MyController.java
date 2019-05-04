package be.tim.codingtim.start;

import be.tim.codingtim.common.PersonalisedContent;
import be.tim.codingtim.common.PersonalizationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/content")
public class MyController {

    private PersonalizationService service;

    MyController(PersonalizationService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonalisedContent> getPersonalisedContent(@RequestHeader String username) {
        PersonalisedContent content = service.getContentFor(username);
        return ResponseEntity.ok(content);
    }

}
