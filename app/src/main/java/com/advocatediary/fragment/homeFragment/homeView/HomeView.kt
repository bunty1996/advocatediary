package com.advocatediary.fragment.homeFragment.homeView

import android.app.Activity
import com.advocatediary.model.casePurposes.CasePurposesDatum

interface HomeView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity,message:String)
    fun setPurposeList(purposeList:List<CasePurposesDatum>?)
    fun setEmptyValues(selectedPosition:Int)
}