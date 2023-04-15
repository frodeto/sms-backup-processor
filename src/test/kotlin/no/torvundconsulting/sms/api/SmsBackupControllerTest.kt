package no.torvundconsulting.sms.api

import com.google.gson.Gson
import no.torvundconsulting.sms.model.FileIOInfo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SmsBackupControllerTest {

    @Test
    fun testSerializingPayload() {
        println(
            Gson().toJson(
                FileIOInfo(
                    listOf(
                        "test1.xml",
                        "test2..xml"
                    ),
                    "./tmp"
                )
            )
        )
    }
}