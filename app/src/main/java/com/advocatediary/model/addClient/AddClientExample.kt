package com.advocatediary.model.addClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddClientExample {@SerializedName("response")
@Expose
private lateinit var response:AddClientResponse

    fun  getResponse():AddClientResponse? {
        return response;
    }

    fun setResponse(response:AddClientResponse) {
        this.response = response;
    }

}