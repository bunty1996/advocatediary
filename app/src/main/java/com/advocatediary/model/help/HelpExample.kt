package com.advocatediary.model.help

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HelpExample {@SerializedName("response")
@Expose
private lateinit var response:HelpResponse

    fun  getResponse():HelpResponse? {
        return response;
    }

    fun setResponse(response:HelpResponse) {
        this.response = response;
    }

}