package dev.drzepka.smarthome.common.pvstats.model

import dev.drzepka.smarthome.common.pvstats.model.vendor.DeviceType

class PutDataRequest {
    var type: DeviceType? = null
    var data: Any? = null
}