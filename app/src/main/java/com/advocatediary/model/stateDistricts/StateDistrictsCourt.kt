package com.advocatediary.model.stateDistricts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDistrictsCourt {
    @SerializedName("id")
    @Expose
    private lateinit var id: String
    @SerializedName("state_id")
    @Expose
    private lateinit var stateId: String
    @SerializedName("district_id")
    @Expose
    private lateinit var districtId: String
    @SerializedName("court_title")
    @Expose
    private lateinit var courtTitle: String
    @SerializedName("court_numbers")
    @Expose
    private lateinit var courtNumbers: List<StateDistrictCourtNumber>;

    fun getId(): String? {
        return id;
    }

    fun setId(id: String) {
        this.id = id;
    }

    fun getStateId(): String? {
        return stateId;
    }

    fun setStateId(stateId: String) {
        this.stateId = stateId;
    }

    fun getDistrictId(): String? {
        return districtId;
    }

    fun setDistrictId(districtId: String) {
        this.districtId = districtId;
    }

    fun getCourtTitle(): String {
        return courtTitle;
    }

    fun setCourtTitle(courtTitle: String) {
        this.courtTitle = courtTitle;
    }

    fun getCourtNumbers(): List<StateDistrictCourtNumber> {
        return courtNumbers;
    }

    fun setCourtNumbers(courtNumbers: List<StateDistrictCourtNumber>) {
        this.courtNumbers = courtNumbers;
    }
}