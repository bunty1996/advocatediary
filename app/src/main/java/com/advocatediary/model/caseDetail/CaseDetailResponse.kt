package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailResponse {
    @SerializedName("status")
    @Expose
    private lateinit var status: String

    @SerializedName("message")
    @Expose
    private lateinit var message: String
    @SerializedName("data")
    @Expose
    private lateinit var data: CaseDetailData
    @SerializedName("case_status")
    @Expose
    private lateinit var caseStatus: String

    fun getStatus(): String? {
        return status;
    }

    fun setStatus(status: String) {
        this.status = status;
    }

    fun getMessage(): String? {
        return message;
    }

    fun setMessage(message: String) {
        this.message = message;
    }

    fun getData(): CaseDetailData? {
        return data;
    }

    fun setData(data: CaseDetailData) {
        this.data = data;
    }

    fun getCaseStatus(): String? {
        return caseStatus;
    }

    fun setCaseStatus(caseStatus: String) {
        this.caseStatus = caseStatus;
    }
}