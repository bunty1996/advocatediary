package com.advocatediary.activity.changePassword.changeView

import android.app.Activity

interface ChangeView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
}