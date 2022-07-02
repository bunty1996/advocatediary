package com.advocatediary.activity.resetPassword.resetPasswordView

import android.app.Activity

interface ResetPasswordView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
}