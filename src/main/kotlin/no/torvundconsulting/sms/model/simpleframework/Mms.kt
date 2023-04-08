package no.torvundconsulting.sms.model.simpleframework

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "mms")
class Mms {
    @field:Attribute(name = "date", required = false)
    var date: String? = null

    @field:Attribute(name = "creator", required = false)
    var creator: String? = null

    @field:Attribute(name = "address", required = false)
    var address: String? = null

    @field:Attribute(name = "readable_date", required = false)
    var readable_date: String? = null

    @field:Attribute(name = "contact_name", required = false)
    var contact_name: String? = null

    @field:ElementList(name = "parts", required = false)
    lateinit var parts: List<Part>
}
