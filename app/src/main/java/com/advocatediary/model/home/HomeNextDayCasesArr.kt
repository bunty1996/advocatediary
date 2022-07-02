package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeNextDayCasesArr {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("case_id")
    @Expose
    private var caseId: String? = null
    @SerializedName("user_id")
    @Expose
    private var userId: String? = null
    @SerializedName("client_id")
    @Expose
    private var clientId: String? = null
    @SerializedName("case_date")
    @Expose
    private var caseDate: String? = null
    @SerializedName("case_next_date")
    @Expose
    private var caseNextDate: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("notes")
    @Expose
    private var notes: String? = null
    @SerializedName("purpose_of_hearing")
    @Expose
    private var purposeOfHearing: String? = null
    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null
    @SerializedName("client")
    @Expose
    private var client: HomeTodayClient? = null
    @SerializedName("case")
    @Expose
    private var _case: HomeTodayCase? = null
    @SerializedName("purpose_hearing")
    @Expose
    private var purposeHearing: HomeTodayPurposeHearing? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getCaseId(): String? {
        return caseId
    }

    fun setCaseId(caseId: String) {
        this.caseId = caseId
    }

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun getClientId(): String? {
        return clientId
    }

    fun setClientId(clientId: String) {
        this.clientId = clientId
    }

    fun getCaseDate(): String? {
        return caseDate
    }

    fun setCaseDate(caseDate: String) {
        this.caseDate = caseDate
    }

    fun getCaseNextDate(): String? {
        return caseNextDate
    }

    fun setCaseNextDate(caseNextDate: String) {
        this.caseNextDate = caseNextDate
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getNotes(): String? {
        return notes
    }

    fun setNotes(notes: String) {
        this.notes = notes
    }

    fun getPurposeOfHearing(): String? {
        return purposeOfHearing
    }

    fun setPurposeOfHearing(purposeOfHearing: String) {
        this.purposeOfHearing = purposeOfHearing
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

    fun getClient(): HomeTodayClient? {
        return client
    }

    fun setClient(client: HomeTodayClient) {
        this.client = client
    }

    fun getCase(): HomeTodayCase? {
        return _case
    }

    fun setCase(_case: HomeTodayCase) {
        this._case = _case
    }

    fun getPurposeHearing(): HomeTodayPurposeHearing? {
        return purposeHearing
    }

    fun setPurposeHearing(purposeHearing: HomeTodayPurposeHearing) {
        this.purposeHearing = purposeHearing
    }
}