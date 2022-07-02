package com.advocatediary.activity.login.loginView

import android.app.Activity

interface LoginView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity, message: String)
}