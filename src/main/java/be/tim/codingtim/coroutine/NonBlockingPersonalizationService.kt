package be.tim.codingtim.coroutine

import be.tim.codingtim.common.PersonalisedContent

interface NonBlockingPersonalizationService {
    suspend fun getContentFor(username: String): PersonalisedContent
}