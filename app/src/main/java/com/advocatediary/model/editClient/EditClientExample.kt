package com.advocatediary.model.editClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EditClientExample {@SerializedName("response")
@Expose
private lateinit var response:EditClientResponse

    fun  getResponse():EditClientResponse? {
        return response;
    }

   fun setResponse(response:EditClientResponse) {
        this.response = response;
    }

}