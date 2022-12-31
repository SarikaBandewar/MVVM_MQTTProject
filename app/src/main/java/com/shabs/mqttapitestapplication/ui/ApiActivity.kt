package com.shabs.mqttapitestapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shabs.mqttapitestapplication.R
import com.shabs.mqttapitestapplication.databinding.ActivityApiBinding
import com.shabs.mqttapitestapplication.utils.snackbar
import kotlinx.android.synthetic.main.activity_api.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ApiActivity : AppCompatActivity(), KodeinAware, ResponseListener {
    override val kodein by kodein()
    private val factory: MQTTApiViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityApiBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_api)
        val viewModel = ViewModelProviders.of(this, factory).get(MQTTApiViewModel::class.java)
        binding.apiTestModel = viewModel

        viewModel.responseListener = this

    }

    override fun onStarted() {
        llRoot.snackbar("Loading !!")
    }

    override fun onSuccess(message: LiveData<String>) {
        message.observe(this,  Observer{
            llRoot.snackbar(it)
        })
    }

    override fun onFailure(message: String) {
        llRoot.snackbar(message)
    }
}