package com.advocatediary.model.stateDistricts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDistrictDistrict {
    @SerializedName("id")
    @Expose
    private lateinit var id: String
    @SerializedName("state_id")
    @Expose
    private lateinit var stateId: String
    @SerializedName("title")
    @Expose
    private lateinit var title: String
    @SerializedName("courts")
    @Expose
    private lateinit var courts: List<StateDistrictsCourt>

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

    fun getTitle(): String? {
        return title;
    }

    fun setTitle(title: String) {
        this.title = title;
    }

    fun getCourts(): List<StateDistrictsCourt>? {
        return courts;
    }

    fun setCourts(courts: List<StateDistrictsCourt>) {
        this.courts = courts;
    }

}