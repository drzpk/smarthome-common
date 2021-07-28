package dev.drzepka.smarthome.common.pvstats.model.vendor.sofar

import java.time.Instant

@Suppress("unused")
class MockSofarData(date: Instant = Instant.now()) : SofarData(date) {
    override var energyToday: Int = 0
    override var energyTotal: Int = 0
    override var currentPower: Int = 0
    override var frequency: Float = 0f

    override var generationHoursToday: Float = 0f
    override var generationHoursTotal: Int = 0

    override var pv1Voltage: Float = 0f
    override var pv1Current: Float = 0f
    override var pv1Power: Int = 0
    override var pv2Voltage: Float = 0f
    override var pv2Current: Float = 0f
    override var pv2Power: Int = 0

    override var phaseAVoltage: Float = 0f
    override var phaseACurrent: Float = 0f
    override var phaseBVoltage: Float = 0f
    override var phaseBCurrent: Float = 0f
    override var phaseCVoltage: Float = 0f
    override var phaseCCurrent: Float = 0f

    override fun serialize(): Any {
        throw UnsupportedOperationException("Cannot serialize mock")
    }
}