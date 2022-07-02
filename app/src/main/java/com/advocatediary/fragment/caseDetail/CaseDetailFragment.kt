package com.advocatediary.fragment.caseDetail

import android.Manifest
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.advocatediary.adapter.RVPurposeAdapter
import com.advocatediary.fragment.caseDetail.caseDeatilView.CaseDetailView
import com.advocatediary.fragment.caseDetail.caseDetailPresenter.CaseDetailPresenter
import com.advocatediary.handler.OnclickListener
import com.advocatediary.model.caseDetail.CaseDetailClientDetail
import com.advocatediary.model.caseDetail.CaseDetailData
import com.advocatediary.model.casePurposes.CasePurposesDatum
import com.advocatediary.model.state.StateSelectedData
import com.advocatediary.model.stateDistricts.StateCheckData
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import com.advocatediary.utils.Constants
import java.text.ParseException

class CaseDetailFragment : Fragment(), CaseDetailView, View.OnClickListener {
    private lateinit var purposeList: List<CasePurposesDatum>

    override fun setPurposeList(purposeList: List<CasePurposesDatum>) {
        this.purposeList = purposeList
    }

    override fun onClick(v: View?) {
        when (v) {
            linear_today_first -> setTabHeight(1)
            linear_tomorrow_second -> setTabHeight(2)
            linear_additional_payment_third -> setTabHeight(3)
            linear_pending_fourth -> setTabHeight(4)
            iv_email_send -> sendMail()
            bt_update_case -> updateCaseMethod()
            iv_phone_dial -> checkPermission()
        }
    }


    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                caseDeatilActivity,
                Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?

            Log.e("ffeas", "efd3eds")
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(
                caseDeatilActivity,
                arrayOf(Manifest.permission.CALL_PHONE),
                42
            )


        } else {
            // Permission has already been granted
            callPhone()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "" + tv_phone_detail.text.toString().trim()))
        startActivity(intent)
    }


    fun updateCaseMethod() {

        var dialog = Dialog(context)
        dialog.setContentView(R.layout.update_case_layout)
        dialog.getWindow().setBackgroundDrawable(object : ColorDrawable(Color.TRANSPARENT) {

        });
        dialog.show()

        var tv_next_date_update = dialog.findViewById<TextView>(R.id.tv_next_date_update)
        var tv_Status_selection = dialog.findViewById<TextView>(R.id.tv_Status_selection)
        var et_payment_update = dialog.findViewById<EditText>(R.id.et_payment_update)

        var et_notes = dialog.findViewById<EditText>(R.id.et_notes)
        tv_next_date_update.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showDatePickar(tv_next_date_update)
            }
        })

        tv_Status_selection.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showPurposeDialog(
                    tv_Status_selection,
                    "purpose",
                    tv_Status_selection.text.toString().trim(),
                    ""
                )
            }
        })

        var bt_update_case = dialog.findViewById<Button>(R.id.bt_update_case)
        var check_box_important = dialog.findViewById<AppCompatCheckBox>(R.id.check_box_important)
        bt_update_case.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
