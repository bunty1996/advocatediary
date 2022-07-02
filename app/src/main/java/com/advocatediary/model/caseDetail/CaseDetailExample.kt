package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailExample {@SerializedName("response")
@Expose
private lateinit var response:CaseDetailResponse

    fun  getResponse():CaseDetailResponse? {
        return response;
    }

    fun setResponse(response:CaseDetailResponse) {
        this.response = response;
    }

}