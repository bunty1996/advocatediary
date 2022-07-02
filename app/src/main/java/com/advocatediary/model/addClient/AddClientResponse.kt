package com.advocatediary.model.addClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddClientResponse {

    @SerializedName("status")
    @Expose
    private  lateinit var status:String
    @SerializedName("message")
    @Expose
    private lateinit var message:String

    @SerializedName("data")
    @Expose
    private lateinit var data:AddClientData


    fun  getStatus():String?
    {
        return status;
    }

    fun setStatus(status:String)
    {
        this.status = status;
    }

    fun  getMessage():String?
    {
        return message;
    }

    fun setMessage(message:String)
    {
        this.message = message;
    }

    fun  getData():AddClientData?
    {
        return data;
    }

    fun setData(data:AddClientData)
    {
        this.data = data;
    }

}