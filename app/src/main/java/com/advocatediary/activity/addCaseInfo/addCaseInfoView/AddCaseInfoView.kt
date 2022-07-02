package com.advocatediary.activity.addCaseInfo.addCaseInfoView

import android.app.Activity
import com.advocatediary.model.caseDetail.CaseDetailData
import com.advocatediary.model.caseType.CaseTypeDatum
import com.advocatediary.model.getJudges.GetJudgesDatum
import com.advocatediary.model.stateDistricts.StateDistrictDatum

interface AddCaseInfoView {
    fun showDialog(activity: Activity)
    fun hideDialog()
    fun showMessage(activity: Activity, message: String)
    fun setStateDistricts(stateDistrictDatum: ArrayList<StateDistrictDatum>?)
    fun setAllJudges(judgesList: ArrayList<GetJudgesDatum>?)
    fun setAllCaseTypes(listCaseType:ArrayList<CaseTypeDatum>?)

    fun getCaseDetail(detail: CaseDetailData)
}