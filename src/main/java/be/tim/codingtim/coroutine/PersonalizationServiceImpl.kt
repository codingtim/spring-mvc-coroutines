package be.tim.codingtim.coroutine

import be.tim.codingtim.common.PersonalisedContent
import be.tim.codingtim.common.User
import be.tim.codingtim.common.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class PersonalizationServiceImpl(private val repository: UserRepository): NonBlockingPersonalizationService {

    private val logger = LoggerFactory.getLogger(PersonalizationServiceImpl::class.java)

    override suspend fun getContentFor(username: String): PersonalisedContent {
        logger.info("Inside service")
        //https://medium.com/@elizarov/blocking-threads-suspending-coroutines-d33e11bf4761
        val user = withContext(Dispatchers.IO) {
            logger.info("Calling repository")
            val user = repository.getUser(username)
            user
        }
        logger.info("Calculating content")
        return calculateContent(user)
    }

    private fun calculateContent(user: User): PersonalisedContent {
        return PersonalisedContent(user.username)
    }

}