package be.tim.codingtim.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockingRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockingRepositoryImpl.class);

    @Override
    public User getUser(String username) {
        try {
            LOGGER.info("Repository called");
            Thread.sleep(1000);
            LOGGER.info("Repository returning");
            return new User(username);
        } catch (InterruptedException e) {
            return new User("error");
        }
    }
}
