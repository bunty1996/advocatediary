package com.advocatediary.model.userExists

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class UserExistsResponse {
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null

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
}