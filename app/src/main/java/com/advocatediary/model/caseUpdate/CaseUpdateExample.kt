package com.advocatediary.model.caseUpdate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseUpdateExample {
    @SerializedName("response")
@Expose
private lateinit var response:CaseUpdateResponse;

    fun  getResponse():CaseUpdateResponse? {
        return response;
    }

   fun setResponse(response:CaseUpdateResponse) {
        this.response = response;
    }

}