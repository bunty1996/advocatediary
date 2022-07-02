package com.advocatediary.model.register

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterExample {
    @SerializedName("response")
@Expose
private lateinit var response:RegisterResponse ;

    fun  getResponse():RegisterResponse {
        return response;
    }

    fun setResponse(response:RegisterResponse) {
        this.response = response;
    }

}