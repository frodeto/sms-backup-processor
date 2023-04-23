package no.torvundconsulting.sms.repository

import no.torvundconsulting.sms.model.Messages
import no.torvundconsulting.sms.model.Sms
import org.slf4j.Logger
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Repository

@Repository
@ConditionalOnProperty(value = ["message.destination"], havingValue = "test")
class MockRepository(
    val logger: Logger
): MessageRepository {
    override fun write(destination: String, messageList: List<Messages>) {
        val smsLists = messageList.map { it.sms }
        smsLists.forEach { logIt(it) }

    }

    private fun logIt(l: List<Sms>) = l.forEach { logger.info(it.toString()) }

}