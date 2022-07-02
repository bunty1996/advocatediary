package com.advocatediary.model.myCases

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class MyCasesDatum {
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("first_name")
    @Expose
    private var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    private var lastName: String? = null
    @SerializedName("full_name")
    @Expose
    private var fullName: String? = null
    @SerializedName("gender")
    @Expose
    private var gender: String? = null
    @SerializedName("image")
    @Expose
    private var image: String? = null
    @SerializedName("phone")
    @Expose
    private var phone: String? = null
    @SerializedName("email")
    @Expose
    private var email: String? = null
    @SerializedName("address")
    @Expose
    private var address: String? = null
    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null
    @SerializedName("case_next_date")
    @Expose
    private var caseNextDate: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("purpose_of_hearing")
    @Expose
    private var purposeOfHearing: String? = null
    @SerializedName("case_id")
    @Expose
    private var caseId: String? = null
    @SerializedName("case_type")
    @Expose
    private var caseType: String? = null
    @SerializedName("state_title")
    @Expose
    private var stateTitle: String? = null
    @SerializedName("district_title")
    @Expose
    private var districtTitle: String? = null
    @SerializedName("court_title")
    @Expose
    private var courtTitle: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String) {
        this.fullName = fullName
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
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

    fun getCaseId(): String? {
        return caseId
    }

    fun setCaseId(caseId: String) {
        this.caseId = caseId
    }

    fun getCaseType(): String? {
        return caseType
    }

    fun setCaseType(caseType: String) {
        this.caseType = caseType
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
}