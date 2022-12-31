package com.shabs.mqttapitestapplication

import android.app.Application
import com.shabs.mqttapitestapplication.data.network.MQTTApi
import com.shabs.mqttapitestapplication.data.network.NetworkConnectionInterceptor
import com.shabs.mqttapitestapplication.data.repository.MQTTRepository
import com.shabs.mqttapitestapplication.ui.MQTTApiViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MQTTApiApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MQTTApiApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MQTTApi(instance()) }
        bind() from singleton { MQTTRepository(instance()) }
        bind() from provider { MQTTApiViewModelFactory(instance()) }
    }
}