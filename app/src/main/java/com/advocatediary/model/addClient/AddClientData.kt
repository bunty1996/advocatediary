package com.advocatediary.model.addClient

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddClientData {
    @SerializedName("email")
    @Expose
    private  var email: String=""
    @SerializedName("first_name")
    @Expose
    private  var firstName: String=""
    @SerializedName("address")
    @Expose
    private  var address: String=""

    @SerializedName("gender")
    @Expose
    private lateinit var gender: String

    @SerializedName("phone")
    @Expose
    private lateinit var phone: String
    @SerializedName("last_name")
    @Expose
    private lateinit var lastName: String

    @SerializedName("full_name")
    @Expose
    private lateinit var fullName: String
    @SerializedName("role_id")
    @Expose
    private lateinit var roleId: Integer

    @SerializedName("advocate_id")
    @Expose
    private lateinit var advocateId: String

    @SerializedName("updated_at")
    @Expose
    private lateinit var updatedAt: String
    @SerializedName("created_at")
    @Expose
    private lateinit var createdAt: String
    @SerializedName("id")
    @Expose
    private lateinit var id: Integer

    fun getEmail(): String? {
        return email;
    }

    fun setEmail(email: String) {
        this.email = email;
    }

    fun getFirstName(): String? {
        return firstName;
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName;
    }

    fun getAddress(): String? {
        return address;
    }

    fun setAddress(address: String) {
        this.address = address;
    }

    fun getGender(): String? {
        return gender;
    }

    fun setGender(gender: String) {
        this.gender = gender;
    }

    fun getPhone(): String? {
        return phone;
    }

    fun setPhone(phone: String) {
        this.phone = phone;
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

    fun getRoleId(): Integer? {
        return roleId;
    }

    fun setRoleId(roleId: Integer) {
        this.roleId = roleId;
    }

    fun getAdvocateId(): String {
        return advocateId;
    }

    fun setAdvocateId(advocateId: String) {
        this.advocateId = advocateId;
    }

    fun getUpdatedAt(): String? {
        return updatedAt;
    }

    fun setUpdatedAt(updatedAt: String) {
        this.updatedAt = updatedAt;
    }

    fun getCreatedAt(): String? {
        return createdAt;
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt;
    }

    fun getId(): Integer {
        return id;
    }

    fun setId(id: Integer) {
        this.id = id;
    }

}