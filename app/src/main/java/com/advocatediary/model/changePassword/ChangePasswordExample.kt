package com.advocatediary.model.changePassword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePasswordExample {

    @SerializedName("response")
    @Expose
    private lateinit var response: ChangePasswordResponse

    fun getResponse(): ChangePasswordResponse? {
        return response;
    }

    fun setResponse(response: ChangePasswordResponse) {
        this.response = response;
    }
}