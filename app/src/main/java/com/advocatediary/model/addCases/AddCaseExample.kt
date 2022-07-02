package com.advocatediary.model.addCases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddCaseExample {
    @SerializedName("response")
    @Expose
    private lateinit var response: AddCaseResponse

    fun getResponse(): AddCaseResponse? {
        return response;
    }

    fun setResponse(response: AddCaseResponse) {
        this.response = response;
    }
}