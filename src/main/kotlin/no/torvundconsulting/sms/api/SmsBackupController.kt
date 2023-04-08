package no.torvundconsulting.sms.api

import no.torvundconsulting.sms.model.FileIOInfo
import no.torvundconsulting.sms.service.Processor
import org.slf4j.Logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("processbackup")
class SmsBackupController(
    val processor: Processor,
    val logger: Logger
) {

    @PostMapping
    fun process(@RequestBody userInputDto: FileIOInfo): ProcessingStatus {
        logger.info("received request $userInputDto")

        return ProcessingStatus("OK")
    }
}

class ProcessingStatus(
    val status: String
)
