package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeTodayCaseType_ {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("case_type")
    @Expose
    private var caseType: String? = null
    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getCaseType(): String? {
        return caseType
    }

    fun setCaseType(caseType: String) {
        this.caseType = caseType
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String) {
        this.updatedAt = updatedAt
    }
}