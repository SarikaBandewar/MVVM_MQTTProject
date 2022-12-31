package com.shabs.mqttapitestapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shabs.mqttapitestapplication.data.network.MQTTApi
import com.shabs.mqttapitestapplication.data.network.SafeApiRequest
import com.shabs.mqttapitestapplication.data.network.responses.MQTTResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MQTTRepository(
    private val mqttApi: MQTTApi
) : SafeApiRequest() {

    suspend fun getLoginToken(user: String, password: String, url: String): LiveData<String> {
        return apiRequest { mqttApi.getLoginToken(user, password, url) }
    }

//    suspend fun getLoginToken(user: String, password: String, url: String): LiveData<String> {
//        val loginResponse = MutableLiveData<String>()
//        mqttApi.getLoginToken(user, password, url)
//            .enqueue(object : Callback<ResponseBody> {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    println("call : $call" )
//                    println("response : $response")
//                    if (response.isSuccessful) {
//                        loginResponse.value = response.body()?.toString()
//                    } else {
//                        loginResponse.value = response.errorBody()?.toString()
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    println("onfailure call : $call" )
//                    loginResponse.value = t.message
//                }
//            })
//        return loginResponse
//    }

    suspend fun setRule(imei: String, token: String, rule: String): LiveData<String> {
        return apiRequest { mqttApi.setRule(imei, token, rule) }
    }

    suspend fun writeToModbus(imei: String, token: String, data: String): LiveData<String> {
        return apiRequest { mqttApi.writeToModbus(imei, token, data) }
    }

    suspend fun eraseRules(imei: String, token: String): LiveData<String> {
        return apiRequest { mqttApi.eraseRules(imei, token) }
    }

    suspend fun restartDevice(imei: String, token: String): LiveData<String> {
        return apiRequest { mqttApi.restartDevice(imei, token) }
    }
}