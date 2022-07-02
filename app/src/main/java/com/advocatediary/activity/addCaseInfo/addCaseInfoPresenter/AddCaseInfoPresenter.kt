package com.advocatediary.activity.addCaseInfo.addCaseInfoPresenter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import com.advocatediary.activity.addCaseDetail.AddCaseDetailActivity
import com.advocatediary.activity.addCaseInfo.addCaseInfoView.AddCaseInfoView
import com.advocatediary.adapter.RVCaseDetailPaymentAdapter
import com.advocatediary.adapter.RVCaseHistoryAdapter
import com.advocatediary.handler.CaseDetailHandler
import com.advocatediary.handler.CaseTypesHandler
import com.advocatediary.handler.GetJudgesHandler
import com.advocatediary.handler.StateDistrictHandler
import com.advocatediary.model.caseDetail.CaseDetailExample
import com.advocatediary.model.caseType.CaseTypeDatum
import com.advocatediary.model.caseType.CaseTypeExample
import com.advocatediary.model.getJudges.GetJudgeExample
import com.advocatediary.model.getJudges.GetJudgesDatum
import com.advocatediary.model.stateDistricts.StateDistrictDatum
import com.advocatediary.model.stateDistricts.StateDistrictExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.advocatediary.utils.WebServices
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.content_add_case_info.*
import org.json.JSONObject

