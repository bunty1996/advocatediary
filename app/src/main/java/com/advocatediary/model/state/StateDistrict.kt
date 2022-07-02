package com.advocatediary.model.state

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateDistrict {
    @SerializedName("id")
    @Expose
    private var id:String? = null
    @SerializedName("state_id")
    @Expose
    private var  stateId: String? = null;
    @SerializedName("title")
    @Expose
    private var title: String? = null
    @SerializedName("created_at")
    @Expose
    private var  createdAt: String? = null;
    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null;

    fun  getId(): String? {
        return id;
    }

    fun setId(id:String) {
        this.id = id;
    }

    fun  getStateId():String? {
        return stateId;
    }

    fun setStateId(stateId:String ) {
        this.stateId = stateId;
    }

    fun  getTitle():String? {
        return title;
    }

    fun setTitle(title:String) {
        this.title = title;
    }

    fun  getCreatedAt():String? {
        return createdAt;
    }

  fun setCreatedAt(createdAt:String ) {
        this.createdAt = createdAt;
    }

    fun  getUpdatedAt():String? {
        return updatedAt;
    }

   fun setUpdatedAt(updatedAt:String ) {
        this.updatedAt = updatedAt;
    }
}