package com.advocatediary.adapter

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.AppCompatCheckBox
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.advocatediary.activity.common.CommonActivity
import com.advocatediary.handler.OnclickListener
import com.advocatediary.model.casePurposes.CasePurposesDatum
import com.advocatediary.model.home.HomeNextDayCasesArr
import com.advocatediary.model.state.StateSelectedData
import com.advocatediary.model.stateDistricts.StateCheckData
import com.advocatediary.utils.Utils
import com.chauthai.swipereveallayout.SwipeRevealLayout
import com.e.advocatediary.R
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RVTomorrowHomeAdapter(
    val context: Context,
    val listTomorrow: List<HomeNextDayCasesArr>?,
    var purposeList: List<CasePurposesDatum>,
    var onClickLissner: OnclickListener
) : RecyclerView.Adapter<RVTomorrowHomeAdapter.MyViewHolder>() {

    private  var lastAnimatedPosition: Int?=0

    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {

        holder.iv_view_line.setBackgroundColor(context.getColor(R.color.second_tab_color_home))
        holder.tv_purpose_evidance.setTextColor(context.getColor(R.color.second_tab_color_home))

        holder.swipe_layout.setLockDrag(false)
        holder.tv_today_name.text = listTomorrow!!.get(p1).getClient()!!.getFullName()
        holder.tv_purpose_evidance.text = listTomorrow!!.get(p1).getPurposeHearing()!!.getName()
        holder.tv_case_type.text = listTomorrow!!.get(p1).getCase()!!.getCaseType()!!.getCaseType()

        holder.tv_case_address.text = listTomorrow!!.get(p1).getCase()!!.getCourt()!!.getCourtTitle()
        holder.tv_place_data.text =
            listTomorrow!!.get(p1).getCase()!!.getDistrict()!!.getTitle() + ", " + listTomorrow!!.get(p1).getCase()!!.getState()!!.getTitle()

        var value: String = listTomorrow!!.get(p1).getClient()!!.getFullName()!!

        val firstLetter = value.substring(0, 1)
        holder.tv_today_first_letter.setText(firstLetter.toUpperCase())

        val gd = GradientDrawable()
        gd.setColor(Color.parseColor(Utils.getColor(firstLetter)))
        // (no gradient)
        //gd.setStroke(2, Color.BLACK);
        gd.cornerRadius = 20f
        gd.shape = GradientDrawable.OVAL
        gd.gradientType = GradientDrawable.RADIAL_GRADIENT
        //gd.setGradientRadius(iv.getWidth()/2);
        holder.tv_today_first_letter.setBackground(gd)


        holder.linear_home_todayd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(context, CommonActivity::class.java)
                intent.putExtra("case_id", listTomorrow!!.get(p1).getCaseId())
                intent.putExtra("screen_type_", "case_detail")
                context.startActivity(intent)
                Utils.screenOpenCloseAnimation(context as Activity)
            }
        })



        if (p1 > this!!.lastAnimatedPosition!!) {

            lastAnimatedPosition = p1
            Utils.showAnimationList(context, holder.linear_home_todayd)
        }

        /*holder.linear_details_today.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(context, CommonActivity::class.java)
                intent.putExtra("case_id", listTomorrow!!.get(p1).getCaseId())
                intent.putExtra("screen_type_", "case_detail")
                context.startActivity(intent)
            }
        })*/


        holder.update_layout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
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
                        showDatePickar(tv_next_date_update,listTomorrow.get(p1).getCaseNextDate()!!)
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
                        jsonObject.put("case_next_date", listTomorrow!!.get(p1).getCaseNextDate());
                        jsonObject.put("case_id", listTomorrow!!.get(p1).getCaseId());

                        onClickLissner.onClcikListener(jsonObject.toString(), p1)

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
        })

    }

    override fun getItemCount(): Int {
        return listTomorrow!!.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_today_home_adapter, p0, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var swipe_layout = itemView.findViewById(R.id.swipe_layout) as SwipeRevealLayout
        var tv_today_name = itemView.findViewById(R.id.tv_today_name) as TextView
        var tv_purpose_evidance = itemView.findViewById(R.id.tv_purpose_evidance) as TextView
        var tv_case_type = itemView.findViewById(R.id.tv_case_type) as TextView
        var tv_case_address = itemView.findViewById(R.id.tv_case_address) as TextView
        var tv_place_data = itemView.findViewById(R.id.tv_place_data) as TextView
        var tv_today_first_letter = itemView.findViewById(R.id.tv_today_first_letter) as TextView

        var update_layout = itemView.findViewById<FrameLayout>(R.id.delete_layout)
        var linear_details_today=itemView.findViewById<LinearLayout>(R.id.linear_details_today)
        var linear_home_todayd=itemView.findViewById<LinearLayout>(R.id.linear_home_todayd)

        var iv_view_line=itemView.findViewById<View>(R.id.iv_view_line)
    }

    // For show filing date
    fun showDatePickar(tv_next_date_update: TextView,previousCaseDate:String) {
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
      //  dpd.datePicker.minDate = System.currentTimeMillis()


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
        var dialog2 = Dialog(context)
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

        var adapter = RVPurposeAdapter(context, stateCheckList, typeData, object : OnclickListener {
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
}