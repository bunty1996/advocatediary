package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeTodayCourt_
{
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("state_id")
    @Expose
    private var stateId: String? = null
    @SerializedName("district_id")
    @Expose
    private var districtId: String? = null
    @SerializedName("court_title")
    @Expose
    private var courtTitle: String? = null
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

    fun getStateId(): String? {
        return stateId
    }

    fun setStateId(stateId: String) {
        this.stateId = stateId
    }

    fun getDistrictId(): String? {
        return districtId
    }

    fun setDistrictId(districtId: String) {
        this.districtId = districtId
    }

    fun getCourtTitle(): String? {
        return courtTitle
    }

    fun setCourtTitle(courtTitle: String) {
        this.courtTitle = courtTitle
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