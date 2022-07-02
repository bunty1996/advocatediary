package com.advocatediary.model.stateDistricts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDistrictExample {
    @SerializedName("response")
    @Expose
    private lateinit var response: StateDistrictResponse

    fun getResponse(): StateDistrictResponse? {
        return response;
    }

    fun setResponse(response: StateDistrictResponse) {
        this.response = response;
    }


}