package com.advocatediary.model.changePassword

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePasswordData {

    @SerializedName("user_id")
    @Expose
    private lateinit var userId: String

    fun getUserId(): String? {
        return userId;
    }

    fun setUserId(userId: String) {
        this.userId = userId;
    }
}