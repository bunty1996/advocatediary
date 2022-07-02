package com.advocatediary.model.getJudges

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetJudgesResponse {
    @SerializedName("status")
    @Expose
    private lateinit var status: String
    @SerializedName("message")
    @Expose
    private lateinit var message: String
    @SerializedName("data")
    @Expose
    private lateinit var data: ArrayList<GetJudgesDatum>

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

    fun getData(): ArrayList<GetJudgesDatum>? {
        return data;
    }

    fun setData(data: ArrayList<GetJudgesDatum>) {
        this.data = data;
    }
}