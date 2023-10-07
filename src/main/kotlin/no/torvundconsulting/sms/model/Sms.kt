package no.torvundconsulting.sms.model

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Suppress("unused", "PropertyName", "MemberVisibilityCanBePrivate")
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
): Comparable<Sms> {

    override fun compareTo(other: Sms): Int = when {
        this.contact_name != other.contact_name -> compareValues(this.contact_name, other.contact_name)
        this.date != other.date -> compareValues(this.date, other.date)
        else -> 0
    }

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
                sms.contact_name ?: sms.name
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

    fun toCSV(): String {
        return "$contact_name;$body;$date;$address"
    }
}
