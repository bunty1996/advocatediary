package com.advocatediary.model.state

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class StateResponse {
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("data")
    @Expose
    private var data: List<StateDatum>? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getData(): List<StateDatum>? {
        return data
    }

    fun setData(data: List<StateDatum>) {
        this.data = data
    }
}