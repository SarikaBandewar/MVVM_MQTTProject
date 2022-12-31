package com.shabs.mqttapitestapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shabs.mqttapitestapplication.data.repository.MQTTRepository

@Suppress("UNCHECKED_CAST")
class MQTTApiViewModelFactory(
    private val mqttRepository: MQTTRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MQTTApiViewModel(mqttRepository) as T
    }
}