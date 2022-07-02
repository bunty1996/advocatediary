package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailAdvancePayment {
    @SerializedName("advanced_fee")
    @Expose
    private lateinit var advancedFee: String

    public fun getAdvancedFee(): String? {
        return advancedFee;
    }

    fun setAdvancedFee(advancedFee: String) {
        this.advancedFee = advancedFee;
    }

}