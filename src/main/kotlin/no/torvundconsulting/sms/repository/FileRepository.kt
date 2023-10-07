package no.torvundconsulting.sms.repository

import no.torvundconsulting.sms.model.Messages
import no.torvundconsulting.sms.model.Mms
import no.torvundconsulting.sms.model.Sms
import org.slf4j.Logger
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Repository
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Repository
@ConditionalOnProperty(value = ["message.destination"], havingValue = "file", matchIfMissing = true)
class FileRepository(
    val logger: Logger
) : MessageRepository {
    override fun write(destination: String, messageList: List<Messages>) {
        FileUtil.createDirectory(destination)
        FileUtil.createDirectory("$destination/images")
        writeSmsesToCSV(
            destination,
            messageList.associate { Pair(it.backupSet ?: "set-${UUID.randomUUID()}", it.sms) })
        writeMmses(
            "$destination/images",
            messageList.associate { Pair(it.backupSet ?: "set-${UUID.randomUUID()}", it.mms) })
    }

    private fun writeMmses(destination: String, mmsLists: Map<String, List<Mms>>) {
        mmsLists.forEach { (_, value) ->
            value.forEach { mms ->
                mms.images.forEach { (key, value) ->
                    if (value != null) {
                        val file = FileUtil.constructFile("$destination/$key")
                        val absolutePath = FileUtil.absolutePath(file)
                        val bytes = Base64.getDecoder().decode(value)
                        FileUtil.writeBytes(file, bytes)
                        logger.info("Wrote $absolutePath")
                    } else {
                        logger.info("No image data for $key")
                    }
                }
            }
        }
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
