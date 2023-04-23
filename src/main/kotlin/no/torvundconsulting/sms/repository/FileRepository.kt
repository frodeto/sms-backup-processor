package no.torvundconsulting.sms.repository

import no.torvundconsulting.sms.model.Messages
import no.torvundconsulting.sms.model.Sms
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Repository
import java.io.File
import java.util.*
import org.slf4j.Logger
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Repository
@ConditionalOnProperty(value = ["message.destination"], havingValue = "file", matchIfMissing = true)
class FileRepository(
    val logger: Logger
) : MessageRepository {
    override fun write(destination: String, messageList: List<Messages>) {
        FileUtil.createDirectory(destination)
        writeSmsesToCSV(
            destination,
            messageList.associate { Pair(it.backup_set ?: "set-${UUID.randomUUID()}", it.sms) })
    }

    internal fun writeSmsesToCSV(destination: String, smsLists: Map<String, List<Sms>>) {
        smsLists.forEach { (key, value) ->
            val filename = "$key.csv"
            logger.info("Writing $filename to $destination")
            val file = FileUtil.constructFile("$destination/$filename")
            val absolutePath = FileUtil.absolutePath(file)
            val csv = value.joinToString("\n}") { it.toCSV() }
            FileUtil.writeText(file, csv)
            logger.info("Wrote $absolutePath")

        }
    }

    @Suppress("RedundantNullableReturnType")
    object FileUtil {
        fun constructFile(pathName: String): File? {
            return File(pathName)
        }

        fun absolutePath(file: File?): String = file!!.absolutePath

        fun createDirectory(destinationDir: String): Path? = Files.createDirectories(Paths.get(destinationDir))

        fun writeBytes(file: File?, bytes: ByteArray) = file!!.writeBytes(bytes)

        fun writeText(file: File?, text: String) = file!!.writeText(text)
    }
}