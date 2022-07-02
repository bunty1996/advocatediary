package com.advocatediary.model.login

import com.google.gson.annotations.SerializedName

class LoginData {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("role_id")
    var roleId: String? = null
    @SerializedName("advocate_id")
    var advocateId: String? = null
    @SerializedName("state_id")
    var stateId: String? = null
    @SerializedName("district_id")
    var districtId: String? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("alternate_phone")
    var alternatePhone: String? = null
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("last_name")
    var lastName: String? = null
    @SerializedName("full_name")
    var fullName: String? = null
    @SerializedName("slug")
    var slug: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("address")
    var address: String? = null
    @SerializedName("expertise")
    var expertise: String? = null
    @SerializedName("office_address")
    var officeAddress: String = ""
    @SerializedName("office_phone")
    var officePhone: String? = null
    @SerializedName("device_token")
    var deviceToken: String? = null
    @SerializedName("device_type")
    var device_type: String? = null
    @SerializedName("status")
    var status: String? = null


}