package dev.drzepka.smarthome.common.pvstats.model.vendor.sofar

import dev.drzepka.smarthome.common.pvstats.model.vendor.VendorData
import java.time.Instant

abstract class SofarData(val date: Instant) : VendorData() {
    abstract val energyToday: Int
    abstract val energyTotal: Int
    abstract val currentPower: Int // Watt
    abstract val frequency: Float

    abstract val generationHoursToday: Float
    abstract val generationHoursTotal: Int

    abstract val pv1Voltage: Float
    abstract val pv1Current: Float
    abstract val pv1Power: Int // Watt
    abstract val pv2Voltage: Float
    abstract val pv2Current: Float
    abstract val pv2Power: Int // Watt

    abstract val phaseAVoltage: Float
    abstract val phaseACurrent: Float
    abstract val phaseBVoltage: Float
    abstract val phaseBCurrent: Float
    abstract val phaseCVoltage: Float
    abstract val phaseCCurrent: Float
}