package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeTodayCase {

    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("user_id")
    @Expose
    private var userId: String? = null
    @SerializedName("client_id")
    @Expose
    private var clientId: String? = null
    @SerializedName("case_type_id")
    @Expose
    private var caseTypeId: String? = null
    @SerializedName("court_id")
    @Expose
    private var courtId: String? = null
    @SerializedName("court_number_id")
    @Expose
    private var courtNumberId: String? = null
    @SerializedName("district_id")
    @Expose
    private var districtId: String? = null
    @SerializedName("state_id")
    @Expose
    private var stateId: String? = null
    @SerializedName("judge_id")
    @Expose
    private var judgeId: String? = null
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
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("payment")
    @Expose
    private var payment: String? = null
    @SerializedName("total_fee")
    @Expose
    private var totalFee: String? = null
    @SerializedName("advanced_fee")
    @Expose
    private var advancedFee: String? = null
    @SerializedName("pending_fee")
    @Expose
    private var pendingFee: String? = null
    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null
    @SerializedName("case_type")
    @Expose
    private var caseType: HomeTodayCaseType_? = null
    @SerializedName("court")
    @Expose
    private var court: HomeTodayCourt_? = null
    @SerializedName("district")
    @Expose
    private var district: HomeTodayDistrict_? = null
    @SerializedName("state")
    @Expose
    private var state: HomeTodayState_? = null
    @SerializedName("judge")
    @Expose
    private var judge: HomeTodayJudge_? = null

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

    fun getCaseTypeId(): String? {
        return caseTypeId
    }

    fun setCaseTypeId(caseTypeId: String) {
        this.caseTypeId = caseTypeId
    }

    fun getCourtId(): String? {
        return courtId
    }

    fun setCourtId(courtId: String) {
        this.courtId = courtId
    }

    fun getCourtNumberId(): String? {
        return courtNumberId
    }

    fun setCourtNumberId(courtNumberId: String) {
        this.courtNumberId = courtNumberId
    }

    fun getDistrictId(): String? {
        return districtId
    }

    fun setDistrictId(districtId: String) {
        this.districtId = districtId
    }

    fun getStateId(): String? {
        return stateId
    }

    fun setStateId(stateId: String) {
        this.stateId = stateId
    }

    fun getJudgeId(): String? {
        return judgeId
    }

    fun setJudgeId(judgeId: String) {
        this.judgeId = judgeId
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

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getPayment(): String? {
        return payment
    }

    fun setPayment(payment: String) {
        this.payment = payment
    }

    fun getTotalFee(): String? {
        return totalFee
    }

    fun setTotalFee(totalFee: String) {
        this.totalFee = totalFee
    }

    fun getAdvancedFee(): String? {
        return advancedFee
    }

    fun setAdvancedFee(advancedFee: String) {
        this.advancedFee = advancedFee
    }

    fun getPendingFee(): String? {
        return pendingFee
    }

    fun setPendingFee(pendingFee: String) {
        this.pendingFee = pendingFee
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

    fun getCaseType(): HomeTodayCaseType_? {
        return caseType
    }

    fun setCaseType(caseType: HomeTodayCaseType_) {
        this.caseType = caseType
    }

    fun getCourt(): HomeTodayCourt_? {
        return court
    }

    fun setCourt(court: HomeTodayCourt_) {
        this.court = court
    }

    fun getDistrict(): HomeTodayDistrict_? {
        return district
    }

    fun setDistrict(district: HomeTodayDistrict_) {
        this.district = district
    }

    fun getState(): HomeTodayState_? {
        return state
    }

    fun setState(state: HomeTodayState_) {
        this.state = state
    }

    fun getJudge(): HomeTodayJudge_? {
        return judge
    }

    fun setJudge(judge: HomeTodayJudge_) {
        this.judge = judge
    }
}