package com.advocatediary.model.myCases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyCasesResponse
{
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("data")
    @Expose
    private var data: MyCasesData? = null

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

    fun getData(): MyCasesData? {
        return data
    }

    fun setData(data: MyCasesData) {
        this.data = data
    }
}