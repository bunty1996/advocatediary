package com.advocatediary.fragment.myClient.myClientView

import android.app.Activity
import android.os.Message

interface MyClientView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message: String)
}