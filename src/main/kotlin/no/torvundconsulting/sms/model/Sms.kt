package no.torvundconsulting.sms.model

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class Sms(
    val date: LocalDateTime? = null,
    val protocol: String? = null,
    val address: String? = null,
    val type: String? = null,
    val subject: String? = null,
    val body: String? = null,
    val sc_toa: String? = null,
    val service_center: String? = null,
    val readable_date: String? = null,
    val contact_name: String? = null
) {

    companion object {
        fun of(smses: List<no.torvundconsulting.sms.model.simpleframework.Sms>) = smses.map { of(it) }

        fun of(sms: no.torvundconsulting.sms.model.simpleframework.Sms): Sms {
            return Sms(
                getDate(sms),
                sms.protocol,
                sms.address,
                sms.type,
                sms.subject,
                sms.body,
                sms.sc_toa,
                sms.service_center,
                sms.readable_date,
                sms.contact_name
            )
        }

        private fun getDate(sms: no.torvundconsulting.sms.model.simpleframework.Sms): LocalDateTime? {
            return sms.date?.let { LocalDateTime.ofInstant(
                Instant.ofEpochMilli(it.toLong()),
                ZoneId.systemDefault()
            ) }
        }
    }

    override fun toString(): String {
        return "Sms(date=$date, address=$address, body=$body, contact_name=$contact_name)"
    }
}