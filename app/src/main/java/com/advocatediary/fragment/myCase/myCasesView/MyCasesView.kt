package com.advocatediary.fragment.myCase.myCasesView

import android.app.Activity

interface MyCasesView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
}