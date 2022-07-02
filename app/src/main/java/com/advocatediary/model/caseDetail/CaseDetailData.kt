package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailData {
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
    @SerializedName("opposite_party")
    @Expose
    private var oppositeParty: String? = null
    @SerializedName("opposite_lawyer")
    @Expose
    private var oppositeLawyer: String? = null
    @SerializedName("court_id")
    @Expose
    private var courtId: String? = null
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
    @SerializedName("judge_id")
    @Expose
    private var judgeId: String? = null
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
    @SerializedName("purpose_hearing")
    @Expose
    private var purposeHearing: String? = null
    @SerializedName("case_type")
    @Expose
    private var caseType: String? = null
    @SerializedName("court_title")
    @Expose
    private var courtTitle: String? = null
    @SerializedName("court_number_value")
    @Expose
    private var courtNumberValue: String? = null
    @SerializedName("state_title")
    @Expose
    private var stateTitle: String? = null
    @SerializedName("state_id")
    @Expose
    private var stateId: String? = null
    @SerializedName("district_id")
    @Expose
    private var districtId: String? = null
    @SerializedName("district_title")
    @Expose
    private var districtTitle: String? = null
    @SerializedName("judge_first_name")
    @Expose
    private var judgeFirstName: String? = null
    @SerializedName("judge_last_name")
    @Expose
    private var judgeLastName: String? = null
    @SerializedName("client_detail")
    @Expose
    private var clientDetail: CaseDetailClientDetail? = null
    @SerializedName("case_All_detail")
    @Expose
    private var caseAllDetail: List<CaseDetailCaseAllDetail>? = null
    @SerializedName("case_additional_payment_detail")
    @Expose
    private var caseAdditionalPaymentDetail: CaseDetailCaseAdditionalPaymentDetail? = null
    @SerializedName("casePaymentHistoryArr")
    @Expose
    private var casePaymentHistoryArr: List<CaseDetailCasePaymentHistoryArr>? = null
    @SerializedName("advance_payment")
    @Expose
    private var advancePayment: CaseDetailAdvancePayment? = null

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

    fun getCourtId(): String? {
        return courtId
    }

    fun setCourtId(courtId: String) {
        this.courtId = courtId
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

    fun getJudgeId(): String? {
        return judgeId
    }

    fun setJudgeId(judgeId: String) {
        this.judgeId = judgeId
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

    fun getPurposeHearing(): String? {
        return purposeHearing
    }

    fun setPurposeHearing(purposeHearing: String) {
        this.purposeHearing = purposeHearing
    }

    fun getCaseType(): String? {
        return caseType
    }

    fun setCaseType(caseType: String) {
        this.caseType = caseType
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

    fun getStateTitle(): String? {
        return stateTitle
    }

    fun setStateTitle(stateTitle: String) {
        this.stateTitle = stateTitle
    }

    fun getStateId(): String? {
        return stateId
    }

    fun setStateId(stateId: String) {
        this.stateId = stateId
    }

    fun getDistrictId(): String? {
        return districtId
    }

    fun setDistrictId(districtId: String) {
        this.districtId = districtId
    }

    fun getDistrictTitle(): String? {
        return districtTitle
    }

    fun setDistrictTitle(districtTitle: String) {
        this.districtTitle = districtTitle
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

    fun getClientDetail(): CaseDetailClientDetail? {
        return clientDetail
    }

    fun setClientDetail(clientDetail: CaseDetailClientDetail) {
        this.clientDetail = clientDetail
    }

    fun getCaseAllDetail(): List<CaseDetailCaseAllDetail>? {
        return caseAllDetail
    }

    fun setCaseAllDetail(caseAllDetail: List<CaseDetailCaseAllDetail>) {
        this.caseAllDetail = caseAllDetail
    }

    fun getCaseAdditionalPaymentDetail(): CaseDetailCaseAdditionalPaymentDetail? {
        return caseAdditionalPaymentDetail
    }

    fun setCaseAdditionalPaymentDetail(caseAdditionalPaymentDetail: CaseDetailCaseAdditionalPaymentDetail) {
        this.caseAdditionalPaymentDetail = caseAdditionalPaymentDetail
    }

    fun getCasePaymentHistoryArr(): List<CaseDetailCasePaymentHistoryArr>? {
        return casePaymentHistoryArr
    }

    fun setCasePaymentHistoryArr(casePaymentHistoryArr: List<CaseDetailCasePaymentHistoryArr>) {
        this.casePaymentHistoryArr = casePaymentHistoryArr
    }

    fun getAdvancePayment(): CaseDetailAdvancePayment? {
        return advancePayment
    }

    fun setAdvancePayment(advancePayment: CaseDetailAdvancePayment) {
        this.advancePayment = advancePayment
    }
}