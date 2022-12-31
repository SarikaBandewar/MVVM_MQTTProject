package com.shabs.mqttapitestapplication.data


data class MQTTUserEntity (
    var user: String? = null,
    var password: String? = null,
    var url: String? = null,
    var token: String? = null,
    var imei: String? = null
) {

}