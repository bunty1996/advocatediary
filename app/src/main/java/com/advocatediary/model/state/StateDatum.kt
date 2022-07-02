package com.advocatediary.model.state

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDatum {

    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("title")
    @Expose
    private  var title: String? = null
    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    private  var updatedAt: String? = null
    @SerializedName("districts")
    @Expose
    private var districts: List<StateDistrict>? = null;

    fun getId(): String? {
        return id
    }
    fun setId(id:String)
    {
        this.id=id
    }

    fun getTitle(): String? {
        return title
    }
    fun setTitle(title:String)
    {
        this.title=title
    }

    fun getCreatedAt(): String? {
        return createdAt
    }
    fun setCreatedAt(createdAt:String)
    {
        this.createdAt=createdAt
    }

    fun getDistricts(): List<StateDistrict>? {
        return districts
    }
    fun setDistricts(districts:List<StateDistrict>)
    {
        this.districts=districts
    }


}