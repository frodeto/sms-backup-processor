package no.torvundconsulting.sms.model.simpleframework

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false, name = "part")
class Part {
    @field:Attribute(name = "seq", required = false)
    var seq: String? = null

    @field:Attribute(name = "name", required = false)
    var name: String? = null

    @field:Attribute(name = "ct", required = false)
    var ct: String? = null

    @field:Attribute(name = "fn", required = false)
    var fn: String? = null

    @field:Attribute(name = "cl", required = false)
    var cl: String? = null

    @field:Attribute(name = "text", required = false)
    var text: String? = null

    @field:Attribute(name = "data", required = false)
    var data: String? = null
}