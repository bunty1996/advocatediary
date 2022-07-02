package com.advocatediary.model.caseUpdate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseUpdateData {@SerializedName("case_next_date")
@Expose
private lateinit var caseNextDate:String
    @SerializedName("notes")
    @Expose
    private lateinit var notes:String
    @SerializedName("status")
    @Expose
    private lateinit var status:String

    fun  getCaseNextDate():String? {
        return caseNextDate;
    }

    fun setCaseNextDate(caseNextDate:String) {
        this.caseNextDate = caseNextDate;
    }

    fun  getNotes():String {
        return notes;
    }

    fun setNotes(notes:String) {
        this.notes = notes;
    }

    fun  getStatus():String? {
        return status;
    }

   fun setStatus(status:String) {
        this.status = status;
    }

}