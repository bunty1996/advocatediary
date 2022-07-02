package com.advocatediary.model.particularClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ParticularClientDatum {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("user_id")
    @Expose
    private var userId: String? = null
    @SerializedName("client_id")
    @Expose
    private var clientId: String? = null
    @SerializedName("opposite_party")
    @Expose
    private var oppositeParty: String? = null
    @SerializedName("opposite_lawyer")
    @Expose
    private var oppositeLawyer: String? = null
    @SerializedName("place")
    @Expose
    private var place: String? = null
    @SerializedName("section")
    @Expose
    private var section: String? = null
    @SerializedName("date_of_fileing")
    @Expose
    private var dateOfFileing: String? = null
    @SerializedName("bio")
    @Expose
    private var bio: String? = null
    @SerializedName("case_date")
    @Expose
    private var caseDate: String? = null
    @SerializedName("case_next_date")
    @Expose
    private var caseNextDate: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("purpose_of_hearing")
    @Expose
    private var purposeOfHearing: String? = null
    @SerializedName("state_title")
    @Expose
    private var stateTitle: String? = null
    @SerializedName("district_title")
    @Expose
    private var districtTitle: String? = null
    @SerializedName("court_title")
    @Expose
    private var courtTitle: String? = null
    @SerializedName("court_number_value")
    @Expose
    private var courtNumberValue: String? = null
    @SerializedName("case_type")
    @Expose
    private var caseType: String? = null
    @SerializedName("judge_first_name")
    @Expose
    private var judgeFirstName: String? = null
    @SerializedName("judge_last_name")
    @Expose
    private var judgeLastName: String? = null
    @SerializedName("purpose_hearing")
    @Expose
    private var purposeHearing: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
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

    fun getOppositeParty(): String? {
        return oppositeParty
    }

    fun setOppositeParty(oppositeParty: String) {
        this.oppositeParty = oppositeParty
    }

    fun getOppositeLawyer(): String? {
        return oppositeLawyer
    }

    fun setOppositeLawyer(oppositeLawyer: String) {
        this.oppositeLawyer = oppositeLawyer
    }

    fun getPlace(): String? {
        return place
    }

    fun setPlace(place: String) {
        this.place = place
    }

    fun getSection(): String? {
        return section
    }

    fun setSection(section: String) {
        this.section = section
    }

    fun getDateOfFileing(): String? {
        return dateOfFileing
    }

    fun setDateOfFileing(dateOfFileing: String) {
        this.dateOfFileing = dateOfFileing
    }

    fun getBio(): String? {
        return bio
    }

    fun setBio(bio: String) {
        this.bio = bio
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

    fun getPurposeOfHearing(): String? {
        return purposeOfHearing
    }

    fun setPurposeOfHearing(purposeOfHearing: String) {
        this.purposeOfHearing = purposeOfHearing
    }

    fun getStateTitle(): String? {
        return stateTitle
    }

    fun setStateTitle(stateTitle: String) {
        this.stateTitle = stateTitle
    }

    fun getDistrictTitle(): String? {
        return districtTitle
    }

    fun setDistrictTitle(districtTitle: String) {
        this.districtTitle = districtTitle
    }

    fun getCourtTitle(): String? {
        return courtTitle
    }

    fun setCourtTitle(courtTitle: String) {
        this.courtTitle = courtTitle
    }

    fun getCourtNumberValue(): String? {
        return courtNumberValue
    }

    fun setCourtNumberValue(courtNumberValue: String) {
        this.courtNumberValue = courtNumberValue
    }

    fun getCaseType(): String? {
        return caseType
    }

    fun setCaseType(caseType: String) {
        this.caseType = caseType
    }

    fun getJudgeFirstName(): String? {
        return judgeFirstName
    }

    fun setJudgeFirstName(judgeFirstName: String) {
        this.judgeFirstName = judgeFirstName
    }

    fun getJudgeLastName(): String? {
        return judgeLastName
    }

    fun setJudgeLastName(judgeLastName: String) {
        this.judgeLastName = judgeLastName
    }

    fun getPurposeHearing(): String? {
        return purposeHearing
    }

    fun setPurposeHearing(purposeHearing: String) {
        this.purposeHearing = purposeHearing
    }

}