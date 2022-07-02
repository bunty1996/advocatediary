package com.advocatediary.model.userExists

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserExistsExample {
    @SerializedName("response")
    @Expose
    private lateinit var response: UserExistsResponse;

    fun getResponse(): UserExistsResponse {
        return response;
    }

    fun setResponse(response: UserExistsResponse) {
        this.response = response;
    }
}