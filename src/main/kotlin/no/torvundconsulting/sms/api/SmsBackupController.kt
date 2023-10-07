package no.torvundconsulting.sms.api

import no.torvundconsulting.sms.model.FileIOInfo
import no.torvundconsulting.sms.service.Processor
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.FileNotFoundException

@RestController
@RequestMapping("processbackup")
class SmsBackupController(
    val processor: Processor,
    val logger: Logger
) {
    @PostMapping
    fun process(@RequestBody userInputDto: FileIOInfo): ResponseEntity<ProcessingStatus> {
        logger.info("received request $userInputDto")
        val count = try {
            processor.process(userInputDto)
        } catch (e: FileNotFoundException) {
            logger.error("File not found: ${e.message}")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProcessingStatus("Failed", 0, "Error message: ${e.message}"))
        }
        return ResponseEntity.status(HttpStatus.OK).body(ProcessingStatus("Processed", count))
    }
}

@Suppress("unused")
class ProcessingStatus(
    val status: String,
    val count: Int,
    val error: String? = null
)
