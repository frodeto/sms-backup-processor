package no.torvundconsulting.sms.service

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import no.torvundconsulting.sms.model.FileIOInfo
import no.torvundconsulting.sms.model.Messages
import no.torvundconsulting.sms.model.Mms
import no.torvundconsulting.sms.model.Sms
import no.torvundconsulting.sms.repository.MessageRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import java.io.File
import java.time.LocalDateTime

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
        val messages = processor.read(File(TEST_FILE))
        assertEquals(2, messages.sms.size)
        assertEquals(2, messages.mms.size)
    }

    @Test
    fun testWriter() {
        processor.write("test", createMockMessageList())
        verify(exactly = 1) { messageRepository.write(any(), any()) }
    }

    @Test
    fun testProcess() {
        val fileIOInfo = FileIOInfo(
            listOf("./src/test/resources/test-smses.xml"),
            "./tmp"
        )
        processor.process(fileIOInfo)
    }

    companion object {
        private const val TEST_FILE = "./src/test/resources/test-smses.xml"

        fun createMockMessageList(): List<Messages> {
            val messages = mutableListOf<Messages>()
            messages.add(Messages("4", "test", LocalDateTime.now(), createMockSmses(), createMockMmses()))
            return messages
        }

        private fun createMockMmses(): List<Mms> {
            return listOf(
                Mms(
                    texts = listOf(),
                    images = mapOf(
                        Pair("test 1", "/9j/4AAQSkZJRgABAQAASABIAAD/4QBARXhpZgAATU0AKgAAAAgAAYdpAAQAAA")
                    )
                ),
                Mms(
                    texts = listOf(),
                    images = mapOf(
                        Pair("test 1", "/9j/4AAQSkZJRgABAQAASABIAAD/4QBARXhpZgAATU0AKgAAAAgAAYdpAAQAAA")
                    )
                )
            )
        }

        private fun createMockSmses(): List<Sms> {
            return listOf(
                Sms(
                    body = "test 1"
                ),
                Sms(
                    body = "test 2"
                )
            )
        }
    }
}