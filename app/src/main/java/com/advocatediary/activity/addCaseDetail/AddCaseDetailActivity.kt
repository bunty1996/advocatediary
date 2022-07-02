package com.advocatediary.activity.addCaseDetail

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import android.widget.Toast
import com.advocatediary.activity.addCaseDetail.addCaseDetailPresenter.AddCaseDetailPresenter
import com.advocatediary.activity.addCaseDetail.addCaseDetailView.AddCaseDetailView
import com.advocatediary.adapter.RVCaseStateDistrictAdapter
import com.advocatediary.adapter.RVPurposeAdapter
import com.advocatediary.handler.OnclickListener
import com.advocatediary.model.caseDetail.CaseDetailData
import com.advocatediary.model.casePurposes.CasePurposesDatum
import com.advocatediary.model.state.StateSelectedData
import com.advocatediary.model.stateDistricts.StateCheckData
import com.advocatediary.model.stateDistricts.StateDistrictDatum
import com.advocatediary.utils.Utils
import com.andexert.library.RippleView
import com.e.advocatediary.R
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.content_add_case_detail.*
import org.json.JSONObject
import java.util.*
import java.text.ParseException
import java.text.SimpleDateFormat


class AddCaseDetailActivity : AppCompatActivity(), View.OnClickListener, AddCaseDetailView {
    private var previousCaseDate: String? = null

    override fun caseDetail(caseDetailData: CaseDetailData) {

        et_total_fee.setText(caseDetailData.getTotalFee())
        tv_purpose.setText(caseDetailData.getPurposeHearing())
        tv_case_filing.setText(caseDetailData.getDateOfFileing())
        tv_next_date.setText(caseDetailData.getCaseNextDate())
        et_advance_fee.setText(caseDetailData.getAdvancedFee())
        et_notess.setText(caseDetailData.getNotes())
        et_advance_fee.isEnabled = false
        et_total_fee.isEnabled = false

        et_notess.setSelection(et_notess.text.toString().trim().length)

        previousCaseDate = caseDetailData.getCaseNextDate()

        tv_ruppess_total_fee.visibility = View.VISIBLE
        tv_date_filing_title.visibility = View.VISIBLE

        tv_next_date_titlee.visibility = View.VISIBLE

        if (et_advance_fee.text.toString().trim().length > 0) {
            tv_ruppess_advance.visibility = View.VISIBLE
            tv_advance_fee_title.visibility = View.VISIBLE
        } else {
            tv_ruppess_advance.visibility = View.GONE
            tv_advance_fee_title.visibility = View.VISIBLE
        }
        tv_ruppess_advance.setTextColor(Color.LTGRAY)
        tv_advance_fee_title.setTextColor(Color.LTGRAY)
        et_advance_fee.setTextColor(Color.LTGRAY)

        tv_total_fee_title.setTextColor(Color.LTGRAY)
        et_total_fee.setTextColor(Color.LTGRAY)
        tv_ruppess_total_fee.setTextColor(Color.LTGRAY)

        tv_purpose1.visibility = View.VISIBLE
        tv_total_fee_title.visibility = View.VISIBLE
    }

    private lateinit var purposeList: List<CasePurposesDatum>

