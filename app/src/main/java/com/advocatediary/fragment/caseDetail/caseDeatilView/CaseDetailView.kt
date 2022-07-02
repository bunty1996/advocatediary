package com.advocatediary.fragment.caseDetail.caseDeatilView

import android.app.Activity
import com.advocatediary.model.caseDetail.CaseDetailClientDetail
import com.advocatediary.model.caseDetail.CaseDetailData
import com.advocatediary.model.casePurposes.CasePurposesDatum

interface CaseDetailView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity, message: String)
    fun setClientData(detail: CaseDetailData)
    fun setPurposeList(purposeList:List<CasePurposesDatum>)
}