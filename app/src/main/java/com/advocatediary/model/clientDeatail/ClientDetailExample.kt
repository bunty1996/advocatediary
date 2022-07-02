package com.advocatediary.model.clientDeatail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClientDetailExample {

    @SerializedName("response")
    @Expose
    private lateinit var responseResponse:ClientDetailResponse

    fun  getResponse():ClientDetailResponse? {
        return responseResponse;
    }

    fun setResponse(response:ClientDetailResponse) {
        this.responseResponse = response;
    }

}