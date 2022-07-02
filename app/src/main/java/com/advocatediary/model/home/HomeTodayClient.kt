package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeTodayClient {

    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("role_id")
    @Expose
    private var roleId: String? = null
    @SerializedName("advocate_id")
    @Expose
    private var advocateId: String? = null
    @SerializedName("state_id")
    @Expose
    private var stateId: String? = null
    @SerializedName("district_id")
    @Expose
    private var districtId: String? = null
    @SerializedName("phone")
    @Expose
    private var phone: String? = null
    @SerializedName("alternate_phone")
    @Expose
    private var alternatePhone: String? = null
    @SerializedName("first_name")
    @Expose
    private var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    private var lastName: String? = null
    @SerializedName("full_name")
    @Expose
    private var fullName: String? = null
    @SerializedName("slug")
    @Expose
    private var slug: String? = null
    @SerializedName("gender")
    @Expose
    private var gender: String? = null
    @SerializedName("email")
    @Expose
    private var email: String? = null
    @SerializedName("image")
    @Expose
    private var image: String? = null
    @SerializedName("address")
    @Expose
    private var address: String? = null
    @SerializedName("expertise")
    @Expose
    private var expertise: String? = null
    @SerializedName("office_address")
    @Expose
    private var officeAddress: String? = null
    @SerializedName("office_phone")
    @Expose
    private var officePhone: String? = null
    @SerializedName("device_token")
    @Expose
    private var deviceToken: String? = null
    @SerializedName("device_type")
    @Expose
    private var deviceType: String? = null
    @SerializedName("registered_on_site")
    @Expose
    private var registeredOnSite: String? = null
    @SerializedName("facebook_link")
    @Expose
    private var facebookLink: String? = null
    @SerializedName("twitter_link")
    @Expose
    private var twitterLink: String? = null
    @SerializedName("google_plus")
    @Expose
    private var googlePlus: String? = null
    @SerializedName("instagram_link")
    @Expose
    private var instagramLink: String? = null
    @SerializedName("skype")
    @Expose
    private var skype: String? = null
    @SerializedName("linkedin")
    @Expose
    private var linkedin: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    private var updatedAt: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getRoleId(): String? {
        return roleId
    }

    fun setRoleId(roleId: String) {
        this.roleId = roleId
    }

    fun getAdvocateId(): String? {
        return advocateId
    }

    fun setAdvocateId(advocateId: String) {
        this.advocateId = advocateId
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

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getAlternatePhone(): String? {
        return alternatePhone
    }

    fun setAlternatePhone(alternatePhone: String) {
        this.alternatePhone = alternatePhone
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

    fun getSlug(): String? {
        return slug
    }

    fun setSlug(slug: String) {
        this.slug = slug
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getExpertise(): String? {
        return expertise
    }

    fun setExpertise(expertise: String) {
        this.expertise = expertise
    }

    fun getOfficeAddress(): String? {
        return officeAddress
    }

    fun setOfficeAddress(officeAddress: String) {
        this.officeAddress = officeAddress
    }

    fun getOfficePhone(): String? {
        return officePhone
    }

    fun setOfficePhone(officePhone: String) {
        this.officePhone = officePhone
    }

    fun getDeviceToken(): String? {
        return deviceToken
    }

    fun setDeviceToken(deviceToken: String) {
        this.deviceToken = deviceToken
    }

    fun getDeviceType(): String? {
        return deviceType
    }

    fun setDeviceType(deviceType: String) {
        this.deviceType = deviceType
    }

    fun getRegisteredOnSite(): String? {
        return registeredOnSite
    }

    fun setRegisteredOnSite(registeredOnSite: String) {
        this.registeredOnSite = registeredOnSite
    }

    fun getFacebookLink(): String? {
        return facebookLink
    }

    fun setFacebookLink(facebookLink: String) {
        this.facebookLink = facebookLink
    }

    fun getTwitterLink(): String? {
        return twitterLink
    }

    fun setTwitterLink(twitterLink: String) {
        this.twitterLink = twitterLink
    }

    fun getGooglePlus(): String? {
        return googlePlus
    }

    fun setGooglePlus(googlePlus: String) {
        this.googlePlus = googlePlus
    }

    fun getInstagramLink(): String? {
        return instagramLink
    }

    fun setInstagramLink(instagramLink: String) {
        this.instagramLink = instagramLink
    }

    fun getSkype(): String? {
        return skype
    }

    fun setSkype(skype: String) {
        this.skype = skype
    }

    fun getLinkedin(): String? {
        return linkedin
    }

    fun setLinkedin(linkedin: String) {
        this.linkedin = linkedin
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
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

}