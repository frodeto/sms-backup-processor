package no.torvundconsulting.sms.repository

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
import io.mockk.verify
import no.torvundconsulting.sms.service.ProcessorTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import java.nio.file.Path

@ExtendWith(MockKExtension::class)
internal class FileRepositoryTest {
    @InjectMockKs
    lateinit var fileRepository: FileRepository

    @RelaxedMockK
    lateinit var logger: Logger

    @Test
    fun write() {
        mockkObject(FileRepository.FileUtil)
        every { FileRepository.FileUtil.writeBytes(any(), any()) } returns Unit
        every { FileRepository.FileUtil.writeText(any(), any()) } returns Unit
        every { FileRepository.FileUtil.createDirectory(any()) } returns Path.of("test")
        every { FileRepository.FileUtil.constructFile(any()) } returns null
        every { FileRepository.FileUtil.absolutePath(any()) } returns "test"

        val messageList = ProcessorTest.createMockMessageList()
        fileRepository.write("test", messageList)

        verify(exactly = 1) { FileRepository.FileUtil.writeText(any(), any()) }

    }
}
