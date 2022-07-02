package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeExample {
    @SerializedName("response")
    @Expose
    private var response: HomeResponse? = null

    fun getResponse(): HomeResponse? {
        return response
    }

    fun setResponse(response: HomeResponse) {
        this.response = response
    }

}