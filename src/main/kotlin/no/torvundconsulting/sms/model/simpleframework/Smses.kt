package no.torvundconsulting.sms.model.simpleframework

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "smses")
class Smses {
    @field:Attribute(name = "count", required = false)
    var count: String? = null

    @field:Attribute(name = "backup_set", required = false)
    var backup_set: String? = null

    @field:Attribute(name = "backup_date", required = false)
    var backup_date: String? = null

    @field:ElementList(inline = true, required = false)
    var sms: List<Sms> = mutableListOf()

    @field:ElementList(inline = true, required = false)
    var mms: List<Mms> = mutableListOf()
}