package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailCaseAllDetail {
    @SerializedName("id")
@Expose
private lateinit var  id:String
    @SerializedName("case_id")
    @Expose
    private lateinit var  caseId:String
    @SerializedName("user_id")
    @Expose
    private lateinit var userId:String
    @SerializedName("client_id")
    @Expose
    private lateinit var clientId:String
    @SerializedName("case_type_id")
    @Expose
    private lateinit var caseTypeId:String
    @SerializedName("judge_id")
    @Expose
    private lateinit var judgeId:String
    @SerializedName("state_id")
    @Expose
    private lateinit var stateId:String
    @SerializedName("district_id")
    @Expose
    private lateinit var districtId:String
    @SerializedName("court_id")
    @Expose
    private lateinit var courtId:String
    @SerializedName("court_number_id")
    @Expose
    private lateinit var courtNumberId:String
    @SerializedName("purpose_of_hearing")
    @Expose
    private lateinit var purposeOfHearing:String
    @SerializedName("case_date")
    @Expose
    private lateinit var caseDate:String
    @SerializedName("previous_case_date")
    @Expose
    private lateinit var previousCaseDate:String
    @SerializedName("case_next_date")
    @Expose
    private lateinit var caseNextDate:String
    @SerializedName("important_date_status")
    @Expose
    private lateinit var importantDateStatus:String
    @SerializedName("notes")
    @Expose
    private lateinit var notes:String
    @SerializedName("opposite_party")
    @Expose
    private lateinit var oppositeParty:String
    @SerializedName("opposite_lawyer")
    @Expose
    private lateinit var oppositeLawyer:String
    @SerializedName("place")
    @Expose
    private lateinit var place:String
    @SerializedName("section")
    @Expose
    private lateinit var section:String
    @SerializedName("date_of_fileing")
    @Expose
    private lateinit var dateOfFileing:String
    @SerializedName("status")
    @Expose
    private lateinit var status:String
    @SerializedName("payment")
    @Expose
    private lateinit var payment:String
    @SerializedName("total_fee")
    @Expose
    private lateinit var totalFee:String
    @SerializedName("advanced_fee")
    @Expose
    private lateinit var advancedFee:String
    @SerializedName("pending_fee")
    @Expose
    private lateinit var pendingFee:String
    @SerializedName("bio")
    @Expose
    private lateinit var bio:String
    @SerializedName("created_at")
    @Expose
    private lateinit var createdAt:String
    @SerializedName("updated_at")
    @Expose
    private lateinit var updatedAt:String
    @SerializedName("purpose_hearing")
    @Expose
    private lateinit var purposeHearing:String
    @SerializedName("case_type")
    @Expose
    private lateinit var caseType:String
    @SerializedName("court_title")
    @Expose
    private lateinit var courtTitle:String
    @SerializedName("court_number_value")
    @Expose
    private lateinit var courtNumberValue:String
    @SerializedName("state_title")
    @Expose
    private lateinit var stateTitle:String
    @SerializedName("district_title")
    @Expose
    private lateinit var districtTitle:String
    @SerializedName("judge_first_name")
    @Expose
    private lateinit var judgeFirstName:String
    @SerializedName("judge_last_name")
    @Expose
    private lateinit var judgeLastName:String

    fun  getId():String? {
        return id;
    }

    fun setId(id:String) {
        this.id = id;
    }

    fun getCaseId():String? {
        return caseId;
    }

    fun setCaseId(caseId:String) {
        this.caseId = caseId;
    }

    fun getUserId():String? {
        return userId;
    }

    fun setUserId(userId:String) {
        this.userId = userId;
    }

    fun getClientId():String? {
        return clientId;
    }

    fun setClientId(clientId:String) {
        this.clientId = clientId;
    }

    fun getCaseTypeId():String? {
        return caseTypeId;
    }

    fun setCaseTypeId(caseTypeId:String) {
        this.caseTypeId = caseTypeId;
    }

    fun getJudgeId():String? {
        return judgeId;
    }

    fun setJudgeId(judgeId:String) {
        this.judgeId = judgeId;
    }

    fun getStateId():String? {
        return stateId;
    }

    fun setStateId(stateId:String) {
        this.stateId = stateId;
    }

    fun getDistrictId():String? {
        return districtId;
    }

    fun setDistrictId(districtId:String) {
        this.districtId = districtId;
    }

    fun getCourtId():String? {
        return courtId;
    }

    fun setCourtId(courtId:String) {
        this.courtId = courtId;
    }

    fun getCourtNumberId():String? {
        return courtNumberId;
    }

    fun setCourtNumberId(courtNumberId:String) {
        this.courtNumberId = courtNumberId;
    }

    fun getPurposeOfHearing():String? {
        return purposeOfHearing;
    }

    fun setPurposeOfHearing(purposeOfHearing:String) {
        this.purposeOfHearing = purposeOfHearing;
    }

    fun getCaseDate():String? {
        return caseDate;
    }

    fun setCaseDate(caseDate:String) {
        this.caseDate = caseDate;
    }

    fun getPreviousCaseDate():String? {
        return previousCaseDate;
    }

    fun setPreviousCaseDate(previousCaseDate:String) {
        this.previousCaseDate = previousCaseDate;
    }

    fun getCaseNextDate():String? {
        return caseNextDate;
    }

    fun setCaseNextDate(caseNextDate:String) {
        this.caseNextDate = caseNextDate;
    }

    fun getImportantDateStatus():String? {
        return importantDateStatus;
    }

    fun setImportantDateStatus(importantDateStatus:String) {
        this.importantDateStatus = importantDateStatus;
    }

    fun getNotes():String? {
        return notes;
    }

    fun setNotes(notes:String) {
        this.notes = notes;
    }

    fun getOppositeParty():String? {
        return oppositeParty;
    }

    fun setOppositeParty(oppositeParty:String) {
        this.oppositeParty = oppositeParty;
    }

    fun getOppositeLawyer():String? {
        return oppositeLawyer;
    }

    fun setOppositeLawyer(oppositeLawyer:String) {
        this.oppositeLawyer = oppositeLawyer;
    }

    fun getPlace():String? {
        return place;
    }

    fun setPlace(place:String) {
        this.place = place;
    }

    fun getSection():String? {
        return section;
    }

    fun setSection(section:String) {
        this.section = section;
    }

    fun getDateOfFileing():String? {
        return dateOfFileing;
    }

    fun setDateOfFileing(dateOfFileing:String) {
        this.dateOfFileing = dateOfFileing;
    }

    fun getStatus():String? {
        return status;
    }

    fun setStatus(status:String) {
        this.status = status;
    }

    fun getPayment():String? {
        return payment;
    }

    fun setPayment(payment:String) {
        this.payment = payment;
    }

    fun getTotalFee():String? {
        return totalFee;
    }

    fun setTotalFee(totalFee:String) {
        this.totalFee = totalFee;
    }

    fun getAdvancedFee():String? {
        return advancedFee;
    }

    fun setAdvancedFee(advancedFee:String) {
        this.advancedFee = advancedFee;
    }

    fun getPendingFee():String? {
        return pendingFee;
    }

    fun setPendingFee(pendingFee:String) {
        this.pendingFee = pendingFee;
    }

    fun getBio():String? {
        return bio;
    }

    fun setBio(bio:String) {
        this.bio = bio;
    }

    fun getCreatedAt():String? {
        return createdAt;
    }

    fun setCreatedAt(createdAt:String) {
        this.createdAt = createdAt;
    }

    fun getUpdatedAt():String? {
        return updatedAt;
    }

    fun setUpdatedAt(updatedAt:String) {
        this.updatedAt = updatedAt;
    }

    fun getPurposeHearing():String? {
        return purposeHearing;
    }

    fun setPurposeHearing(purposeHearing:String) {
        this.purposeHearing = purposeHearing;
    }

    fun getCaseType():String? {
        return caseType;
    }

    fun setCaseType(caseType:String) {
        this.caseType = caseType;
    }

    fun getCourtTitle():String? {
        return courtTitle;
    }

    fun setCourtTitle(courtTitle:String) {
        this.courtTitle = courtTitle;
    }

    fun getCourtNumberValue():String? {
        return courtNumberValue;
    }

    fun setCourtNumberValue(courtNumberValue:String) {
        this.courtNumberValue = courtNumberValue;
    }

    fun getStateTitle():String? {
        return stateTitle;
    }

    fun setStateTitle(stateTitle:String) {
        this.stateTitle = stateTitle;
    }

    fun getDistrictTitle():String? {
        return districtTitle;
    }

    fun setDistrictTitle(districtTitle:String) {
        this.districtTitle = districtTitle;
    }

    fun getJudgeFirstName():String? {
        return judgeFirstName;
    }

    fun setJudgeFirstName(judgeFirstName:String) {
        this.judgeFirstName = judgeFirstName;
    }

    fun getJudgeLastName():String? {
        return judgeLastName;
    }

    fun setJudgeLastName(judgeLastName:String) {
        this.judgeLastName = judgeLastName;
    }

}