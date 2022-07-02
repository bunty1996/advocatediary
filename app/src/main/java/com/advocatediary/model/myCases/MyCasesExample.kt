package com.advocatediary.model.myCases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyCasesExample {
    @SerializedName("response")
    @Expose
    private var response: MyCasesResponse? = null

    fun getResponse(): MyCasesResponse? {
        return response
    }

    fun setResponse(response: MyCasesResponse) {
        this.response = response
    }
}