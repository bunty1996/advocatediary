package com.advocatediary.model.allClients

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllClientExample {
    @SerializedName("response")
    @Expose
    private lateinit var response:AllClientsResponse

    fun  getResponse():AllClientsResponse?
    {
        return response;
    }

    fun setResponse(response:AllClientsResponse)
    {
        this.response = response;
    }

}