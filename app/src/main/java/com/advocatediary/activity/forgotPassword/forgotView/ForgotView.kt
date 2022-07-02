package com.advocatediary.activity.forgotPassword.forgotView

import android.app.Activity

interface ForgotView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
    fun callMessageData(phone:String)
}