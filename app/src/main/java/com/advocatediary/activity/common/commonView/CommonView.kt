package com.advocatediary.activity.common.commonView

import android.app.Activity

interface CommonView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
}