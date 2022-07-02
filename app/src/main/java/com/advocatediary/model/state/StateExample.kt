package com.advocatediary.model.state

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class StateExample {

    @SerializedName("response")
    @Expose
    var response: StateResponse? = null

    fun getStateResponse(): StateResponse?
    {
        return response;
    }
    fun setStateResponse(response:StateResponse)
    {
        this.response=response
    }
}