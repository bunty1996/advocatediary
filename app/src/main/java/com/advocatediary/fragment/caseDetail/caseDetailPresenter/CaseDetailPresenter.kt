package com.advocatediary.fragment.caseDetail.caseDetailPresenter

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.advocatediary.adapter.RVCaseDetailPaymentAdapter
import com.advocatediary.adapter.RVCaseHistoryAdapter
import com.advocatediary.fragment.caseDetail.caseDeatilView.CaseDetailView
import com.advocatediary.handler.CaseDetailHandler
import com.advocatediary.handler.CasePurposesHandler
import com.advocatediary.handler.CaseUpdateHandler
import com.advocatediary.model.caseDetail.CaseDetailExample
import com.advocatediary.model.casePurposes.CasePurposesDatum
import com.advocatediary.model.casePurposes.CasePurposesExample
import com.advocatediary.model.caseUpdate.CaseUpdateExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices

class CaseDetailPresenter(
    var activity: Activity,
    var caseDetailView: CaseDetailView,
    var rv_payment_history: RecyclerView, var rv_case_history: RecyclerView, var bt_update_case: Button,var tv_advance_fee_data:TextView
) {
    private lateinit var adapter: RVCaseDetailPaymentAdapter

    fun getCaseDetail(case_id: String) {
        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.getCaseDetailMethod(userId, case_id, 1, object : CaseDetailHandler {
            override fun onSuccess(example: CaseDetailExample) {
                caseDetailView.hideDialog()
                if (example != null) {
                    caseDetailView.setClientData(example.getResponse()!!.getData()!!)

                    adapter = RVCaseDetailPaymentAdapter(
                        activity,
                        example.getResponse()!!.getData()!!.getCasePaymentHistoryArr()!!
                    )
                    rv_payment_history.layoutManager = LinearLayoutManager(activity)
                    rv_payment_history.adapter = adapter


                    var historyAdapter =
                        RVCaseHistoryAdapter(activity, example.getResponse()!!.getData()!!.getCaseAllDetail()!!)
                    rv_case_history.layoutManager = LinearLayoutManager(activity)
                    rv_case_history.adapter = historyAdapter


                    if (example.getResponse()!!.getCaseStatus()!!.equals("Pending")) {
                        bt_update_case.visibility = View.VISIBLE
                    } else {
                        bt_update_case.visibility = View.GONE
                    }

                    var totalPayment:Float=0f
                    for((idx,value) in example.getResponse()!!.getData()!!.getCasePaymentHistoryArr()!!.withIndex())
                    {
                        if(example.getResponse()!!.getData()!!.getCasePaymentHistoryArr()!!.get(idx).getDeposit().equals(null)){
                            totalPayment =
                                totalPayment + 0
                            }else {
                            totalPayment =
                                totalPayment + example.getResponse()!!.getData()!!.getCasePaymentHistoryArr()!!.get(idx).getDeposit()!!.toFloat()
                        }
                    }

                    tv_advance_fee_data.text=totalPayment.toString()
                }
            }

            override fun onError(mesage: String) {
                caseDetailView.hideDialog()
            }
        })
    }


    private lateinit var purposeList: List<CasePurposesDatum>

    fun getAllPuposesMethod(case_id: String) {

        caseDetailView.showDialog(activity)
        WebServices.Factory1.getInstance()?.getAllPurposesMthod(object : CasePurposesHandler {

            override fun onSuccess(example: CasePurposesExample) {
                getCaseDetail(case_id)
                if (example != null) {
                    // homeView.setCasePurposesList(activity, example.getResponse()!!.getData()!!.getData()!!)
                    purposeList = example.getResponse()!!.getData()!!.getData()!!
                    caseDetailView.setPurposeList(purposeList)
                }
            }

            override fun onError(message: String) {
                getCaseDetail(case_id)
            }
        })
    }

    private lateinit var tv_next_date_update: String
    private lateinit var tv_Status_selection: String
    private lateinit var et_payment_update: String
    private lateinit var et_notes: String
    private lateinit var checkValue: String

    fun updateCasesMethod(
        tv_next_date_update: String,
        tv_Status_selection: String,
        et_payment_update: String,
        et_notes: String, checkValue: String, previousCaseDate: String, CaseId: String
    ) {
        this.tv_next_date_update = tv_next_date_update
        this.tv_Status_selection = tv_Status_selection
        this.et_payment_update = et_payment_update

        this.et_notes = et_notes
        this.checkValue = checkValue

        var purposeId: String = ""

        for ((idx, value) in purposeList.withIndex()) {
            if (tv_Status_selection.trim().equals(value.getName()!!.trim())) {
                purposeId = value.getId().toString()
                break;
            }
        }

        caseDetailView.showDialog(activity)
        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.updateCasseAPICall(
            tv_next_date_update.toString().trim(),
            purposeId,
            et_payment_update.toString().trim(),
            et_notes.toString().trim(),
            checkValue, previousCaseDate, CaseId, userId, object : CaseUpdateHandler {
                override fun onSuccess(caseUpdateExample: CaseUpdateExample) {
                    //aseDetailView.hideDialog()
                    if (caseUpdateExample != null) {
                        getCaseDetail(CaseId)
                    }
                }

                override fun onError(message: String) {
                    caseDetailView.hideDialog()
                }
            })
    }
}
