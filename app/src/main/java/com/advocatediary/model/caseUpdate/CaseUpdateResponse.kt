package com.advocatediary.model.caseUpdate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseUpdateResponse {@SerializedName("status")
@Expose
private lateinit var status:String
    @SerializedName("message")
    @Expose
    private lateinit var message:String
    @SerializedName("data")
    @Expose
    private lateinit var data:CaseUpdateData

    fun  getStatus():String? {
        return status;
    }

   fun setStatus(status:String) {
        this.status = status;
    }

    fun getMessage():String? {
        return message;
    }

    fun setMessage(message:String) {
        this.message = message;
    }

    fun  getData():CaseUpdateData? {
        return data;
    }

    fun setData(data:CaseUpdateData) {
        this.data = data;
    }

}