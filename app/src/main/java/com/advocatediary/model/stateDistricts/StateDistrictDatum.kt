package com.advocatediary.model.stateDistricts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDistrictDatum {
    @SerializedName("id")
    @Expose
    private lateinit var id: String
    @SerializedName("title")
    @Expose
    private lateinit var title: String
    @SerializedName("districts")
    @Expose
    private lateinit var districts: List<StateDistrictDistrict>

    fun getId(): String? {
        return id;
    }

    fun setId(id: String) {
        this.id = id;
    }

    fun getTitle(): String? {
        return title;
    }

    fun setTitle(title: String) {
        this.title = title;
    }

    fun getDistricts(): List<StateDistrictDistrict> {
        return districts;
    }

    fun setDistricts(districts: List<StateDistrictDistrict>) {
        this.districts = districts;
    }

}