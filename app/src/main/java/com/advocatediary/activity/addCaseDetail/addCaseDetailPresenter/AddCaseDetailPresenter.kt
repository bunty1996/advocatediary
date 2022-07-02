package com.advocatediary.activity.addCaseDetail.addCaseDetailPresenter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import com.advocatediary.activity.addCaseDetail.addCaseDetailView.AddCaseDetailView
import com.advocatediary.activity.home.HomeActivity
import com.advocatediary.handler.AddCaseHandler
import com.advocatediary.handler.CaseDetailHandler
import com.advocatediary.handler.CasePurposesHandler
import com.advocatediary.handler.CaseUpdateHandler
import com.advocatediary.model.addCases.AddCaseExample
import com.advocatediary.model.caseDetail.CaseDetailExample
import com.advocatediary.model.casePurposes.CasePurposesDatum
import com.advocatediary.model.casePurposes.CasePurposesExample
import com.advocatediary.model.caseUpdate.CaseUpdateExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices
import com.google.gson.JsonObject
import org.json.JSONObject

class AddCaseDetailPresenter(
    var activity: Activity,
    var addCaseDetailView: AddCaseDetailView,
    var case_id: String,
    var scrollview_add_case_detail: ScrollView,var bt_add_case_submit111:Button
) {
    private lateinit var purposeList: List<CasePurposesDatum>


    fun getAllPuposesMethod() {

        addCaseDetailView.showDialog(activity)
        WebServices.Factory1.getInstance()?.getAllPurposesMthod(object : CasePurposesHandler {

            override fun onSuccess(example: CasePurposesExample) {
                // addCaseDetailView.hideDialog()
                if (example != null) {
                    if (!(case_id.equals(""))) {
                        getCaseDetail(case_id)
                    } else {
                        scrollview_add_case_detail.visibility = View.VISIBLE
                        bt_add_case_submit111.visibility = View.VISIBLE
                    }
                    addCaseDetailView.setCasePurposesList(activity, example.getResponse()!!.getData()!!.getData()!!)
                    purposeList = example.getResponse()!!.getData()!!.getData()!!

                }
            }

            override fun onError(message: String) {
                addCaseDetailView.hideDialog()
            }
        })
    }

    private lateinit var et_total_fee: EditText

    private lateinit var tv_purpose: TextView

    private lateinit var tv_case_filing: TextView

    private lateinit var tv_next_date: TextView

    private lateinit var et_advance_fee: EditText

    private lateinit var et_notess: EditText

    private lateinit var purposeId: String

    fun addCaseMethod(
        et_total_fee: EditText, tv_purpose: TextView, tv_case_filing: TextView,
        tv_next_date: TextView, et_advance_fee: EditText, et_notess: EditText, caseInfoData: String
    ) {
        //WebServices.Factory1.getInstance()

        this.et_total_fee = et_total_fee
        this.tv_purpose = tv_purpose
        this.tv_case_filing = tv_case_filing

        this.tv_next_date = tv_next_date
        this.et_advance_fee = et_advance_fee
        this.et_notess = et_notess

        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)

            for ((idx, value) in purposeList.withIndex()) {
                if (tv_purpose.text.toString().trim().equals(value.getName())) {
                    purposeId = value.getId().toString()
                    break;
                }
            }


            var user_id = CsPreferences.readString(activity, Constants.USER_ID)
            var jsonObject = JsonObject()
            jsonObject.addProperty("user_id", user_id)

            jsonObject.addProperty("date_of_fileing", tv_case_filing.text.toString().trim());
            jsonObject.addProperty("next_date", tv_next_date.text.toString().trim());
            jsonObject.addProperty("payment", "");

            jsonObject.addProperty("purpose_of_hearing", purposeId);

            jsonObject.addProperty("advanced_fee", et_advance_fee.text.toString().trim());
            jsonObject.addProperty("total_fee", et_total_fee.text.toString().trim());

            jsonObject.addProperty("notes", et_notess.text.toString().trim());
            jsonObject.addProperty("place", "");


            var jsonObject2 = JSONObject(caseInfoData)

            jsonObject.addProperty("case_type_id", jsonObject2.getString("case_type_id"))
            jsonObject.addProperty("judge_id", jsonObject2.getString("judgeId"))
            jsonObject.addProperty("court_number_id", jsonObject2.getString("courtNumberId"))
            jsonObject.addProperty("court_id", jsonObject2.getString("courtId"))
            jsonObject.addProperty("district_id", jsonObject2.getString("districtId"))
            jsonObject.addProperty("state_id", jsonObject2.getString("stateId"))

            jsonObject.addProperty("section", jsonObject2.getString("section"))
            jsonObject.addProperty("opposite_party", jsonObject2.getString("et_case_info_other_party"))
            jsonObject.addProperty("opposite_lawyer", jsonObject2.getString("et_other_party_lawyer_name"))
            jsonObject.addProperty("client_id", jsonObject2.getString("client_id"))

            addCaseDetailView.showDialog(activity)
            WebServices.Factory1.getInstance()!!.addCaseMethod(jsonObject, object : AddCaseHandler {
                override fun onSuccess(addCaseExample: AddCaseExample) {
                    addCaseDetailView.hideDialog()
                    if (addCaseExample != null) {

                        /* var intent2 = Intent()
                         intent2.action = "case_update"
                         activity.sendBroadcast(intent2)

                         var intent = Intent(activity, HomeActivity::class.java)
                         activity.startActivity(intent)
                         activity.finishAffinity()*/

                        var intent2 = Intent()
                        intent2.action = Constants.CASE_DETAIL_UPDATE
                        activity.sendBroadcast(intent2)

                        var intent3 = Intent()
                        intent3.action = Constants.HOME_UPDATE
                        activity.sendBroadcast(intent3)

                        var intent4 = Intent()
                        intent4.action = Constants.MY_CASE_UPDATE
                        activity.sendBroadcast(intent4)

                        var intent5 = Intent()
                        intent5.action = Constants.MY_CLIENT_UPDATE
                        activity.sendBroadcast(intent5)

                        var intent6 = Intent()
                        intent6.action = Constants.CLIENT_DETAIL_UPDATE
                        activity.sendBroadcast(intent6)

                        var intent7 = Intent()
                        intent7.action = "case_update"
                        activity.sendBroadcast(intent7)

                        activity.finish()
                        Utils.screenOpenCloseAnimation(activity)
                    }
                }

                override fun onError(message: String) {
                    addCaseDetailView.hideDialog()
                }

            })
        }
    }

    fun checkValidation(): Boolean {
        if (et_total_fee.getText().toString().trim().length == 0) {
            et_total_fee.setError("Please enter total fee")
            return false
        } else
            if (tv_purpose.getText().toString().trim().length == 0) {
                tv_purpose.setError("Please select purpose")
                return false
            } else
                if (tv_case_filing.getText().toString().trim().length == 0) {
                    tv_case_filing.setError("Please select filing date")
                    return false
                } else
                    if (tv_next_date.getText().toString().trim().length == 0) {
                        tv_next_date.setError("Please select next date")
                        return false
                    }
        return true
    }


    fun getCaseDetail(case_id: String) {
        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.getCaseDetailMethod(userId, case_id, 1, object : CaseDetailHandler {
            override fun onSuccess(example: CaseDetailExample) {
                addCaseDetailView.hideDialog()
                scrollview_add_case_detail.visibility = View.VISIBLE
                bt_add_case_submit111.visibility = View.VISIBLE
                if (example != null) {
                    addCaseDetailView.caseDetail(example.getResponse()!!.getData()!!)

                }
            }

            override fun onError(mesage: String) {
                addCaseDetailView.hideDialog()
            }
        })
    }


    fun editCaseMethod(
        et_total_fee: EditText, tv_purpose: TextView, tv_case_filing: TextView,
        tv_next_date: TextView, et_advance_fee: EditText, et_notess: EditText, caseInfoData: String,
        case_id: String, previousCaseDate: String
    ) {
        //WebServices.Factory1.getInstance()

        this.et_total_fee = et_total_fee
        this.tv_purpose = tv_purpose
        this.tv_case_filing = tv_case_filing

        this.tv_next_date = tv_next_date
        this.et_advance_fee = et_advance_fee
        this.et_notess = et_notess

        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)

            for ((idx, value) in purposeList.withIndex()) {
                if (tv_purpose.text.toString().trim().equals(value.getName())) {
                    purposeId = value.getId().toString()
                    break;
                }
            }

            var user_id = CsPreferences.readString(activity, Constants.USER_ID)
            var jsonObject = JsonObject()
            jsonObject.addProperty("user_id", user_id)
            jsonObject.addProperty("case_id", case_id)

            jsonObject.addProperty("date_of_fileing", tv_case_filing.text.toString().trim());
            jsonObject.addProperty("case_next_date", tv_next_date.text.toString().trim());
            jsonObject.addProperty("payment", "");

            jsonObject.addProperty("purpose_of_hearing", purposeId);

            /* jsonObject.addProperty("advanced_fee", et_advance_fee.text.toString().trim());
             jsonObject.addProperty("total_fee", et_total_fee.text.toString().trim());*/

            jsonObject.addProperty("notes", et_notess.text.toString().trim());
            jsonObject.addProperty("place", "");


            var jsonObject2 = JSONObject(caseInfoData)

            jsonObject.addProperty("case_type_id", jsonObject2.getString("case_type_id"))
            jsonObject.addProperty("judge_id", jsonObject2.getString("judgeId"))
            jsonObject.addProperty("court_number_id", jsonObject2.getString("courtNumberId"))
            jsonObject.addProperty("court_id", jsonObject2.getString("courtId"))
            jsonObject.addProperty("district_id", jsonObject2.getString("districtId"))
            jsonObject.addProperty("state_id", jsonObject2.getString("stateId"))

            jsonObject.addProperty("section", jsonObject2.getString("section"))
            jsonObject.addProperty("opposite_party", jsonObject2.getString("et_case_info_other_party"))
            jsonObject.addProperty("opposite_lawyer", jsonObject2.getString("et_other_party_lawyer_name"))
            //   jsonObject.addProperty("client_id", jsonObject2.getString("client_id"))

            jsonObject.addProperty("important_date_status", "")
            jsonObject.addProperty("previous_case_date", previousCaseDate)


            addCaseDetailView.showDialog(activity)
            WebServices.Factory1.getInstance()!!.editCaseMethod(jsonObject, object : CaseUpdateHandler {
                override fun onSuccess(addCaseExample: CaseUpdateExample) {
                    addCaseDetailView.hideDialog()
                    if (addCaseExample != null) {

                        var intent1 = Intent()
                        intent1.action = "case_update"
                        activity.sendBroadcast(intent1)


                        var intent2 = Intent()
                        intent2.action = Constants.CASE_DETAIL_UPDATE
                        activity.sendBroadcast(intent2)

                        var intent3 = Intent()
                        intent3.action = Constants.HOME_UPDATE
                        activity.sendBroadcast(intent3)

                        var intent4 = Intent()
                        intent4.action = Constants.MY_CASE_UPDATE
                        activity.sendBroadcast(intent4)

                        var intent5 = Intent()
                        intent5.action = Constants.MY_CLIENT_UPDATE
                        activity.sendBroadcast(intent5)

                        var intent6 = Intent()
                        intent6.action = Constants.CLIENT_DETAIL_UPDATE
                        activity.sendBroadcast(intent6)

                        /*   var intent3 = Intent()
                           intent3.action = Constants.MY_CASE_UPDATE
                           activity.sendBroadcast(intent3)

                           var intent4 = Intent()
                           intent4.action = Constants.CLIENT_DETAIL_UPDATE
                           activity.sendBroadcast(intent4)*/


                        activity.finish()
                        Utils.screenOpenCloseAnimation(activity)
                        /*  var intent = Intent(activity, HomeActivity::class.java)
                          activity.startActivity(intent)
                          activity.finishAffinity()*/
                    }
                }

                override fun onError(message: String) {
                    addCaseDetailView.hideDialog()
                }

            })
        }
    }
}