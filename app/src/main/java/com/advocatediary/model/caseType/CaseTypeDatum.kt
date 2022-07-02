package com.advocatediary.model.caseType

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseTypeDatum {
    @SerializedName("id")
    @Expose
    private lateinit var id:Integer
    @SerializedName("case_type")
    @Expose
    private  lateinit var caseType:String
    @SerializedName("created_at")
    @Expose
    private lateinit var createdAt:String
    @SerializedName("updated_at")
    @Expose
    private lateinit var updatedAt:String

    fun  getId():Integer?
    {
        return id;
    }

    fun setId(id:Integer)
    {
        this.id = id;
    }

    fun  getCaseType():String?
    {
        return caseType;
    }

    fun setCaseType(caseType:String)
    {
        this.caseType = caseType;
    }

    fun  getCreatedAt():String?
    {
        return createdAt;
    }

    fun setCreatedAt(createdAt:String)
    {
        this.createdAt = createdAt;
    }

    fun getUpdatedAt():String?
    {
        return updatedAt;
    }

    fun setUpdatedAt(updatedAt:String)
    {
        this.updatedAt = updatedAt;
    }
}