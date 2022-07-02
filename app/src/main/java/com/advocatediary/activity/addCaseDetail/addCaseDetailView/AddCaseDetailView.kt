package com.advocatediary.activity.addCaseDetail.addCaseDetailView

import android.app.Activity
import com.advocatediary.model.caseDetail.CaseDetailData
import com.advocatediary.model.casePurposes.CasePurposesDatum

interface AddCaseDetailView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity, message: String)
    fun setCasePurposesList(activity: Activity, message: List<CasePurposesDatum>?)
    fun caseDetail(caseDetailData: CaseDetailData)
}