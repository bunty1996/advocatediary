package com.advocatediary.model.login

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("status")
    var status:String? = null
    @SerializedName("message")
    var message:String? = null
    @SerializedName("data")
    var data : LoginData?=null
}