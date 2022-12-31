package com.shabs.mqttapitestapplication.data.network

import androidx.lifecycle.LiveData
import com.shabs.mqttapitestapplication.data.network.responses.MQTTResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MQTTApi {

    @FormUrlEncoded
    @POST("set_s2s")
    suspend fun getLoginToken(
        @Field("user") user: String,
        @Field("password") password: String,
        @Field("url") url: String
    ): Response<LiveData<String>>

    @FormUrlEncoded
    @POST("set_rule")
    suspend fun setRule(
        @Field("imei") imei: String,
        @Field("token") token: String,
        @Field("rule") rule: String
    ): Response<LiveData<String>>

    @FormUrlEncoded
    @POST("write_modbus")
    suspend fun writeToModbus(
        @Field("imei") imei: String,
        @Field("token") token: String,
        @Field("write") write: String
    ): Response<LiveData<String>>

    @FormUrlEncoded
    @POST("erase_rules")
    suspend fun eraseRules(
        @Field("imei") imei: String,
        @Field("token") token: String
    ): Response<LiveData<String>>

    @FormUrlEncoded
    @POST("restart")
    suspend fun restartDevice(
        @Field("imei") imei: String,
        @Field("token") token: String
    ): Response<LiveData<String>>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MQTTApi {
            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://ma.dazzlerobotics.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MQTTApi::class.java)
        }
    }
}