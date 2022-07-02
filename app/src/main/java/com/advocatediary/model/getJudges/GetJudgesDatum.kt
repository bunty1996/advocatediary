package com.advocatediary.model.getJudges

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetJudgesDatum {
    @SerializedName("id")
    @Expose
    private lateinit var id:Integer
    @SerializedName("first_name")
    @Expose
    private lateinit var firstName:String
    @SerializedName("last_name")
    @Expose
    private lateinit var lastName:String
    @SerializedName("email")
    @Expose
    private lateinit var email:String
    @SerializedName("address")
    @Expose
    private lateinit var address:String
    @SerializedName("phone")
    @Expose
    private lateinit var  phone:String

    fun  getId():Integer?
    {
        return id;
    }

    fun setId(id:Integer)
    {
        this.id = id;
    }

    fun  getFirstName():String?
    {
        return firstName;
    }

    fun setFirstName(firstName:String)
    {
        this.firstName = firstName;
    }

    fun  getLastName():String
    {
        return lastName;
    }

   fun setLastName(lastName:String)
    {
        this.lastName = lastName;
    }

    fun  getEmail():String?
    {
        return email;
    }

    fun setEmail(email:String)
    {
        this.email = email;
    }

    fun  getAddress():String?
    {
        return address;
    }

   fun setAddress(address:String)
    {
        this.address = address;
    }

    fun  getPhone():String?
    {
        return phone;
    }

   fun setPhone(phone:String)
    {
        this.phone = phone;
    }
}