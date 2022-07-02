package com.advocatediary.model.getJudges

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetJudgeExample {
    @SerializedName("response")
    @Expose
    private lateinit var response:GetJudgesResponse ;

    fun  getResponse():GetJudgesResponse? {
        return response;
    }

   fun setResponse(response:GetJudgesResponse) {
        this.response = response;
    }

}