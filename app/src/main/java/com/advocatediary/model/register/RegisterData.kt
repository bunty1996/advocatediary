package com.advocatediary.model.register

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterData {

    @SerializedName("id")
    @Expose
    private lateinit var id:Integer
    @SerializedName("role_id")
    @Expose
    private lateinit var roleId:String
    @SerializedName("advocate_id")
    @Expose
    private lateinit var advocateId:String
    @SerializedName("state_id")
    @Expose
    private lateinit var stateId:String
    @SerializedName("district_id")
    @Expose
    private lateinit var districtId:String
    @SerializedName("phone")
    @Expose
    private lateinit var phone:String
    @SerializedName("alternate_phone")
    @Expose
    private lateinit var alternatePhone:String
    @SerializedName("first_name")
    @Expose
    private lateinit var firstName:String
    @SerializedName("last_name")
    @Expose
    private lateinit var lastName:String
    @SerializedName("full_name")
    @Expose
    private lateinit var fullName:String
    @SerializedName("slug")
    @Expose
    private lateinit var slug:String
    @SerializedName("gender")
    @Expose
    private lateinit var gender:String
    @SerializedName("email")
    @Expose
    private lateinit var email:String
    @SerializedName("image")
    @Expose
    private lateinit var image:String
    @SerializedName("address")
    @Expose
    private lateinit var address:String
    @SerializedName("expertise")
    @Expose
    private lateinit var expertise:String
    @SerializedName("office_address")
    @Expose
    private lateinit var officeAddress:String
    @SerializedName("office_phone")
    @Expose
    private lateinit var officePhone:String
    @SerializedName("device_token")
    @Expose
    private lateinit var deviceToken:String
    @SerializedName("device_type")
    @Expose
    private lateinit var deviceType:String
    @SerializedName("registered_on_site")
    @Expose
    private lateinit var registeredOnSite:String

    @SerializedName("status")
    @Expose
    private lateinit var status:String
    @SerializedName("created_at")
    @Expose
    private lateinit var createdAt:String
    @SerializedName("updated_at")
    @Expose
    private lateinit var updatedAt:String

    fun  getId():Integer? {
        return id;
    }

  fun setId(id:Integer) {
        this.id = id;
    }

    fun  getRoleId():String? {
        return roleId;
    }

   fun setRoleId(roleId:String) {
        this.roleId = roleId;
    }

    fun  getAdvocateId():String? {
        return advocateId;
    }

    fun setAdvocateId(advocateId:String) {
        this.advocateId = advocateId;
    }

    fun  getStateId():String? {
        return stateId;
    }

    fun setStateId(stateId:String) {
        this.stateId = stateId;
    }

    fun  getDistrictId():String? {
        return districtId;
    }

   fun setDistrictId(districtId:String) {
        this.districtId = districtId;
    }

    fun  getPhone():String {
        return phone;
    }

    fun setPhone(phone:String) {
        this.phone = phone;
    }

    fun  getAlternatePhone():String? {
        return alternatePhone;
    }

    fun setAlternatePhone(alternatePhone:String) {
        this.alternatePhone = alternatePhone;
    }

    fun  getFirstName():String? {
        return firstName;
    }

    fun setFirstName(firstName:String) {
        this.firstName = firstName;
    }

    fun  getLastName():String? {
        return lastName;
    }

    fun setLastName(lastName:String) {
        this.lastName = lastName;
    }

    fun  getFullName():String? {
        return fullName;
    }

    fun setFullName(fullName:String) {
        this.fullName = fullName;
    }

    fun  getSlug():String? {
        return slug;
    }

    fun setSlug(slug:String) {
        this.slug = slug;
    }

    fun  getGender():String? {
        return gender;
    }

    fun setGender(gender:String) {
        this.gender = gender;
    }

    fun  getEmail():String? {
        return email;
    }

    fun setEmail(email:String) {
        this.email = email;
    }

    fun  getImage():String? {
        return image;
    }

    fun setImage(image:String) {
        this.image = image;
    }

    fun  getAddress():String? {
        return address;
    }

  fun setAddress(address:String) {
        this.address = address;
    }

    fun  getExpertise():String? {
        return expertise;
    }

    fun setExpertise(expertise:String) {
        this.expertise = expertise;
    }

    fun  getOfficeAddress():String? {
        return officeAddress;
    }

    fun setOfficeAddress(officeAddress:String) {
        this.officeAddress = officeAddress;
    }

    fun  getOfficePhone():String? {
        return officePhone;
    }

   fun setOfficePhone(officePhone:String) {
        this.officePhone = officePhone;
    }

    fun  getDeviceToken():String? {
        return deviceToken;
    }

  fun setDeviceToken(deviceToken:String) {
        this.deviceToken = deviceToken;
    }

    fun  getDeviceType():String? {
        return deviceType;
    }

  fun setDeviceType(deviceType:String) {
        this.deviceType = deviceType;
    }



    fun  getStatus():String? {
        return status;
    }

    fun setStatus(status:String) {
        this.status = status;
    }

    fun  getCreatedAt():String? {
        return createdAt;
    }

    fun setCreatedAt(createdAt:String) {
        this.createdAt = createdAt;
    }

    fun  getUpdatedAt():String? {
        return updatedAt;
    }

    fun setUpdatedAt(updatedAt:String) {
        this.updatedAt = updatedAt;
    }

}