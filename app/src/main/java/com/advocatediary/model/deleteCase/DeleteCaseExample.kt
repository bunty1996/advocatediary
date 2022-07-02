package com.advocatediary.model.deleteCase

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteCaseExample {
    @SerializedName("response")
    @Expose
    private lateinit var response:DeleteCaseResponse

    fun  getResponse():DeleteCaseResponse? {
        return response;
    }

    fun setResponse(response:DeleteCaseResponse) {
        this.response = response;
    }

}