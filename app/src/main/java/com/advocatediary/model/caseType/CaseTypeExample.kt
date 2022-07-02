package com.advocatediary.model.caseType

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseTypeExample {
    @SerializedName("response")
    @Expose
    private lateinit var response: CaseTypeResponse

    fun getResponse(): CaseTypeResponse {
        return response;
    }

    fun setResponse(response: CaseTypeResponse) {
        this.response = response;
    }
}