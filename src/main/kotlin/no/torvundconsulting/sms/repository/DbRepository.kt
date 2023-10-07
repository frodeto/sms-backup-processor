package no.torvundconsulting.sms.repository

import no.torvundconsulting.sms.model.Messages
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
@ConditionalOnProperty(value = ["message.destination"], havingValue = "db")
class DbRepository: MessageRepository {
    override fun write(destination: String, messageList: List<Messages>) {
        TODO("Not yet implemented")
    }
}
