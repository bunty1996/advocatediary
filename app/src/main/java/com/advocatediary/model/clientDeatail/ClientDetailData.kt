package com.advocatediary.model.clientDeatail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClientDetailData {
    @SerializedName("first_name")
    @Expose
    private  var firstName: String?=""
    @SerializedName("last_name")
    @Expose
    private  var lastName: String?=""
    @SerializedName("full_name")
    @Expose
    private  var fullName: String?=""
    @SerializedName("gender")
    @Expose
    private  var gender: String?=""
    @SerializedName("email")
    @Expose
    private  var email: String?=""
    @SerializedName("image")
    @Expose
    private lateinit var image: String
    @SerializedName("address")
    @Expose
    private  var address: String?=""
    @SerializedName("phone")
    @Expose
    private  var phone: String?=""

    fun getFirstName(): String? {
        return firstName;
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName;
    }

    fun getLastName(): String? {
        return lastName;
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName;
    }

    fun getFullName(): String? {
        return fullName;
    }

    fun setFullName(fullName: String) {
        this.fullName = fullName;
    }

    fun getGender(): String? {
        return gender;
    }

    fun setGender(gender: String) {
        this.gender = gender;
    }

    fun getEmail(): String? {
        return email;
    }

    fun setEmail(email: String) {
        this.email = email;
    }

    fun getImage(): String {
        return image;
    }

    fun setImage(image: String) {
        this.image = image;
    }

    fun getAddress(): String? {
        return address;
    }

    fun setAddress(address: String) {
        this.address = address;
    }

    fun getPhone(): String? {
        return phone;
    }

    fun setPhone(phone: String) {
        this.phone = phone;
    }

}