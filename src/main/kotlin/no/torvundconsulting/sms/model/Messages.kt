package no.torvundconsulting.sms.model

import no.torvundconsulting.sms.model.simpleframework.Smses
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class Messages(
    val count: String? = null,
    val backup_set: String? = null,
    val backup_date: LocalDateTime? = null,
    val sms: List<Sms>,
    val mms: List<Mms>
) {
    companion object {
        fun of(messages: Smses): Messages {
            return Messages(
                messages.count,
                messages.backup_set,
                messages.backup_date?.let { LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(it.toLong()),
                    ZoneId.systemDefault()
                ) },
                Sms.of(messages.sms),
                Mms.of(messages.mms)
            )
        }
    }
}
