package com.advocatediary.model.caseDetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaseDetailClientDetail {
    @SerializedName("first_name")
    @Expose
    private lateinit var firstName:String
    @SerializedName("last_name")
    @Expose
    private lateinit var lastName:String
    @SerializedName("full_name")
    @Expose
    private lateinit var  fullName:String
    @SerializedName("gender")
    @Expose
    private lateinit var gender:String
    @SerializedName("image")
    @Expose
    private lateinit var image:String
    @SerializedName("phone")
    @Expose
    private lateinit var phone:String
    @SerializedName("email")
    @Expose
    private lateinit var email:String
    @SerializedName("address")
    @Expose
    private  lateinit var address:String

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

    fun  getGender():String? {
        return gender;
    }

   fun setGender(gender:String) {
        this.gender = gender;
    }

    fun  getImage():String? {
        return image;
    }

    fun setImage(image:String) {
        this.image = image;
    }

    fun  getPhone():String? {
        return phone;
    }

    fun setPhone(phone:String) {
        this.phone = phone;
    }

    fun  getEmail():String? {
        return email;
    }

    fun setEmail(email:String) {
        this.email = email;
    }

    fun  getAddress():String? {
        return address;
    }

    fun setAddress(address:String) {
        this.address = address;
    }


}