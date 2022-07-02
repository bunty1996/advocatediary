package com.advocatediary.model.stateDistricts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDistrictCourtNumber {

    @SerializedName("id")
    @Expose
    private lateinit var id: String
    @SerializedName("court_id")
    @Expose
    private lateinit var courtId: String
    @SerializedName("value")
    @Expose
    private lateinit var value: String

    fun getId(): String? {
        return id;
    }

    fun setId(id: String) {
        this.id = id;
    }

    fun getCourtId(): String? {
        return courtId;
    }

    fun setCourtId(courtId: String) {
        this.courtId = courtId;
    }

    fun getValue(): String? {
        return value;
    }

    fun setValue(value: String) {
        this.value = value;
    }

}