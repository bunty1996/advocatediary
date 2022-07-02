package com.advocatediary.activity.addCaseInfo

import android.app.Activity
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.advocatediary.activity.addCaseInfo.addCaseInfoPresenter.AddCaseInfoPresenter
import com.advocatediary.activity.addCaseInfo.addCaseInfoView.AddCaseInfoView
import com.advocatediary.adapter.RVCaseStateDistrictAdapter
import com.advocatediary.handler.OnclickListener
import com.advocatediary.model.caseDetail.CaseDetailData
import com.advocatediary.model.caseType.CaseTypeDatum
import com.advocatediary.model.getJudges.GetJudgesDatum
import com.advocatediary.model.state.StateSelectedData
import com.advocatediary.model.stateDistricts.StateCheckData
import com.advocatediary.model.stateDistricts.StateDistrictDatum
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_add_case_info.*
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver
import com.andexert.library.RippleView
import java.lang.Exception

class AddCaseInfoActivity : AppCompatActivity(), View.OnClickListener, AddCaseInfoView {
    override fun getCaseDetail(detail: CaseDetailData) {

        tv_casetype_selection.setText(detail.getCaseType())
        tv_state_name.setText(detail.getStateTitle())
        tv_district_name.setText(detail.getDistrictTitle())
        tv_court_name.setText(detail.getCourtTitle())
        tv_court_number.setText(detail.getCourtNumberValue())
        et_section.setText(detail.getSection())
        tv_judge_name__name.setText((detail.getJudgeFirstName()!!.trim() + " " + detail.getJudgeLastName()!!.trim()).trim())
        et_case_info_other_party.setText(detail.getOppositeParty())
        et_other_party_lawyer_name.setText(detail.getOppositeLawyer())

        client_id = detail.getClientId()!!
        tv_client_name.text = detail.getClientDetail()!!.getFullName()
        if (detail.getClientDetail()?.getEmail() != null) {
            tv_client_emails.text = detail.getClientDetail()?.getEmail()

            if (!(detail.getClientDetail()?.getEmail().equals(""))) {
                linear_add_case_info_email.visibility = View.VISIBLE
            }
        }

        et_section.setSelection(et_section.text.toString().trim().length)

        if (!(et_section.text.toString().trim().equals(""))) {
            tv_title_Section.visibility = View.VISIBLE
        } else {
            tv_title_Section.visibility = View.VISIBLE
        }


        if (tv_judge_name__name.text.toString().trim().length > 0) {
            tv_judge_name_title.visibility = View.VISIBLE
        } else {
            tv_judge_name_title.visibility = View.VISIBLE
        }

        et_case_info_other_party.setSelection(et_case_info_other_party.text.toString().trim().length)
        et_other_party_lawyer_name.setSelection(et_other_party_lawyer_name.text.toString().trim().length)
        tv_case_title_title.visibility = View.VISIBLE
        tv_state_name_title.visibility = View.VISIBLE
        tv_district_name_title.visibility = View.VISIBLE
        tv_court_name_title2.visibility = View.VISIBLE
        tv_court_number_title2.visibility = View.VISIBLE

        if (et_case_info_other_party.text.toString().trim().length > 0) {
            tv_title_other_party.visibility = View.VISIBLE
        } else {
            tv_title_other_party.visibility = View.VISIBLE
        }

        if (et_other_party_lawyer_name.text.toString().trim().length > 0) {
            tv_title_other_party_lawyer_name.visibility = View.VISIBLE
        } else {
            tv_title_other_party_lawyer_name.visibility = View.VISIBLE
        }


        var clientNameValue: String
        if (case_id.equals("")) {
            clientNameValue = clientName!!
        } else {
            clientNameValue = detail.getClientDetail()!!.getFullName()!!
        }

        val firstLetter = clientNameValue.substring(0, 1)
        tv_text_title.setText(firstLetter.toUpperCase())
        val gd = GradientDrawable()
        gd.setColor(Color.parseColor(Utils.getColor(firstLetter)))
        gd.cornerRadius = 20f
        gd.shape = GradientDrawable.OVAL
        gd.gradientType = GradientDrawable.RADIAL_GRADIENT
        tv_text_title.setBackground(gd)

        et_section.clearFocus()
        et_section.requestFocus()
    }

