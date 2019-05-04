package be.tim.codingtim.coroutine

import be.tim.codingtim.common.PersonalisedContent
import be.tim.codingtim.common.PersonalizationService
import be.tim.codingtim.common.User
import be.tim.codingtim.common.UserRepository

class PersonalizationServiceImpl(private val repository: UserRepository): PersonalizationService {

    override fun getContentFor(username: String): PersonalisedContent {
        val user = repository.getUser(username)
        return calculateContent(user)
    }

    private fun calculateContent(user: User): PersonalisedContent {
        return PersonalisedContent(user.username)
    }

}