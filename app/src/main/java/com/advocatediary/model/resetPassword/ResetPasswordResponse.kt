package com.advocatediary.model.resetPassword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResetPasswordResponse {
    @SerializedName("status")
    @Expose
    private lateinit var status:String
    @SerializedName("message")
    @Expose
    private lateinit var message:String
    @SerializedName("data")
    @Expose
    private lateinit var data:ResetPasswordData

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

    fun  getData():ResetPasswordData?
    {
        return data;
    }

    fun setData(data:ResetPasswordData)
    {
        this.data = data;
    }

}