    private lateinit var caseTypeList: ArrayList<CaseTypeDatum>

    override fun setAllCaseTypes(caseTypeList: ArrayList<CaseTypeDatum>?) {
        this.caseTypeList = caseTypeList!!
    }

    private lateinit var judgesList: ArrayList<GetJudgesDatum>

    override fun setAllJudges(judgesList: ArrayList<GetJudgesDatum>?) {
        this.judgesList = judgesList!!
    }

    private lateinit var stateDistrictList: ArrayList<StateDistrictDatum>

    override fun setStateDistricts(stateDistrictList: ArrayList<StateDistrictDatum>?) {
        this.stateDistrictList = stateDistrictList!!;

        if (case_id.equals("")) {
            var userState = CsPreferences.Factory.readString(
                activity,
                Constants.USER_STATE
            )

            var userDistrict = CsPreferences.Factory.readString(
                activity,
                Constants.USER_DISTRICT
            )

            var stateIdx: Int = 0
            for ((idx, value) in stateDistrictList.withIndex()) {
                if (userState.trim().equals(value.getId())) {
                    tv_state_name.text = value.getTitle()
                    stateIdx = idx;
                    break

                }
            }

            for ((idx, value) in stateDistrictList.get(stateIdx).getDistricts().withIndex()) {
                if (userDistrict.equals(value.getId())) {
                    tv_district_name.text = value.getTitle()
                    break
                }
            }

        }
    }

    private lateinit var activity: Activity
    override fun onClick(v: View?) {
        when (v) {
            //  back_case_info -> finish()
            tv_casetype_selection -> selectCaseType()
            iv_down_arrow_case_type -> selectCaseType()

            tv_state_name -> selectStateType()
            iv_down_arrow_state_name -> selectStateType()

            tv_district_name -> selectDistrictType()
            iv_down_arrow_district_name -> selectDistrictType()

            tv_court_name -> selectCourtName()
            iv_down_arrow_court_name1 -> selectCourtName()

            tv_court_number -> selectCourtNumber()
            iv_down_arrow_court_number -> selectCourtNumber()

            tv_judge_name__name -> selectJudges()
            iv_down_arrow_judge_name -> selectJudges()


            bt_add_client -> submitButton()
            bt_add_client11 -> submitButton()
            tv_next_case_info -> submitButton()

            iv_email_send_info -> sendEmailClick()
            linear_status_bar -> Utils.hideSoftKeyboard(activity)
            linear_add_case_info -> Utils.hideSoftKeyboard(activity)
        }
    }

