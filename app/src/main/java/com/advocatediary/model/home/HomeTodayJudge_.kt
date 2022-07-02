package com.advocatediary.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeTodayJudge_
{
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("first_name")
    @Expose
    private var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    private var lastName: String? = null
    @SerializedName("address")
    @Expose
    private var address: String? = null
    @SerializedName("phone")
    @Expose
    private var phone: String? = null
    @SerializedName("email")
    @Expose
    private var email: String? = null
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

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
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