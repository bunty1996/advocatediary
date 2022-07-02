package com.advocatediary.model.help

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HelpResponse {@SerializedName("status")
@Expose
private  lateinit var status:String
    @SerializedName("message")
    @Expose
    private lateinit var message:String
    @SerializedName("data")
    @Expose
    private lateinit var data:HelpData

    fun  getStatus():String? {
        return status;
    }

   fun setStatus(status:String) {
        this.status = status;
    }

    fun  getMessage():String? {
        return message;
    }

    fun setMessage(message:String) {
        this.message = message;
    }

    fun  getData():HelpData? {
        return data;
    }

    fun setData(data:HelpData) {
        this.data = data;
    }

}