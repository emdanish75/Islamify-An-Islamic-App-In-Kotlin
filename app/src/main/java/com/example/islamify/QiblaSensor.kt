package com.example.islamify

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class QiblaSensor(context: Context) : SensorEventListener {
    private var sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private var magnetometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private var gravity = FloatArray(3)
    private var geomagnetic = FloatArray(3)
    private var azimuth = 0f

    var qiblaDirection = mutableStateOf(0f)

    var latitude: Double = 0.0
    var longitude: Double = 0.0

    init {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI)
    }

    fun updateLocation(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
println({latitude})
        recalculateQiblaDirection()
    }

    private fun recalculateQiblaDirection() {
        val qiblaAzimuth = calculateQiblaDirection(latitude, longitude)
        qiblaDirection.value = (qiblaAzimuth - azimuth).toFloat()
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> gravity = event.values.clone()
            Sensor.TYPE_MAGNETIC_FIELD -> geomagnetic = event.values.clone()
        }

        val R = FloatArray(9)
        val I = FloatArray(9)

        if (SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)) {
            val orientation = FloatArray(3)
            SensorManager.getOrientation(R, orientation)
            azimuth = Math.toDegrees(orientation[0].toDouble()).toFloat()

            // Normalize azimuth to range [0, 360)
            val adjustedAzimuth = (azimuth + 360) % 360

            val qiblaAzimuth = calculateQiblaDirection(latitude, longitude)

            // Calculate the direction to Qibla
            val directionToQibla = (qiblaAzimuth - adjustedAzimuth + 360) % 360

            // Update the qiblaDirection state
            qiblaDirection.value = directionToQibla.toFloat()
        }
    }


    private fun calculateQiblaDirection(lat: Double, lon: Double): Double {
        val kaabaLat = Math.toRadians(21.4225)
        val kaabaLon = Math.toRadians(39.8262)
        val userLat = Math.toRadians(lat)
        val userLon = Math.toRadians(lon)

        val dLon = kaabaLon - userLon
        val y = sin(dLon) * cos(kaabaLat)
        val x = cos(userLat) * sin(kaabaLat) - sin(userLat) * cos(kaabaLat) * cos(dLon)
        val brng = atan2(y, x)
        return (Math.toDegrees(brng) + 360) % 360 // Ensure the angle is in the range [0, 360)
    }


    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    fun unregister() {
        sensorManager.unregisterListener(this)
    }
}
