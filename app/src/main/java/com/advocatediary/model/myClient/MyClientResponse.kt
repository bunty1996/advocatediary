package com.advocatediary.model.myClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyClientResponse {
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("data")
    @Expose
    private var data: MyClientData? = null

    fun getStatus(): String? {
        return status;
    }

    fun setStatus(status: String) {
        this.status = status;
    }

    fun getMessage(): String? {
        return message;
    }

    fun setMessage(message: String) {
        this.message = message;
    }

    fun getData(): MyClientData? {
        return data;
    }

    fun setData(data: MyClientData) {
        this.data = data;
    }

}