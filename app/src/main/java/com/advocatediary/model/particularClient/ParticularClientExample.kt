package com.advocatediary.model.particularClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ParticularClientExample {
    @SerializedName("response")
    @Expose
    private lateinit var response: ParticularClientResponse

    fun getResponse(): ParticularClientResponse? {
        return response;
    }

    fun setResponse(response: ParticularClientResponse) {
        this.response = response;
    }

}