package com.advocatediary.activity.allClient.allClientView

import android.app.Activity

interface AllClientView
{
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
}