package com.example.myapplication.level

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


@Composable
fun LevelScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val levelValue = remember {
        mutableStateOf<Pair<Float,Float>>(Pair(0f, 0f))
    }
    val sensorEventListener = SensorListener(levelValue)



    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when(event) {
                Lifecycle.Event.ON_RESUME -> {
                    sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
                }
                Lifecycle.Event.ON_PAUSE -> {}
                Lifecycle.Event.ON_STOP -> {}
                Lifecycle.Event.ON_DESTROY -> {

                }
                else -> {
                    sensorManager.unregisterListener(sensorEventListener)
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Text(
        text = "x: ${levelValue.value.first} y: ${levelValue.value.second}"
    )

}