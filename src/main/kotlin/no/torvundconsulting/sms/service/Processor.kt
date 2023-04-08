package no.torvundconsulting.sms.service

import no.torvundconsulting.sms.model.FileIOInfo
import no.torvundconsulting.sms.model.Messages
import no.torvundconsulting.sms.model.simpleframework.Smses
import no.torvundconsulting.sms.repository.MessageRepository
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
import org.slf4j.Logger
import org.springframework.stereotype.Service
import java.io.File

@Service
class Processor(
    val messageRepository: MessageRepository,
    val logger: Logger
) {
    fun process(paths: FileIOInfo) {
        val messageList: MutableList<Messages> = mutableListOf()
        paths.fromBackupFiles.forEach {
            messageList.add(read(File(it)))
        }
        write(paths.destinationFolder, messageList)
    }

    fun read(xmlFile: File): Messages {
        logger.info("Reading from ${xmlFile.canonicalPath}")
        val serializer: Serializer = Persister()
        val fromXml = serializer.read(Smses::class.java, xmlFile)
        return Messages.of(fromXml)
    }

    fun write(destination: String, messageList: List<Messages>) {
        logger.info("Writing to $destination")
        messageRepository.write(destination, messageList)
    }
}
