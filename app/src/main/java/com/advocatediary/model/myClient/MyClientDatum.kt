package com.advocatediary.model.myClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyClientDatum {
    @SerializedName("id")
    @Expose
    private lateinit var id: String;
    @SerializedName("first_name")
    @Expose
    private lateinit var firstName: String;
    @SerializedName("last_name")
    @Expose
    private lateinit var lastName: String
    @SerializedName("full_name")
    @Expose
    private lateinit var fullName: String;
    @SerializedName("email")
    @Expose
    private lateinit var email: String
    @SerializedName("gender")
    @Expose
    private lateinit var gender: String
    @SerializedName("address")
    @Expose
    private lateinit var address: String
    @SerializedName("phone")
    @Expose
    private lateinit var phone: String
    @SerializedName("image")
    @Expose
    private lateinit var image: String
    @SerializedName("total_case")
    @Expose
    private lateinit var totalCase: Integer;

    fun getId(): String? {
        return id;
    }

    fun setId(id: String) {
        this.id = id;
    }

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

    fun getEmail(): String {
        return email;
    }

    fun setEmail(email: String) {
        this.email = email;
    }

    fun getGender(): String? {
        return gender;
    }

    fun setGender(gender: String) {
        this.gender = gender;
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

    fun getImage(): String? {
        return image;
    }

    fun setImage(image: String) {
        this.image = image;
    }

    fun getTotalCase(): Integer? {
        return totalCase;
    }

    fun setTotalCase(totalCase: Integer) {
        this.totalCase = totalCase;
    }

}