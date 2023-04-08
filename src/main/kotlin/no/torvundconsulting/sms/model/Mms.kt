package no.torvundconsulting.sms.model

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class Mms(
    val date: LocalDateTime? = null,
    val readable_date: String? = null,
    val creator: String? = null,
    val address: String? = null,
    val contact_name: String? = null,
    val texts: List<String?>,
    val images: Map<String, ByteArray?>,
) {
    companion object {
        fun of(mmses: List<no.torvundconsulting.sms.model.simpleframework.Mms>) = mmses.map { of(it) }

        fun of(mms: no.torvundconsulting.sms.model.simpleframework.Mms): Mms {
            return Mms(
                getDate(mms),
                mms.readable_date,
                mms.creator,
                mms.address,
                mms.contact_name,
                getTexts(mms),
                getImages(mms)
            )
        }

        private fun getImages(mms: no.torvundconsulting.sms.model.simpleframework.Mms): Map<String, ByteArray?> {
            return mms.parts.filter { it.ct == "image/jpeg" }.map { it.name!! to it.data?.toByteArray() }.toMap()
        }

        private fun getTexts(mms: no.torvundconsulting.sms.model.simpleframework.Mms): List<String?> {
            return mms.parts.filter { it.ct == "text/plain" }.map { it.text }.toList()
        }

        private fun getDate(mms: no.torvundconsulting.sms.model.simpleframework.Mms): LocalDateTime? {
            return mms.date?.let { LocalDateTime.ofInstant(
                Instant.ofEpochMilli(it.toLong()),
                ZoneId.systemDefault()
            ) }
        }
    }
}
