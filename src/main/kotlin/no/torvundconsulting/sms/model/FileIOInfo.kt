package no.torvundconsulting.sms.model

import com.google.gson.Gson

class FileIOInfo(
    val fromBackupFiles: List<String>,
    val destinationFolder: String
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}