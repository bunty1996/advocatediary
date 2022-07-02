package com.advocatediary.model.resetPassword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResetPasswordExample {@SerializedName("response")
@Expose
private lateinit var  response:ResetPasswordResponse

    fun  getResponse():ResetPasswordResponse? {
        return response;
    }

    fun setResponse(response:ResetPasswordResponse) {
        this.response = response;
    }

}