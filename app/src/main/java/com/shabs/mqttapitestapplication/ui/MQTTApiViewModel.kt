package com.shabs.mqttapitestapplication.ui

import android.view.View
import androidx.lifecycle.ViewModel
import com.shabs.mqttapitestapplication.data.repository.MQTTRepository
import com.shabs.mqttapitestapplication.utils.Coroutines

class MQTTApiViewModel(private val mqttRepository: MQTTRepository) : ViewModel() {
    var user: String? = "atula@innologixconsulting.com"
    var password: String? = "K)2e3mX6y5N&%"
    var url: String? = "https://ma.dazzlerobotics.com/S2S_log_test.php"
    var token: String? = "6r2uby39TGD2V"
    var imei: String? = "868984040435352"
    var rule: String? = "{\"rule\": 1, \"slave_id\": 1, \"operation\": 4, \"address\": 1, \"qty\": 1, \"delay\": 60, \"enabled\": 1}"
    var data: String? = "{\"write\": 1, \"slave_id\": 1, \"operation\": 6, \"address\": 1, \"qty\": 1, \"data\": \"FF00\"}"

    var responseListener: ResponseListener? = null

    fun onClickGetLoginToken(view: View) {
        responseListener?.onStarted()

        if (user.isNullOrEmpty() || password.isNullOrEmpty()) {
            responseListener?.onFailure("Invalid Details")
        }

        Coroutines.main {
            try {
                val response = mqttRepository.getLoginToken(user!!, password!!, url!!)
//                if (response == null) {
//                    responseListener?.onFailure("Something wrong")
//                }
                responseListener?.onSuccess(response)
            } catch (e: Exception) {
                e.printStackTrace()
                responseListener?.onFailure(e.message!!)
            }
        }
    }

    fun onClickSetRule(view: View) {
        responseListener?.onStarted()

        if (user.isNullOrEmpty() || password.isNullOrEmpty()) {
            responseListener?.onFailure("Invalid Details")
        }

        Coroutines.main {
            try {
                val response = mqttRepository.setRule(imei!!, token!!, rule!!)
                if (response == null) {
                    responseListener?.onFailure("Something wrong")
                }
                responseListener?.onSuccess(response)
            } catch (e: Exception) {
                responseListener?.onFailure(e.message!!)
            }
        }
    }


    fun onClickWriteModebus(view: View) {
        responseListener?.onStarted()

        if (user.isNullOrEmpty() || password.isNullOrEmpty()) {
            responseListener?.onFailure("Invalid Details")
        }

        Coroutines.main {
            try {
                val response = mqttRepository.writeToModbus(imei!!, token!!, data!!)
                if (response == null) {
                    responseListener?.onFailure("Something wrong")
                }
                responseListener?.onSuccess(response)
            } catch (e: Exception) {
                responseListener?.onFailure(e.message!!)
            }
        }
    }


    fun onClickEraseRule(view: View) {
        responseListener?.onStarted()

        if (imei.isNullOrEmpty() || token.isNullOrEmpty()) {
            responseListener?.onFailure("Invalid Details")
        }

        Coroutines.main {
            try {
                val response = mqttRepository.eraseRules(imei!!, token!!)
                if (response == null) {
                    responseListener?.onFailure("Something wrong")
                }
                responseListener?.onSuccess(response)
            } catch (e: Exception) {
                responseListener?.onFailure(e.message!!)
            }
        }
    }

    fun onClickRestartDevice(view: View) {
        responseListener?.onStarted()

        if (imei.isNullOrEmpty() || token.isNullOrEmpty()) {
            responseListener?.onFailure("Invalid Details")
        }

        Coroutines.main {
            try {
                val response = mqttRepository.restartDevice(imei!!, token!!)
                if (response == null) {
                    responseListener?.onFailure("Something wrong")
                }
                responseListener?.onSuccess(response)
            } catch (e: Exception) {
                responseListener?.onFailure(e.message!!)
            }
        }
    }
}