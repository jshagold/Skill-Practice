package com.example.myapplication.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.compose.runtime.MutableDoubleState
import kotlin.math.sqrt

class SensorListener(var value: MutableDoubleState) : SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if(it.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                val magX: Double = it.values[0].toDouble()
                val magY: Double = it.values[1].toDouble()
                val magZ: Double = it.values[2].toDouble()

                value.value = sqrt((magX * magX) + (magY * magY) + (magZ * magZ))
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}