package no.torvundconsulting.sms.repository

import no.torvundconsulting.sms.model.Messages
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository {
    fun write(destination: String, messageList: List<Messages>)
}