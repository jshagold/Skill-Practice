package com.example.myapplication.level

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableState
import kotlin.math.sqrt

class SensorListener(var levelValue: MutableState<Pair<Float,Float>>) : SensorEventListener {
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if(it.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val pair = Pair(
                    it.values[0],
                    it.values[1]
                )

                levelValue.value = pair
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {


    }
}