    override fun setCasePurposesList(activity: Activity, purposeList: List<CasePurposesDatum>?) {
        this.purposeList = purposeList!!
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

    override fun onClick(v: View?) {
        when (v) {
            //   back_case_detail -> finish()
            tv_purpose -> setPurposeMethod()
            iv_down_arrow_case_type -> setPurposeMethod()
            tv_case_filing -> showDatePickar()
            iv_case_filling -> showDatePickar()

            tv_next_date -> showNextDate()
            iv_next_date -> showNextDate()

            bt_add_case_submit -> submitButton()
            bt_add_case_submit111 -> submitButton()
            linear_status_bar -> Utils.hideSoftKeyboard(activity)
            linear_case_add_details -> Utils.hideSoftKeyboard(activity)
            linear_CaseDetail -> Utils.hideSoftKeyboard(activity)
        }
    }

    fun submitButton() {
        if (case_id.equals("")) {
            // for add new case
            presenter.addCaseMethod(
                et_total_fee,
                tv_purpose,
                tv_case_filing,
                tv_next_date,
                et_advance_fee,
                et_notess,
                jsonObject
            )
        } else {
            // for edit case
            presenter.editCaseMethod(
                et_total_fee,
                tv_purpose,
                tv_case_filing,
                tv_next_date,
                et_advance_fee,
                et_notess,
                jsonObject, case_id!!, previousCaseDate!!
            )
        }
    }

    // For next date
    fun showNextDate() {
        if (!(tv_case_filing.text.toString().trim().equals(""))) {


            val c = Calendar.getInstance()
            var day = c.get(Calendar.DAY_OF_MONTH)
            var month = c.get(Calendar.MONTH)
            var year = c.get(Calendar.YEAR)

            val dpd = DatePickerDialog(
                this,
                // android.R.style.Theme_Holo_Dialog,

                DatePickerDialog.OnDateSetListener { datePicker, selyear, monthOfYear, dayOfMonth ->
                    day = dayOfMonth
                    month = monthOfYear + 1
                    year = selyear
                    //  tv_case_filing.text = "$day - $month - $year"


                    var monthValue = ""
                    if (month < 10) {
                        monthValue = "0" + month
                    } else {
                        monthValue = month.toString()
                    }

                    var dayValue = ""
                    if (day < 10) {
                        dayValue = "0" + day
                    } else {
                        dayValue = day.toString()
                    }

                    tv_next_date.text = "$year-$monthValue-$dayValue"
                    tv_next_date_titlee.visibility = View.VISIBLE

                }, year, month, day
            )
            dpd.show()
            // dpd.datePicker.minDate = System.currentTimeMillis()

            if (case_id.equals("")) {
                //   tv_case_filing.text = "$year-$month-$day"
                val Date = tv_case_filing.text.toString().trim() + " 13:00"
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
                try {
                    val mDate = sdf.parse(Date)
                    var timeInMilliseconds = mDate.getTime()

                    val date = getCurrentDateTime()
                    val mDate2 = sdf.parse(date)
                    var timeInMilliseconds2 = mDate2.getTime()

                    var hours = 86400000

                    if (timeInMilliseconds < timeInMilliseconds2) {
                        dpd.datePicker.minDate = System.currentTimeMillis()
                    } else {
                        var finalTime = timeInMilliseconds + hours
                        dpd.datePicker.minDate = finalTime
                    }
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            } else {
                // for edit Case
                val Date = previousCaseDate + " 13:00"
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
                try {
                    val mDate = sdf.parse(Date)
                    var timeInMilliseconds = mDate.getTime()

                    val date = getCurrentDateTime()
                    val mDate2 = sdf.parse(date)
                    var timeInMilliseconds2 = mDate2.getTime()

                    var hours = 86400000

                    if (timeInMilliseconds < timeInMilliseconds2) {
                        dpd.datePicker.minDate = System.currentTimeMillis()
                    } else {
                        var finalTime = timeInMilliseconds + hours
                        dpd.datePicker.minDate = finalTime
                    }
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            }

        } else {
            tv_case_filing.setError("Please select filing date first")
        }
    }

    fun getCurrentDateTime(): String {
        var currentTime = System.currentTimeMillis()

        val cl = Calendar.getInstance()
        cl.timeInMillis = currentTime  //here your time in miliseconds

        var month = cl.get(Calendar.MONTH) + 1
        var monthValue = ""
        if (month < 10) {
            monthValue = "0" + month
        } else {
            monthValue = month.toString()
        }

        var day = cl.get(Calendar.DAY_OF_MONTH)
        var dayValue = ""
        if (day < 10) {
            dayValue = "0" + day
        } else {
            dayValue = day.toString()
        }

        var date =
            "" + cl.get(Calendar.YEAR) + "-" + monthValue + "-" + dayValue + " 13:00"

        return date
    }

    // For show filing date
    fun showDatePickar() {
        val c = Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_MONTH)
        var month = c.get(Calendar.MONTH)
        var year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(
            this,
            // android.R.style.Theme_Holo_Dialog,

            DatePickerDialog.OnDateSetListener { datePicker, selyear, monthOfYear, dayOfMonth ->
                day = dayOfMonth
                month = monthOfYear + 1
                year = selyear
                //  tv_case_filing.text = "$day - $month - $year"

                var monthValue = ""
                if (month < 10) {
                    monthValue = "0" + month
                } else {
                    monthValue = month.toString()
                }

                var dayValue = ""
                if (day < 10) {
                    dayValue = "0" + day
                } else {
                    dayValue = day.toString()
                }


                tv_case_filing.text = "$year-$monthValue-$dayValue"
                tv_date_filing_title.visibility = View.VISIBLE

                tv_next_date.text = ""
                tv_next_date_titlee.visibility = View.INVISIBLE

            }, year, month, day
        )
        dpd.show()
        //  dpd.datePicker.minDate = System.currentTimeMillis()

        /*     if(!(case_id.equals(""))) {
                 val Date = previousCaseDate + " 13:00"
                 val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
                 try {
                     val mDate = sdf.parse(Date)
                     var timeInMilliseconds = mDate.getTime()

                     val date = getCurrentDateTime()
                     val mDate2 = sdf.parse(date)
                     var timeInMilliseconds2 = mDate2.getTime()

                     var hours = 86400000

                     if (timeInMilliseconds < timeInMilliseconds2) {
                         dpd.datePicker.minDate = System.currentTimeMillis()
                     } else {
                         var finalTime = timeInMilliseconds + hours
                         dpd.datePicker.minDate = finalTime
                     }
                 } catch (e: ParseException) {
                     e.printStackTrace()
                 }

             }*/
    }

    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_case_detail)
        activity = this
        initUI()
    }

