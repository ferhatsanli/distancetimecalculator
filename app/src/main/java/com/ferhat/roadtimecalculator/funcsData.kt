package com.ferhat.roadtimecalculator

import android.content.Context
import android.util.Log

class FunctionsData(context: Context) {
    val TAG = "DataFunc"
    private val dbName = "roadTimeData"

    private val keyDist = "distance"
    private val keySpeed = "speed"
    private val sharedPref = context.getSharedPreferences(dbName, Context.MODE_PRIVATE)

    fun saveDistance(distanceValue: Float) {
        sharedPref.edit().putFloat(keyDist, distanceValue).apply()
        Log.i(TAG, "saveDistance: distance saved (value:$distanceValue)")
    }

    fun saveSpeed(speedValue: Float) {
        sharedPref.edit().putFloat(keySpeed, speedValue).apply()
        Log.i(TAG, "saveSpeed: speed saved (value:$speedValue")
    }

    fun getDistance(): Float {
        return sharedPref.getFloat(keyDist, 0.0F)
    }

    fun getSpeed(): Float {
        return sharedPref.getFloat(keySpeed, 0.0F)
    }



}