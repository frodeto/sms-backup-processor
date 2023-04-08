package no.torvundconsulting.sms.service

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import no.torvundconsulting.sms.model.FileIOInfo
import no.torvundconsulting.sms.repository.MessageRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import java.io.File

@ExtendWith(MockKExtension::class)
internal class ProcessorTest {
    @InjectMockKs
    lateinit var processor: Processor

    @RelaxedMockK
    lateinit var messageRepository: MessageRepository

    @RelaxedMockK
    lateinit var logger: Logger

    @Test
    fun testReader() {
        processor.read(File("./src/test/resources/test-smses.xml"))
    }

    @Test
    fun testProcess() {
        val fileIOInfo = FileIOInfo(
            listOf("/Users/frodetorvund/Dropbox/Backup/sms/sms_20130203114812.xml"),
            "./tmp"
        )
        processor.process(fileIOInfo)
    }
}