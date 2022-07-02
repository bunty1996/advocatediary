package com.advocatediary.model.deleteClients

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteClientResponse {
    @SerializedName("status")
    @Expose
    private lateinit var status: String
    @SerializedName("message")
    @Expose
    private lateinit var message: String

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

}