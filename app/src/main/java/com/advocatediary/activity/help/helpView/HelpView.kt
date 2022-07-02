package com.advocatediary.activity.help.helpView

import android.app.Activity

interface HelpView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity, message: String)
}