// for update case
                dialog.dismiss()
                var checkValue: String
                if (check_box_important.isChecked) {
                    checkValue = "1"
                } else {
                    checkValue = "0"
                }

                var jsonObject = JSONObject()

                jsonObject.put("tv_next_date_update", tv_next_date_update.text.toString().trim());
                jsonObject.put("tv_Status_selection", tv_Status_selection.text.toString().trim());
                jsonObject.put("et_payment_update", et_payment_update.text.toString().trim());
                jsonObject.put("et_notes", et_notes.text.toString().trim());
                jsonObject.put("checkValue", checkValue);
                jsonObject.put("case_next_date", previousDate);
                jsonObject.put("case_id", case_id);

                presenter.updateCasesMethod(
                    tv_next_date_update.text.toString().trim(), tv_Status_selection.text.toString().trim(),
                    et_payment_update.text.toString().trim(), et_notes.text.toString().trim(), checkValue,
                    previousDate!!, case_id!!
                )
            }
        })

        var tv_ruppess = dialog.findViewById<EditText>(R.id.tv_ruppess)
        et_payment_update.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_payment_update.text.toString().trim().length > 0) {
                    tv_ruppess.visibility = View.VISIBLE
                } else {
                    tv_ruppess.visibility = View.GONE
                }
            }
        })
    }

    // For show filing date
    fun showDatePickar(tv_next_date_update: TextView) {
        val c = Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_MONTH)
        var month = c.get(Calendar.MONTH)
        var year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(
            context,
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

                tv_next_date_update.text = "$year-$monthValue-$dayValue"

            }, year, month, day
        )
        dpd.show()
        //     dpd.datePicker.minDate = System.currentTimeMillis()

        // for edit Case
        val Date = previousDate + " 13:00"
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

    fun showPurposeDialog(
        et_register_country: TextView, typeData: String,
        countryName: String, stateName: String
    ) {
        var dialog2 = Dialog(caseDeatilActivity)
        dialog2.setContentView(R.layout.selection_state_dialog)
        dialog2.show()
        var rv_country_list = dialog2.findViewById(R.id.rv_country_list) as RecyclerView
        var tv_cancel_popup = dialog2.findViewById(R.id.tv_cancel_popup) as TextView
        var tv_done_button = dialog2.findViewById(R.id.tv_done_button) as TextView
        var tv_title_popup = dialog2.findViewById(R.id.tv_title_popup) as TextView
        tv_done_button.visibility = View.INVISIBLE

        var list = ArrayList<StateSelectedData>()

        var stateCheckList = ArrayList<StateCheckData>()

        tv_title_popup.text = "Select Purpose"
        for ((idx, value) in purposeList.withIndex()) {
            var stateCheckData = StateCheckData()
            if (et_register_country.text.toString().trim().equals(value.getName())) {
                stateCheckData.setCheckValue("true")
                //stateIndex = idx
            } else {
                stateCheckData.setCheckValue("false")
            }
            stateCheckData.setId(value.getId().toString())
            stateCheckData.setName(value.getName()!!)
            stateCheckList.add(stateCheckData)
        }

        var adapter = RVPurposeAdapter(caseDeatilActivity, stateCheckList, typeData, object : OnclickListener {
            override fun onClcikListener(value: String, selectedPosition: Int) {

                dialog2.dismiss()

                if (typeData.equals("purpose")) {

                    et_register_country.text = value
                }
            }
        })

        rv_country_list.layoutManager = LinearLayoutManager(context)
        rv_country_list.adapter = adapter

        tv_cancel_popup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog2.cancel()
            }
        })
    }

    fun sendMail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, tv_email_detail.text.toString().trim())
        intent.putExtra(Intent.EXTRA_SUBJECT, "Advocate Diary")
        if (intent.resolveActivity(activity!!.getPackageManager()) != null) {
            startActivity(intent)
        }
    }

    fun defaultTab() {

    }

    fun setTabHeight(position: Int) {
        img_today_down_first.rotation = 0f
        img_tomorrow_down_second.rotation = 0f
        img_pending_down_payment_third.rotation = 0f
        img_pending_down_fourth.rotation = 0f

        linear_client_detail.visibility = View.GONE
        linear_case_info.visibility = View.GONE
        linear_payment_dataa.visibility = View.GONE
        linear_case_history.visibility = View.GONE
        cardview_case_history.visibility = View.GONE

        if (position == 1) {
            img_today_down_first.rotation = 180f
            linear_client_detail.visibility = View.VISIBLE

            Utils.showFromTopToBottomAnimation(caseDeatilActivity,linear_client_detail)
           /* linear_client_detail.setTranslationY(linear_client_detail.getHeight().toFloat())
            linear_client_detail.setAlpha(0f)
            linear_client_detail.animate()
                .translationY(0f)
                .setDuration(1100)
                .alpha(1f)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()*/


        } else
            if (position == 2) {
                img_tomorrow_down_second.rotation = 180f
                linear_case_info.visibility = View.VISIBLE
            } else
                if (position == 3) {
                    img_pending_down_payment_third.rotation = 180f
                    linear_payment_dataa.visibility = View.VISIBLE
                } else
                    if (position == 4) {
                        img_pending_down_fourth.rotation = 180f
                        linear_case_history.visibility = View.VISIBLE
                        cardview_case_history.visibility = View.VISIBLE
                    }
    }


    fun setThirdFourthHeights() {
        /* if(linear_case_history.visibility==View.VISIBLE)
         {
             var ll_menu_code = new LinearLayout(this);
     ll_menu_code.setId(1001);
     ll_menu_code.setWeightSum(2f);

    var parmas_ll_menu_code = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
     parmas_ll_menu_code.setMargins(0, 20, 0, 0);
     parmas_ll_menu_code.addRule(RelativeLayout.BELOW, linear_third_layout);

             linear_third_layout.setLayoutParams(parmas_ll_menu_code);
         }*/
    }

    private var previousDate: String? = null

    override fun setClientData(detail: CaseDetailData) {

        rel_top_detail_data.visibility = View.VISIBLE

        if (detail.getCasePaymentHistoryArr()!!.size > 0) {
            linear_payment_history11.visibility = View.VISIBLE
        } else {
            linear_payment_history11.visibility = View.GONE
        }

        previousDate = detail.getCaseNextDate()

        tv_name_detail.text = detail.getClientDetail()!!.getFullName()
        tv_email_detail.text = detail.getClientDetail()!!.getEmail()
        tv_phone_detail.text = detail.getClientDetail()!!.getPhone()
        tv_city_detail.text = detail.getClientDetail()!!.getAddress()
        tv_male_detail.text = detail.getClientDetail()!!.getGender()

        tv_case_type_case_info.text = detail.getCaseType()
        tv_court_name_case_info.text = detail.getCourtTitle()
        tv_section.text = detail.getSection()
        tv_other_party_case_info.text = detail.getOppositeParty()
        tv_judge_name_case_info.text = detail.getJudgeFirstName() + " " + detail.getJudgeLastName()
        tv_other_party_lawyer_name_case_info.text = detail.getOppositeLawyer()

        if (tv_email_detail.text.toString().trim().equals("")) {
            linear_email_value.visibility = View.GONE
        } else {
            linear_email_value.visibility = View.VISIBLE
        }

        if (tv_city_detail.text.toString().trim().equals("")) {
            linear_place.visibility = View.GONE
        } else {
            linear_place.visibility = View.VISIBLE
        }

        if (tv_judge_name_case_info.text.toString().trim().equals("")) {
            tv_judge_name_case_info.visibility = View.GONE
        } else {
            tv_judge_name_case_info.visibility = View.VISIBLE
        }

        if (tv_other_party_lawyer_name_case_info.text.toString().trim().equals("")) {
            tv_other_party_lawyer_name_case_info.visibility = View.GONE
        } else {
            tv_other_party_lawyer_name_case_info.visibility = View.VISIBLE
        }

        if (tv_other_party_case_info.text.toString().trim().equals("")) {
            tv_other_party_case_info.visibility = View.GONE
        } else {
            tv_other_party_case_info.visibility = View.VISIBLE
        }

        if (tv_section.text.toString().trim().equals("")) {
            tv_section.visibility = View.GONE
        } else {
            tv_section.visibility = View.VISIBLE
        }

        var inputSimpleFormat = SimpleDateFormat("yyyy-MM-dd")
        var outputSimpleFormat = SimpleDateFormat("MMM dd, yyyy")
        var date1 = inputSimpleFormat.parse(detail.getDateOfFileing())
        var date2 = outputSimpleFormat.format(date1)

        tv_date_of_filing.text = date2

        tv_total_fee_data.text = detail.getTotalFee()
        tv_advance_fee_data.text = detail.getPayment()
    }

    private lateinit var caseDeatilActivity: Activity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.case_detail, container, false)
        caseDeatilActivity = activity!!

        initUI(view)
        return view
    }

    private lateinit var presenter: CaseDetailPresenter

    private lateinit var tv_name_detail: TextView
    private lateinit var tv_email_detail: TextView
    private lateinit var tv_phone_detail: TextView
    private lateinit var tv_city_detail: TextView
    private lateinit var tv_male_detail: TextView

    private lateinit var tv_case_type_case_info: TextView
    private lateinit var tv_court_name_case_info: TextView
    private lateinit var tv_section: TextView
    private lateinit var tv_other_party_case_info: TextView
    private lateinit var tv_judge_name_case_info: TextView
    private lateinit var tv_other_party_lawyer_name_case_info: TextView
    private lateinit var tv_date_of_filing: TextView

    private lateinit var tv_total_fee_data: TextView
    private lateinit var tv_advance_fee_data: TextView
    private lateinit var rv_payment_history: RecyclerView
    private lateinit var rv_case_history: RecyclerView
    private lateinit var linear_client_detail: LinearLayout
    private lateinit var linear_case_info: LinearLayout
    private lateinit var linear_payment_dataa: LinearLayout
    private lateinit var linear_case_history: LinearLayout

    private lateinit var cardview_case_history:CardView

    private lateinit var linear_today_first: LinearLayout
    private lateinit var linear_tomorrow_second: LinearLayout
    private lateinit var linear_additional_payment_third: LinearLayout
    private lateinit var linear_pending_fourth: LinearLayout
    private lateinit var iv_email_send: ImageView
    private lateinit var img_today_down_first: ImageView
    private lateinit var img_tomorrow_down_second: ImageView
    private lateinit var img_pending_down_payment_third: ImageView
    private lateinit var img_pending_down_fourth: ImageView
    private lateinit var linear_payment_history11: LinearLayout

    private lateinit var bt_update_case: Button
    private lateinit var linear_email_value: LinearLayout
    private lateinit var linear_place: LinearLayout
    private lateinit var rel_top_detail_data: RelativeLayout

    private var case_id: String? = null

    private lateinit var receiver: BroadcastReceiver
    private lateinit var iv_phone_dial: ImageView

    private fun initUI(view: View?) {
        iv_phone_dial = view!!.findViewById<ImageView>(R.id.iv_phone_dial)
        iv_phone_dial.setOnClickListener(this)

        tv_name_detail = view!!.findViewById<TextView>(R.id.tv_name_detail)
        tv_email_detail = view!!.findViewById<TextView>(R.id.tv_email_detail)
        tv_phone_detail = view!!.findViewById<TextView>(R.id.tv_phone_detail)
        tv_city_detail = view!!.findViewById<TextView>(R.id.tv_city_detail)
        tv_male_detail = view!!.findViewById<TextView>(R.id.tv_male_detail)
        linear_email_value = view!!.findViewById<LinearLayout>(R.id.linear_email_value)
        linear_place = view!!.findViewById<LinearLayout>(R.id.linear_place)

        rel_top_detail_data = view.findViewById<RelativeLayout>(R.id.rel_top_detail_data)

        tv_case_type_case_info = view!!.findViewById<TextView>(R.id.tv_case_type_case_info)
        tv_court_name_case_info = view!!.findViewById<TextView>(R.id.tv_court_name_case_info)
        tv_section = view!!.findViewById<TextView>(R.id.tv_section)
        tv_other_party_case_info = view!!.findViewById<TextView>(R.id.tv_other_party_case_info)
        tv_judge_name_case_info = view!!.findViewById<TextView>(R.id.tv_judge_name_case_info)
        tv_other_party_lawyer_name_case_info = view!!.findViewById<TextView>(R.id.tv_other_party_lawyer_name_case_info)
        tv_date_of_filing = view!!.findViewById<TextView>(R.id.tv_date_of_filing)

        iv_email_send = view!!.findViewById<ImageView>(R.id.iv_email_send)
        iv_email_send.setOnClickListener(this)

        tv_total_fee_data = view!!.findViewById<TextView>(R.id.tv_total_fee_data)
        tv_advance_fee_data = view!!.findViewById<TextView>(R.id.tv_advance_fee_data)
        rv_payment_history = view!!.findViewById<RecyclerView>(R.id.rv_payment_history)
        rv_case_history = view!!.findViewById<RecyclerView>(R.id.rv_case_history)

        linear_client_detail = view!!.findViewById<LinearLayout>(R.id.linear_client_detail)
        linear_today_first = view!!.findViewById<LinearLayout>(R.id.linear_today_first)

        linear_case_info = view!!.findViewById<LinearLayout>(R.id.linear_case_info)
        linear_tomorrow_second = view!!.findViewById<LinearLayout>(R.id.linear_tomorrow_second)

        linear_payment_dataa = view!!.findViewById<LinearLayout>(R.id.linear_payment_dataa)
        linear_additional_payment_third = view!!.findViewById<LinearLayout>(R.id.linear_additional_payment_third)

        linear_case_history = view!!.findViewById<LinearLayout>(R.id.linear_case_history)
        linear_pending_fourth = view!!.findViewById<LinearLayout>(R.id.linear_pending_fourth)

        cardview_case_history=view!!.findViewById<CardView>(R.id.cardview_case_history)


        linear_today_first.setOnClickListener(this)
        linear_tomorrow_second.setOnClickListener(this)
        linear_additional_payment_third.setOnClickListener(this)
        linear_pending_fourth.setOnClickListener(this)

        img_today_down_first = view!!.findViewById<ImageView>(R.id.img_today_down_first)
        img_tomorrow_down_second = view!!.findViewById<ImageView>(R.id.img_tomorrow_down_second)
        img_pending_down_payment_third = view!!.findViewById<ImageView>(R.id.img_pending_down_payment_third)
        img_pending_down_fourth = view!!.findViewById<ImageView>(R.id.img_pending_down_fourth)

        bt_update_case = view!!.findViewById<Button>(R.id.bt_update_case)
        bt_update_case.setOnClickListener(this)

        linear_payment_history11 = view!!.findViewById<LinearLayout>(R.id.linear_payment_history11)

        case_id = arguments!!.getString("case_id")

        presenter = CaseDetailPresenter(
            caseDeatilActivity,
            this,
            rv_payment_history,
            rv_case_history,
            bt_update_case,
            tv_advance_fee_data
        )
        //   presenter.getCaseDetail(case_id)

        presenter.getAllPuposesMethod(case_id!!)
        setTabHeight(1)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // for refresh screens
                //presenter = ClientDetailPresenter(clientDetailActivity, context, rv_client_details, tv_total_cases)
                presenter.getAllPuposesMethod(case_id!!)
            }
        }

        caseDeatilActivity.registerReceiver(receiver, object : IntentFilter(Constants.CASE_DETAIL_UPDATE) {

        })
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

}