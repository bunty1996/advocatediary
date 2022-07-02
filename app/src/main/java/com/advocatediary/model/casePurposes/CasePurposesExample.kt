package com.advocatediary.model.casePurposes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class CasePurposesExample {
    @SerializedName("response")
    @Expose
    private var response: CasePurposeResponse? = null

    fun getResponse(): CasePurposeResponse? {
        return response
    }

    fun setResponse(response: CasePurposeResponse) {
        this.response = response
    }
}