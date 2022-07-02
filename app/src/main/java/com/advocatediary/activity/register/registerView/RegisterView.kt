package com.advocatediary.activity.register.registerView

import android.app.Activity
import com.advocatediary.model.state.StateDatum

interface RegisterView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
    fun setStateList(stateList: List<StateDatum>?)
    fun callForPhone(phone:String)
}