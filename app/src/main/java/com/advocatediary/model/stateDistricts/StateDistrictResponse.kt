package com.advocatediary.model.stateDistricts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDistrictResponse {
    @SerializedName("status")
    @Expose
    private lateinit var  status:String
    @SerializedName("message")
    @Expose
    private lateinit var  message:String
    @SerializedName("data")
    @Expose
    private lateinit var  data:ArrayList<StateDistrictDatum>

    fun  getStatus() :String?{
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

    fun  getData():ArrayList<StateDistrictDatum>? {
        return data;
    }

   fun setData(data:ArrayList<StateDistrictDatum>) {
        this.data = data;
    }

}