package be.tim.codingtim.start;

import be.tim.codingtim.common.PersonalisedContent;
import be.tim.codingtim.common.PersonalizationService;
import be.tim.codingtim.common.User;
import be.tim.codingtim.common.UserRepository;

public class PersonalizationServiceImpl implements PersonalizationService {

    private UserRepository repository;

    public PersonalizationServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonalisedContent getContentFor(String username) {
        User user = repository.getUser(username);
        PersonalisedContent personalisedContent = calculateContent(user);
        return personalisedContent;
    }

    private PersonalisedContent calculateContent(User user) {
        return new PersonalisedContent(user.getUsername());
    }
}
