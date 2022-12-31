package com.shabs.mqttapitestapplication.data.network

import com.shabs.mqttapitestapplication.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
//        return response as T
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                }
                message.append("\n")
            }
            message.append("Error code: ${response.code()}")
            throw ApiException(message.toString())
        }

    }
}