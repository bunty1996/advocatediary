package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomePendingCasesArr {
    @SerializedName("id")
    @Expose
    private lateinit var id: String
    @SerializedName("case_id")
    @Expose
    private lateinit var caseId: String
    @SerializedName("user_id")
    @Expose
    private lateinit var userId: String
    @SerializedName("client_id")
    @Expose
    private lateinit var clientId: String
    @SerializedName("case_date")
    @Expose
    private lateinit var caseDate: String
    @SerializedName("case_next_date")
    @Expose
    private lateinit var caseNextDate: String
    @SerializedName("status")
    @Expose
    private lateinit var status: String
    @SerializedName("notes")
    @Expose
    private lateinit var notes: String
    @SerializedName("purpose_of_hearing")
    @Expose
    private lateinit var purposeOfHearing: String
    @SerializedName("created_at")
    @Expose
    private lateinit var createdAt: String
    @SerializedName("updated_at")
    @Expose
    private lateinit var updatedAt: String
    @SerializedName("client")
    @Expose
    private lateinit var client: HomeTodayClient
    @SerializedName("case")
    @Expose
    private var _case: HomeTodayCase? = null
    @SerializedName("purpose_hearing")
    @Expose
    private lateinit var purposeHearing: HomeTodayPurposeHearing

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getCaseId(): String {
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

    fun getCaseNextDate(): String {
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