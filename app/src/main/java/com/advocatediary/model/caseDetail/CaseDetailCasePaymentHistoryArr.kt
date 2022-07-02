package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailCasePaymentHistoryArr {@SerializedName("id")
@Expose
private lateinit var id:Integer
    @SerializedName("case_id")
    @Expose
    private lateinit var caseId:Integer
    @SerializedName("deposit")
    @Expose
    private  var deposit:String? = null
    @SerializedName("pending_fee")
    @Expose
    private lateinit var pendingFee:String
    @SerializedName("total_fee")
    @Expose
    private lateinit var totalFee:String
    @SerializedName("created_at")
    @Expose
    private lateinit var createdAt:String
    @SerializedName("updated_at")
    @Expose
    private lateinit var updatedAt:String

    fun getId():Integer? {
        return id;
    }

    fun setId(id:Integer) {
        this.id = id;
    }

    fun  getCaseId():Integer? {
        return caseId;
    }

    fun setCaseId(caseId:Integer) {
        this.caseId = caseId;
    }

    fun  getDeposit():String? {
        return deposit;
    }

    fun setDeposit(deposit:String) {
        this.deposit = deposit;
    }

    fun getPendingFee():String? {
        return pendingFee;
    }

    fun setPendingFee(pendingFee:String) {
        this.pendingFee = pendingFee;
    }

    fun getTotalFee():String? {
        return totalFee;
    }

    fun setTotalFee(totalFee:String) {
        this.totalFee = totalFee;
    }

    fun getCreatedAt():String? {
        return createdAt;
    }

    fun setCreatedAt(createdAt:String) {
        this.createdAt = createdAt;
    }

    fun getUpdatedAt():String? {
        return updatedAt;
    }

    fun setUpdatedAt(updatedAt:String) {
        this.updatedAt = updatedAt;
    }

}