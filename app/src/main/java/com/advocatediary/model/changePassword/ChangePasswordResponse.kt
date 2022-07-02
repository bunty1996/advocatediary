package com.advocatediary.model.changePassword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePasswordResponse {

    @SerializedName("status")
    @Expose
    private lateinit var status:String
    @SerializedName("message")
    @Expose
    private lateinit var message:String
    @SerializedName("data")
    @Expose
    private lateinit var data:ChangePasswordData

    fun  getStatus():String? {
        return status;
    }

    fun setStatus(status:String) {
        this.status = status;
    }

    fun  getMessage():String? {
        return message;
    }

    fun setMessage(message:String) {
        this.message = message;
    }

    fun  getData():ChangePasswordData? {
        return data;
    }

    fun setData(data:ChangePasswordData) {
        this.data = data;
    }

}