    fun sendEmailClick() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL, tv_client_emails.text.toString().trim())
        intent.putExtra(Intent.EXTRA_SUBJECT, "Advocate Diary")
        //intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.")

        startActivity(Intent.createChooser(intent, "Send Email"))
    }

    // for judges
    fun selectJudges() {
        showStateDialog(
            tv_judge_name__name,
            stateDistrictList!!,
            "State",
            tv_state_name.text.toString().trim(),
            "judge"
        )
    }

    // for select case type
    fun selectCaseType() {
        showStateDialog(
            tv_casetype_selection,
            stateDistrictList!!,
            "case_type",
            tv_casetype_selection.text.toString().trim(),
            "case_type"
        )
    }

    // for select state
    fun selectStateType() {

        showStateDialog(tv_state_name, stateDistrictList!!, "State", tv_state_name.text.toString().trim(), "state")
    }

    // for select district
    fun selectDistrictType() {

        if (checkDistrictType()) {
            showStateDialog(
                tv_district_name,
                stateDistrictList!!,
                "District",
                tv_district_name.text.toString().trim(),
                "state"
            )
        }
    }

    fun checkDistrictType(): Boolean {
        if (tv_state_name.text.toString().toString().trim().length == 0) {
            // Toast.makeText(activity, "Please select state first", Toast.LENGTH_LONG).show()
            tv_state_name.setError("Please select state first")
            return false
        }
        return true
    }

    fun checkCourtNameType(): Boolean {
        if (tv_state_name.text.toString().toString().trim().length == 0) {
            //  Toast.makeText(activity, "Please select state first", Toast.LENGTH_LONG).show()
            tv_state_name.setError("Please select state first")
            return false
        } else
            if (tv_district_name.text.toString().toString().trim().length == 0) {
                tv_district_name.setError("Please select district first")
                //  Toast.makeText(activity, "Please select district first", Toast.LENGTH_LONG).show()
                return false
            }
        return true
    }

    // for select court name
    fun selectCourtName() {
        if (checkCourtNameType()) {
            showStateDialog(
                tv_court_name,
                stateDistrictList!!,
                "Court name",
                tv_court_name.text.toString().trim(),
                "state"
            )
        }
    }

    // for select court number
    fun selectCourtNumber() {
        if (checkCourtNumber()) {
            showStateDialog(
                tv_court_number,
                stateDistrictList!!,
                "Court number",
                tv_court_number.text.toString().trim(),
                "state"
            )
        }
    }


    fun checkCourtNumber(): Boolean {
        if (tv_state_name.text.toString().toString().trim().length == 0) {
            //Toast.makeText(activity, "Please select state first", Toast.LENGTH_LONG).show()
            tv_state_name.setError("Please select state first")
            return false
        } else
            if (tv_district_name.text.toString().toString().trim().length == 0) {
                tv_district_name.setError("Please select district first")
                //  Toast.makeText(activity, "Please select district first", Toast.LENGTH_LONG).show()
                return false
            } else
                if (tv_court_name.text.toString().toString().trim().length == 0) {
                    tv_court_name.setError("Please select court name first")
                    //     Toast.makeText(activity, "Please select court name first", Toast.LENGTH_LONG).show()
                    return false
                }
        return true
    }


    fun submitButton() {
        presenter.hitAddCaseInfoMethod(
            tv_casetype_selection, tv_state_name, tv_district_name, tv_court_name, tv_court_number, et_section
            , tv_judge_name__name, et_case_info_other_party, et_other_party_lawyer_name, client_id, case_id!!
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_case_info)
        activity = this
        initUI()
    }

    private lateinit var presenter: AddCaseInfoPresenter

    private lateinit var client_id: String

    private var case_id: String? = null

    private lateinit var clientName: String

    private lateinit var receiver: BroadcastReceiver

    private fun initUI() {

        case_id = intent.getStringExtra("case_id")
        if (case_id.equals("")) {
            scroll_add_case_info.visibility = View.GONE
            bt_add_client11.visibility = View.GONE

            clientName = intent.getStringExtra("name")
            var email = intent.getStringExtra("email")
            client_id = intent.getStringExtra("client_id")
            tv_client_name.text = clientName
            if (email != null) {

                tv_client_emails.text = email

                if (!(email.equals("")) && !(email.equals("null"))) {
                    linear_add_case_info_email.visibility = View.VISIBLE
                }
            }
            tv_title_case.text = "Add Case"

            val firstLetter = clientName.substring(0, 1)
            tv_text_title.setText(firstLetter.toUpperCase())
            val gd = GradientDrawable()
            gd.setColor(Color.parseColor(Utils.getColor(firstLetter)))
            gd.cornerRadius = 20f
            gd.shape = GradientDrawable.OVAL
            gd.gradientType = GradientDrawable.RADIAL_GRADIENT
            tv_text_title.setBackground(gd)

        } else {
            scroll_add_case_info.visibility = View.GONE
            bt_add_client11.visibility = View.GONE
            tv_title_case.text = "Update Case"
        }

        ripple_effect_add_case_info.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                finish()
            }
        })

        iv_email_send_info.setOnClickListener(this)

        //  back_case_info.setOnClickListener(this)

        tv_casetype_selection.setOnClickListener(this)
        iv_down_arrow_case_type.setOnClickListener(this)

        tv_state_name.setOnClickListener(this)
        iv_down_arrow_state_name.setOnClickListener(this)

        tv_district_name.setOnClickListener(this)
        iv_down_arrow_district_name.setOnClickListener(this)

        tv_court_name.setOnClickListener(this)
        iv_down_arrow_court_name1.setOnClickListener(this)

        tv_court_number.setOnClickListener(this)
        iv_down_arrow_court_number.setOnClickListener(this)

        bt_add_client.setOnClickListener(this)
        bt_add_client11.setOnClickListener(this)

        tv_judge_name__name.setOnClickListener(this)
        iv_down_arrow_judge_name.setOnClickListener(this)
        tv_next_case_info.setOnClickListener(this)
        linear_status_bar.setOnClickListener(this)
        linear_add_case_info.setOnClickListener(this)

        presenter = AddCaseInfoPresenter(activity, this, case_id!!, scroll_add_case_info, bt_add_client11)
        presenter.getStateDistrictMethod()
        presenter.getAllJudges()
        presenter.getAllCaseTypes()

        et_section.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_section.text.toString().trim().length > 0) {
                    tv_title_Section.visibility = View.VISIBLE
                } else {
                    tv_title_Section.visibility = View.VISIBLE
                }
            }

        })

        et_case_info_other_party.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (et_case_info_other_party.text.toString().trim().length > 0) {
                    tv_title_other_party.visibility = View.VISIBLE
                } else {
                    tv_title_other_party.visibility = View.VISIBLE
                }
            }
        })

        et_other_party_lawyer_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_other_party_lawyer_name.text.toString().trim().length > 0) {
                    tv_title_other_party_lawyer_name.visibility = View.VISIBLE
                } else {
                    tv_title_other_party_lawyer_name.visibility = View.VISIBLE
                }
            }
        })



        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                try {
                    finish()
                } catch (e: Exception) {

                }
            }
        }

        registerReceiver(receiver, object : IntentFilter("case_update") {

        })

        /*rel_top_add_case_info.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            rel_top_add_case_info.getWindowVisibleDisplayFrame(r)

            val screenHeight = rel_top_add_case_info.getRootView().getHeight()
            Log.e("screenHeight", screenHeight.toString())
            val heightDiff = screenHeight - (r.bottom - r.top)
            Log.e("heightDiff", heightDiff.toString())
            val visible = heightDiff > screenHeight / 3
            Log.e("visible", visible.toString())
            if (visible) {
                bt_add_client11.visibility = View.VISIBLE
                bt_add_client.visibility = View.GONE
            } else {
                bt_add_client11.visibility = View.GONE
                bt_add_client.visibility = View.VISIBLE
            }
        });*/
    }

    override fun showDialog(activity: Activity) {
        Utils.showDialog(activity)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private lateinit var dialog: Dialog

    private var stateIndex: Int = 0
    private var districtIndex: Int = 0
    private var courtNameIndex: Int = 0

    fun showStateDialog(
        et_register_country: TextView, stateDistrictList: List<StateDistrictDatum>, typeData: String,
        countryName: String, stateName: String
    ) {
        dialog = Dialog(activity)
        dialog.setContentView(R.layout.selection_state_dialog)
        dialog.show()
        var rv_country_list = dialog.findViewById(R.id.rv_country_list) as RecyclerView
        var tv_cancel_popup = dialog.findViewById(R.id.tv_cancel_popup) as TextView
        var tv_done_button = dialog.findViewById(R.id.tv_done_button) as TextView
        var tv_title_popup = dialog.findViewById(R.id.tv_title_popup) as TextView
        tv_done_button.visibility = View.INVISIBLE

        var list = ArrayList<StateSelectedData>()

        var stateCheckList = ArrayList<StateCheckData>()


        if (tv_state_name.text.toString().trim().length > 0) {
            for ((idx, value) in stateDistrictList.withIndex()) {
                if (tv_state_name.text.toString().trim().equals(value.getTitle())) {
                    stateIndex = idx
                    break
                }
            }
        }

        if (!(tv_state_name.text.toString().trim().equals(""))) {
            for ((idx, value) in stateDistrictList.get(stateIndex).getDistricts().withIndex()) {
                if (tv_district_name.text.toString().trim().equals(value.getTitle())) {
                    districtIndex = idx
                    break;
                }
            }
        }

        if (!(tv_state_name.text.toString().trim().equals("")) && !(tv_district_name.text.toString().trim().equals(""))) {
            for ((idx, value) in stateDistrictList.get(stateIndex).getDistricts().get(districtIndex).getCourts()!!.withIndex()) {
                if (tv_court_name.text.toString().trim().equals(value.getCourtTitle())) {
                    courtNameIndex = idx
                    break
                }
            }
        }

        if (stateName.equals("state")) {
            if (typeData.equals("State")) {

                tv_title_popup.text = "Select State"
                for ((idx, value) in stateDistrictList.withIndex()) {
                    var stateCheckData = StateCheckData()
                    if (tv_state_name.text.toString().trim().equals(value.getTitle())) {
                        stateCheckData.setCheckValue("true")
                        stateIndex = idx
                    } else {
                        stateCheckData.setCheckValue("false")
                    }
                    stateCheckData.setId(value.getId()!!)
                    stateCheckData.setName(value.getTitle()!!)
                    stateCheckList.add(stateCheckData)
                }


            } else
                if (typeData.equals("District")) {
                    tv_title_popup.text = "Select District"
                    for ((idx, value) in stateDistrictList.get(stateIndex).getDistricts().withIndex()) {
                        var stateCheckData = StateCheckData()
                        if (tv_district_name.text.toString().trim().equals(value.getTitle())) {
                            stateCheckData.setCheckValue("true")
                            districtIndex = idx
                        } else {
                            stateCheckData.setCheckValue("false")
                        }
                        stateCheckData.setId(value.getId()!!)
                        stateCheckData.setName(value.getTitle()!!)
                        stateCheckList.add(stateCheckData)
                    }
                } else
                    if (typeData.equals("Court name")) {
                        tv_title_popup.text = "Select Court"
                        for ((idx, value) in stateDistrictList.get(stateIndex).getDistricts().get(districtIndex).getCourts()!!.withIndex()) {
                            var stateCheckData = StateCheckData()
                            if (tv_court_name.text.toString().trim().equals(value.getCourtTitle())) {
                                stateCheckData.setCheckValue("true")
                                courtNameIndex = idx

                            } else {
                                stateCheckData.setCheckValue("false")
                            }
                            stateCheckData.setId(value.getId()!!)
                            stateCheckData.setName(value.getCourtTitle()!!)
                            stateCheckList.add(stateCheckData)
                        }
                    } else {
                        tv_title_popup.text = "Select Court Number"
                        for ((idx, value) in stateDistrictList.get(stateIndex).getDistricts().get(districtIndex).getCourts()!!.get(
                            courtNameIndex
                        ).getCourtNumbers()!!.withIndex()) {
                            var stateCheckData = StateCheckData()
                            if (tv_court_number.text.toString().trim().equals(value.getValue())) {
                                stateCheckData.setCheckValue("true")

                            } else {
                                stateCheckData.setCheckValue("false")
                            }
                            stateCheckData.setId(value.getId()!!)
                            stateCheckData.setName(value.getValue()!!)
                            stateCheckList.add(stateCheckData)
                        }
                    }
        } else if (stateName.equals("judge")) {
            tv_title_popup.text = "Select Judge"
            for ((idx, value) in judgesList.withIndex()) {
                var stateCheckData = StateCheckData()
                if (tv_judge_name__name.text.toString().trim().equals(value.getFirstName()!!.trim() + " " + value.getLastName().trim())) {
                    stateCheckData.setCheckValue("true")
                } else {
                    stateCheckData.setCheckValue("false")
                }
                stateCheckData.setId(value.getId().toString())
                stateCheckData.setName(value.getFirstName()!!.trim() + " " + value.getLastName()!!.trim())
                stateCheckList.add(stateCheckData)
            }

        } else
            if (stateName.equals("case_type")) {
                tv_title_popup.text = "Select Case Type"
                for ((idx, value) in caseTypeList.withIndex()) {
                    var stateCheckData = StateCheckData()
                    if (tv_casetype_selection.text.toString().trim().equals(value.getCaseType())) {
                        stateCheckData.setCheckValue("true")
                    } else {
                        stateCheckData.setCheckValue("false")
                    }
                    stateCheckData.setId(value.getId().toString())
                    stateCheckData.setName(value.getCaseType().toString())
                    stateCheckList.add(stateCheckData)
                }
            }

        var adapter = RVCaseStateDistrictAdapter(activity, stateCheckList, typeData, object : OnclickListener {
            override fun onClcikListener(value: String, selectedPosition: Int) {

                dialog.dismiss()
                if (stateName.equals("state")) {
                    if (typeData.equals("State")) {
                        tv_state_name.error = null
                        tv_state_name.text = value
                        tv_district_name.text = ""
                        tv_court_name.text = ""
                        tv_court_number.text = ""
                        stateIndex = selectedPosition
                        courtNameIndex = 0
                        districtIndex = 0

                        if (value.equals("")) {
                            tv_state_name_title.visibility = View.VISIBLE
                        } else {
                            tv_state_name_title.visibility = View.VISIBLE
                        }
                        tv_district_name_title.visibility = View.VISIBLE
                        tv_court_name_title2.visibility = View.VISIBLE
                        tv_court_number_title2.visibility = View.VISIBLE
                    } else
                        if (typeData.equals("District")) {
                            tv_district_name.error = null
                            tv_district_name.text = value
                            tv_court_name.text = ""
                            tv_court_number.text = ""
                            districtIndex = selectedPosition
                            courtNameIndex = 0
                            if (value.equals("")) {
                                tv_district_name_title.visibility = View.VISIBLE
                            } else {
                                tv_district_name_title.visibility = View.VISIBLE
                            }
                            tv_court_name_title2.visibility = View.VISIBLE
                            tv_court_number_title2.visibility = View.VISIBLE
                        } else
                            if (typeData.equals("Court name")) {
                                tv_court_name.error = null
                                courtNameIndex = selectedPosition
                                tv_court_name.text = value
                                tv_court_number.text = ""

                                if (value.equals("")) {
                                    tv_court_name_title2.visibility = View.VISIBLE
                                } else {
                                    tv_court_name_title2.visibility = View.VISIBLE
                                }
                                tv_court_number_title2.visibility = View.VISIBLE
                            } else {
                                tv_court_number.error = null
                                tv_court_number.text = value

                                if (value.equals("")) {
                                    tv_court_number_title2.visibility = View.VISIBLE
                                } else {
                                    tv_court_number_title2.visibility = View.VISIBLE
                                }
                            }
                } else
                    if (stateName.equals("judge")) {
                        tv_judge_name__name.text = value

                        if (value.equals("")) {
                            tv_judge_name_title.visibility = View.VISIBLE
                        } else {

                            tv_judge_name_title.visibility = View.VISIBLE
                        }
                    } else
                        if (stateName.equals("case_type")) {
                            tv_casetype_selection.error = null
                            tv_casetype_selection.text = value
                            if (value.equals("")) {
                                tv_case_title_title.visibility = View.VISIBLE
                            } else {
                                tv_case_title_title.visibility = View.VISIBLE
                            }
                        }
            }
        })
        rv_country_list.layoutManager = LinearLayoutManager(activity)
        rv_country_list.adapter = adapter

        /* if (typeData.contentEquals("State")) {
             tv_title_popup.text="Select State"
             for ((idx, value) in stateList.withIndex()) {
                 var state1 = StateSelectedData()

                 state1.setId(stateList.get(idx).getId()!!)
                 state1.setName(stateList.get(idx).getTitle()!!)

                 if (tv_state.getText().toString().trim().contentEquals(stateList.get(idx).getTitle()!!)) {

                     state1.setChekValue("true")
                 } else {
                     state1.setChekValue("false")
                 }
                 list.add(state1)
             }
         } else {
             tv_title_popup.text="Select District"
             var stateId: String
             // for District
             for ((idx, value) in stateList.withIndex()) {
                 var state1 = StateSelectedData()
                 state1.setId(stateList.get(idx).getId()!!)
                 state1.setName(stateList.get(idx).getTitle()!!)
                 if (tv_state.getText().toString().trim().contentEquals(stateList.get(idx).getTitle()!!)) {

                     for ((idx2, value) in stateList.get(idx).getDistricts()!!.withIndex()) {
                         var state1 = StateSelectedData()

                         state1.setId(stateList.get(idx).getDistricts()!!.get(idx2).getId()!!)
                         state1.setName(stateList.get(idx).getDistricts()!!.get(idx2).getTitle()!!)

                         if (tv_district.getText().toString().trim().contentEquals(
                                 stateList.get(idx).getDistricts()!!.get(
                                     idx2
                                 ).getTitle()!!
                             )
                         ) {

                             state1.setChekValue("true")
                         } else {
                             state1.setChekValue("false")
                         }
                         list.add(state1)
                     }

                     break;
                 }
             }
         }
 */


        tv_cancel_popup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.cancel()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
