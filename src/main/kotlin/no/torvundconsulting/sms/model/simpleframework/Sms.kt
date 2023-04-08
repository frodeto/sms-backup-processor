package no.torvundconsulting.sms.model.simpleframework

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false, name = "sms")
class Sms {
    @field:Attribute(name = "date", required = false)
    var date: String? = null

    @field:Attribute(name = "protocol", required = false)
    var protocol: String? = null

    @field:Attribute(name = "address", required = false)
    var address: String? = null

    @field:Attribute(name = "type", required = false)
    var type: String? = null

    @field:Attribute(name = "subject", required = false)
    var subject: String? = null

    @field:Attribute(name = "body", required = false)
    var body: String? = null

    @field:Attribute(name = "sc_toa", required = false)
    var sc_toa: String? = null

    @field:Attribute(name = "service_center", required = false)
    var service_center: String? = null

    @field:Attribute(name = "readable_date", required = false)
    var readable_date: String? = null

    @field:Attribute(name = "contact_name", required = false)
    var contact_name: String? = null
}
