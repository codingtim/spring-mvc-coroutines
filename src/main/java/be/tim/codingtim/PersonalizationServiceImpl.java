package be.tim.codingtim;

public class PersonalizationServiceImpl implements PersonalizationService {

    private UserRepository repository;

    @Override
    public PersonalisedContent getContentFor(String username) {
        User user = repository.getUser(username);
        PersonalisedContent personalisedContent = calculateContent(user);
        return personalisedContent;
    }

    private PersonalisedContent calculateContent(User user) {
        return null;
    }
}
