package com.advocatediary.model.deleteClients

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteClientsExample {

    @SerializedName("response")
    @Expose
    private lateinit var response: DeleteClientResponse

    fun getResponse(): DeleteClientResponse {
        return response;
    }

    fun setResponse(response: DeleteClientResponse) {
        this.response = response;
    }

}