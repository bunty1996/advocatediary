package com.advocatediary.model.myClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MyClientExample {
    @SerializedName("response")
    @Expose
    private  var response: MyClientResponse?=null

    fun getResponse(): MyClientResponse? {
        return response
    }

    fun setResponse(response: MyClientResponse) {
        this.response = response
    }
}