class AddCaseInfoPresenter(
    var activity: Activity, var addCaseInfoView: AddCaseInfoView, var case_id: String,
    var scroll_add_case_info: ScrollView,var bt_add_client11:Button
) {

    private lateinit var tv_casetype_selection: TextView

    private lateinit var tv_state_name: TextView

    private lateinit var tv_district_name: TextView

    private lateinit var tv_court_name: TextView

    private lateinit var tv_court_number: TextView

    private lateinit var et_section: EditText

    private lateinit var tv_judge_name__name: TextView

    private lateinit var et_case_info_other_party: EditText

    private lateinit var et_other_party_lawyer_name: EditText

    private lateinit var stateId: String

    private var stateIndex: Int = 0

    private var districtId: String? = ""
    private var courtId: String? = ""
    private var courtNumberId: String? = ""

    private var districtIndex: Int = 0

    private var judgeId: String? = ""

    private var caseTypeId: String? = ""

    private var courtIndex: Int = 0

    fun hitAddCaseInfoMethod(
        tv_casetype_selection: TextView,
        tv_state_name: TextView,
        tv_district_name: TextView,
        tv_court_name: TextView,
        tv_court_number: TextView,
        et_section: EditText,
        tv_judge_name__name: TextView,
        et_case_info_other_party: EditText,
        et_other_party_lawyer_name: EditText, client_id: String, case_id: String
    ) {
        this.tv_casetype_selection = tv_casetype_selection
        this.tv_state_name = tv_state_name
        this.tv_district_name = tv_district_name
        this.tv_court_name = tv_court_name
        this.tv_court_number = tv_court_number

        this.et_section = et_section
        this.tv_judge_name__name = tv_judge_name__name
        this.et_case_info_other_party = et_case_info_other_party
        this.et_other_party_lawyer_name = et_other_party_lawyer_name
        this.case_id = case_id

        /*  var intent = Intent(activity, AddCaseDetailActivity::class.java)
          activity.startActivity(intent)*/

        if (checkValidation()) {
            Utils.hideSoftKeyboard(activity)

            for ((idx1, value) in stateList.withIndex()) {
                if (tv_state_name.text.toString().trim().equals(stateList.get(idx1).getTitle())) {
                    stateId = stateList.get(idx1).getId().toString()
                    stateIndex = idx1
                    break;
                }
            }

            for ((idx2, value) in stateList.get(stateIndex).getDistricts().withIndex()) {
                if (tv_district_name.text.toString().trim().equals(stateList.get(stateIndex).getDistricts().get(idx2).getTitle())) {
                    districtId = stateList.get(stateIndex).getDistricts().get(idx2).getId().toString()
                    districtIndex = idx2
                    break;
                }
            }

            for ((idx3, value) in stateList.get(stateIndex).getDistricts().get(districtIndex).getCourts()!!.withIndex()) {
                if (tv_court_name.text.toString().trim().equals(value.getCourtTitle())) {
                    courtId = value.getId().toString()
                    courtIndex = idx3
                    break;
                }
            }

            for ((idx, value) in stateList.get(stateIndex).getDistricts().get(districtIndex).getCourts()!!.get(
                courtIndex
            ).getCourtNumbers()!!.withIndex()) {
                if (tv_court_number.text.toString().equals(value.getValue())) {
                    courtNumberId = value.getId().toString()
                    break
                }
            }




            for ((idx1, value) in allJudgesList.withIndex()) {
                if (tv_judge_name__name.text.toString().trim().equals(value.getFirstName() + " " + value.getLastName())) {
                    judgeId = value.getId().toString()
                    break;
                }
            }


            for ((idx, value) in caseTypeList.withIndex()) {
                if (tv_casetype_selection.text.toString().trim().equals(value.getCaseType())) {
                    caseTypeId = value.getId().toString()
                }
            }

            var jsonObject = JSONObject()
            jsonObject.put("case_type_id", caseTypeId)
            jsonObject.put("judgeId", judgeId)
            jsonObject.put("courtNumberId", courtNumberId)
            jsonObject.put("courtId", courtId)
            jsonObject.put("districtId", districtId)
            jsonObject.put("stateId", stateId)

            jsonObject.put("section", et_section.text.toString().trim())
            jsonObject.put("et_case_info_other_party", et_case_info_other_party.text.toString().trim())
            jsonObject.put("et_other_party_lawyer_name", et_other_party_lawyer_name.text.toString().trim())
            jsonObject.put("client_id", client_id)
            jsonObject.put("case_id", case_id)


            var intent = Intent(activity, AddCaseDetailActivity::class.java)
            intent.putExtra("jsonObject", jsonObject.toString())
            // intent.putExtra("")
            activity.startActivity(intent)
            Utils.screenOpenCloseAnimation(activity)
        }
    }

    fun checkValidation(): Boolean {
        if (tv_casetype_selection.text.toString().trim().length == 0) {
            tv_casetype_selection.setError("Please select case type")
            return false
        } else
            if (tv_state_name.text.toString().trim().length == 0) {
                tv_state_name.setError("Please select state")
                return false
            } else
                if (tv_district_name.text.toString().trim().length == 0) {
                    tv_district_name.setError("Please select district")
                    return false
                } else
                    if (tv_court_name.text.toString().trim().length == 0) {
                        tv_court_name.setError("Please select court name")
                        return false
                    } else
                        if (tv_court_number.text.toString().trim().length == 0) {
                            tv_court_number.setError("Please select court number")
                            return false
                        }
        return true;
    }

    private lateinit var stateValue: String
    private lateinit var stateList: ArrayList<StateDistrictDatum>
    fun getStateDistrictMethod() {
        addCaseInfoView.showDialog(activity)
        stateValue = ""
        WebServices.Factory1.getInstance()!!.getStateDistrictMethod(object : StateDistrictHandler {

            override fun onSuccess(stateDistrictExample: StateDistrictExample) {
                stateValue = "set"
                stopLoader();
                if (stateDistrictExample != null) {
                    addCaseInfoView.setStateDistricts(stateDistrictExample.getResponse()!!.getData())

                    stateList = stateDistrictExample.getResponse()!!.getData()!!
                }
            }

            override fun onError(message: String) {
                stateValue = "set"
                stopLoader();
            }

            /*  fun onSuccess(stateDistrictExample: StateDistrictExample?) {
                  courtValueLoader = "1"
                  if (stateDistrictExample != null) {
                      viewCaseInfo.setStateDistrictData(stateDistrictExample!!.getResponse().getData())
                  }
                  setLoadingValue()

                  if (case_id.equals("", ignoreCase = true)) {
                      val stateName = ""
                      var selectedState = 0
                      for (idx in 0 until stateDistrictExample!!.getResponse().getData().size()) {

                          if (stateDistrictExample!!.getResponse().getData().get(idx).getId().equalsIgnoreCase(
                                  CSPreferences.readString(activity, "user_state").toString()
                              )
                          ) {
                              tv_state_name.setText(stateDistrictExample!!.getResponse().getData().get(idx).getTitle())
                              selectedState = idx
                              break
                          }
                      }

                      for (idx1 in 0 until stateDistrictExample!!.getResponse().getData().get(selectedState).getDistricts().size()) {
                          if (stateDistrictExample!!.getResponse().getData().get(selectedState).getDistricts().get(idx1).getId().equalsIgnoreCase(
                                  CSPreferences.readString(activity, "user_district").toString()
                              )
                          ) {

                              tv_district_name.setText(
                                  stateDistrictExample!!.getResponse().getData().get(selectedState).getDistricts().get(
                                      idx1
                                  ).getTitle()
                              )
                              break
                          }
                      }
                  }
              }

              fun onError(message: String) {

              }*/
        })
    }

    fun stopLoader() {
        if (stateValue.equals("set") && judgesCheck.equals("set") && caseTypeCheck.equals("set")) {
            //addCaseInfoView.hideDialog()
            if (case_id.equals("")) {
                scroll_add_case_info.visibility = View.VISIBLE
                bt_add_client11.visibility = View.VISIBLE
                addCaseInfoView.hideDialog()
            } else {
                getCaseDetail(case_id)
            }
        }
    }

    private lateinit var judgesCheck: String

    private lateinit var allJudgesList: ArrayList<GetJudgesDatum>

    // for get all judges
    fun getAllJudges() {
        judgesCheck = ""
        WebServices.Factory1.getInstance()?.getAllJudgesMethod(object : GetJudgesHandler {
            override fun onSuccess(example: GetJudgeExample) {
                judgesCheck = "set"
                stopLoader()
                if (example != null) {
                    addCaseInfoView.setAllJudges(example.getResponse()!!.getData())
                    allJudgesList = example.getResponse()!!.getData()!!

                }
            }

            override fun onError(message: String) {
                judgesCheck = "set"
                stopLoader()
            }
        })
    }

    private lateinit var caseTypeCheck: String

    private lateinit var caseTypeList: ArrayList<CaseTypeDatum>

    fun getAllCaseTypes() {
        caseTypeCheck = ""
        WebServices.Factory1.getInstance()?.getAllCaseTypesMethod(object : CaseTypesHandler {
            override fun onSuccess(example: CaseTypeExample) {
                caseTypeCheck = "set"
                stopLoader()
                if (example != null) {
                    addCaseInfoView.setAllCaseTypes(example.getResponse()!!.getData())
                    caseTypeList = example.getResponse()!!.getData()!!
                }
            }

            override fun onError(message: String) {
                caseTypeCheck = "set"
                stopLoader()
            }

        })
    }

    // for get case detail
    fun getCaseDetail(case_id: String) {
        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.getCaseDetailMethod(userId, case_id, 1, object : CaseDetailHandler {
            override fun onSuccess(example: CaseDetailExample) {
                addCaseInfoView.hideDialog()
                scroll_add_case_info.visibility = View.VISIBLE
                bt_add_client11.visibility = View.VISIBLE
                if (example != null) {
                    addCaseInfoView.getCaseDetail(example.getResponse()!!.getData()!!)

                }
            }

            override fun onError(mesage: String) {
                addCaseInfoView.hideDialog()
            }
        })
    }

}