    private lateinit var presenter: AddCaseDetailPresenter

    private lateinit var jsonObject: String

    private var case_id: String? = null

    private fun initUI() {

        // intent.putExtra("jsonObject", jsonObject.toString())

        jsonObject = intent.getStringExtra("jsonObject")


        ripple_back_add_case_Detail.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                finish()
            }
        })

        // back_case_detail.setOnClickListener(this)
        tv_purpose.setOnClickListener(this)
        iv_down_arrow_case_type.setOnClickListener(this)

        tv_case_filing.setOnClickListener(this)
        iv_case_filling.setOnClickListener(this)

        tv_next_date.setOnClickListener(this)
        iv_next_date.setOnClickListener(this)

        bt_add_case_submit.setOnClickListener(this)
        bt_add_case_submit111.setOnClickListener(this)
        linear_status_bar.setOnClickListener(this)
        linear_case_add_details.setOnClickListener(this)
        linear_CaseDetail.setOnClickListener(this)
        bt_add_case_submit111.visibility = View.GONE

        var jsonObject = JSONObject(jsonObject)
        case_id = jsonObject.getString("case_id")

        if (case_id.equals("")) {
            tv_title_case.text = "Add Case"
            bt_add_case_submit.text = "Add Case"
            bt_add_case_submit111.text = "Add Case"
        } else {
            tv_title_case.text = "Update Case"
            bt_add_case_submit.text = "Update Case"
            bt_add_case_submit111.text = "Update Case"
        }

        presenter = AddCaseDetailPresenter(activity, this, case_id!!, scrollview_add_case_detail, bt_add_case_submit111)
        presenter.getAllPuposesMethod()


        /* rel_top_add_case_details.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
             val r = Rect()
             rel_top_add_case_details.getWindowVisibleDisplayFrame(r)

             val screenHeight = rel_top_add_case_details.getRootView().getHeight()
             Log.e("screenHeight", screenHeight.toString())
             val heightDiff = screenHeight - (r.bottom - r.top)
             Log.e("heightDiff", heightDiff.toString())
             val visible = heightDiff > screenHeight / 3
             Log.e("visible", visible.toString())
             if (visible) {
                 bt_add_case_submit111.visibility = View.VISIBLE
                 bt_add_case_submit.visibility = View.GONE
             } else {
                 bt_add_case_submit111.visibility = View.GONE
                 bt_add_case_submit.visibility = View.VISIBLE
             }
         });*/


        et_total_fee.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_total_fee.text.toString().trim().length > 0) {
                    tv_ruppess_total_fee.visibility = View.VISIBLE
                    tv_total_fee_title.visibility = View.VISIBLE
                } else {
                    tv_ruppess_total_fee.visibility = View.GONE
                    tv_total_fee_title.visibility = View.VISIBLE
                }
            }

        })

        et_advance_fee.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_advance_fee.text.toString().trim().length > 0) {
                    tv_ruppess_advance.visibility = View.VISIBLE
                    tv_advance_fee_title.visibility = View.VISIBLE
                } else {
                    tv_ruppess_advance.visibility = View.GONE
                    tv_advance_fee_title.visibility = View.VISIBLE
                }
            }

        })
    }

    fun setPurposeMethod() {
        showPurposeDialog(tv_purpose, "purpose", tv_purpose.text.toString().trim(), "")
    }


    private lateinit var dialog: Dialog

    fun showPurposeDialog(
        et_register_country: TextView, typeData: String,
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

        tv_title_popup.text = "Select Purpose"
        // if (typeData.equals("State")) {
        for ((idx, value) in purposeList.withIndex()) {
            var stateCheckData = StateCheckData()
            if (tv_purpose.text.toString().trim().equals(value.getName())) {
                stateCheckData.setCheckValue("true")
                //stateIndex = idx
            } else {
                stateCheckData.setCheckValue("false")
            }
            stateCheckData.setId(value.getId().toString())
            stateCheckData.setName(value.getName()!!)
            stateCheckList.add(stateCheckData)
        }

        var adapter = RVPurposeAdapter(activity, stateCheckList, typeData, object : OnclickListener {
            override fun onClcikListener(value: String, selectedPosition: Int) {

                dialog.dismiss()

                if (typeData.equals("purpose")) {
                    tv_purpose.error = null
                    tv_purpose.text = value

                    if (value.equals("")) {
                        tv_purpose1.visibility = View.INVISIBLE
                    } else {
                        tv_purpose1.visibility = View.VISIBLE
                    }
                }
            }
        })

        rv_country_list.layoutManager = LinearLayoutManager(activity)
        rv_country_list.adapter = adapter

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
