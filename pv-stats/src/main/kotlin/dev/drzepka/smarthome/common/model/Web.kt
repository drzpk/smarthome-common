package dev.drzepka.smarthome.common.model

import dev.drzepka.smarthome.common.model.vendor.DeviceType

class PutDataRequest {
    var type: DeviceType? = null
    var data: Any? = null
}