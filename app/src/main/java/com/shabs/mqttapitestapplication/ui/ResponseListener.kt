package com.shabs.mqttapitestapplication.ui

import androidx.lifecycle.LiveData

interface ResponseListener {
    fun onStarted()
    fun onSuccess(message: LiveData<String>)
    fun onFailure(message : String)
}