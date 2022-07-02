package com.advocatediary.activity.phoneVerification.phoneVerifyView

import android.app.Activity

interface PhoneVerifyView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
}