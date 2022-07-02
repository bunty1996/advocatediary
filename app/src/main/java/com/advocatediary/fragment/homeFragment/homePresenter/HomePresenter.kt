package com.advocatediary.fragment.homeFragment.homePresenter

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.adapter.RVHomePendingAdapter
import com.advocatediary.adapter.RVHomeTodayAdapter
import com.advocatediary.adapter.RVTomorrowHomeAdapter
import com.advocatediary.fragment.homeFragment.HomeFragment
import com.advocatediary.fragment.homeFragment.homeView.HomeView
import com.advocatediary.handler.CasePurposesHandler
import com.advocatediary.handler.CaseUpdateHandler
import com.advocatediary.handler.HomeHandler
import com.advocatediary.handler.OnclickListener
import com.advocatediary.model.casePurposes.CasePurposesDatum
import com.advocatediary.model.casePurposes.CasePurposesExample
import com.advocatediary.model.caseUpdate.CaseUpdateExample
import com.advocatediary.model.home.HomeExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices
import com.github.clans.fab.FloatingActionMenu
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class HomePresenter(
    var activity: Activity,
    var homeView: HomeView,
    var rv_today_list: RecyclerView,
    var tv_today_time: TextView,
    var tv_tommorrow_time: TextView,
    var tv_total_today_case: TextView,
    var tv_total_tommorrow_case: TextView,
    var rv_tommorrow_list: RecyclerView,
    var rv_pending_list_home: RecyclerView,
    var tv_pending_case_total: TextView,
    var homeFragment: HomeFragment,
    var card_view_no_today_case: RelativeLayout,
    var card_view_no_tommorrow_case: RelativeLayout,
    var card_view_no_pending_case: RelativeLayout,
    var linear_full_layout: LinearLayout,
    var menu_fab_home: FloatingActionMenu
) {

    fun getAllHomeData(loaderType: String) {
        //homeView.showDialog(activity)
        var userId = CsPreferences.readString(activity, Constants.USER_ID)

        WebServices.Factory1.getInstance()!!.getHomeSectionMethod(userId, object : HomeHandler {
            override fun onSuccess(homeExample: HomeExample) {
                linear_full_layout.visibility = View.VISIBLE
                menu_fab_home.visibility = View.VISIBLE
                if (loaderType.equals("")) {
                    homeView.hideDialog()
                }
                if (homeExample != null) {

                    // for today date
                    var inputDate = SimpleDateFormat("yyyy-MM-dd")
                    var outputDate = SimpleDateFormat("MMM dd, yyyy")

                    var date1 = inputDate.parse(homeExample.getResponse()!!.getToday()) as Date
                    var date2 = outputDate.format(date1)

                    tv_today_time.text = "(" + date2 + ")"

                    // for Next date
                    var inputDate3 = SimpleDateFormat("yyyy-MM-dd")
                    var outputDate3 = SimpleDateFormat("MMM dd, yyyy")

                    var date3 = inputDate3.parse(homeExample.getResponse()!!.getNextDay()) as Date
                    var date4 = outputDate3.format(date3)

                    tv_tommorrow_time.text = "(" + date4 + ")"

                    tv_total_today_case.text = "0"
                    if (homeExample.getResponse()!!.getData()!!.getTodayCasesArr() != null) {
                        if (homeExample.getResponse()!!.getData()!!.getTodayCasesArr()!!.size > 0) {
                            tv_total_today_case.text =
                                "" + homeExample.getResponse()!!.getData()!!.getTodayCasesArr()!!.size

                            val manager = LinearLayoutManager(activity)
                            rv_today_list.layoutManager = manager
                            val adapter =
                                RVHomeTodayAdapter(activity,
                                    homeExample.getResponse()!!.getData()!!.getTodayCasesArr(),
                                    purposeList,
                                    object : OnclickListener {
                                        override fun onClcikListener(value: String, selectedPosition: Int) {
                                            val jsonObject = JSONObject(value)

                                            var tv_next_date_update = jsonObject.getString("tv_next_date_update");
                                            var tv_Status_selection = jsonObject.getString("tv_Status_selection");
                                            var et_payment_update = jsonObject.getString("et_payment_update");
                                            var et_notes = jsonObject.getString("et_notes");
                                            var checkValue = jsonObject.getString("checkValue");
                                            var case_next_date = jsonObject.getString("case_next_date");
                                            var case_id = jsonObject.getString("case_id");

                                            updateCasesMethod(
                                                tv_next_date_update,
                                                tv_Status_selection,
                                                et_payment_update,
                                                et_notes,
                                                checkValue,
                                                case_next_date,
                                                case_id
                                            )
                                        }

                                    })
                            rv_today_list.adapter = adapter


                            rv_today_list.setTranslationY(rv_today_list.getHeight().toFloat())
                            rv_today_list.setAlpha(0f)
                            rv_today_list.animate()
                                .translationY(0f)
                                .setDuration(1500)
                                .alpha(1f)
                                .setInterpolator(AccelerateDecelerateInterpolator())
                                .start()


                            //   card_view_no_today_case.visibility = View.GONE
                        } else {

                            //  card_view_no_today_case.visibility = View.VISIBLE
                        }
                    } else {
                        //   card_view_no_today_case.visibility = View.VISIBLE
                    }

                    tv_total_tommorrow_case.text = "0"
                    if (homeExample.getResponse()!!.getData()!!.getNextDayCasesArr() != null) {
                        if (homeExample.getResponse()!!.getData()!!.getNextDayCasesArr()!!.size > 0) {
                            tv_total_tommorrow_case.text =
                                "" + homeExample.getResponse()!!.getData()!!.getNextDayCasesArr()!!.size

                            val manager = LinearLayoutManager(activity)
                            rv_tommorrow_list.layoutManager = manager
                            val adapter =
                                RVTomorrowHomeAdapter(
                                    activity,
                                    homeExample.getResponse()!!.getData()!!.getNextDayCasesArr(), purposeList,
                                    object : OnclickListener {
                                        override fun onClcikListener(value: String, selectedPosition: Int) {


                                            val jsonObject = JSONObject(value)


                                            var tv_next_date_update = jsonObject.getString("tv_next_date_update");
                                            var tv_Status_selection = jsonObject.getString("tv_Status_selection");
                                            var et_payment_update = jsonObject.getString("et_payment_update");
                                            var et_notes = jsonObject.getString("et_notes");
                                            var checkValue = jsonObject.getString("checkValue");
                                            var case_next_date = jsonObject.getString("case_next_date");
                                            var case_id = jsonObject.getString("case_id");

                                            updateCasesMethod(
                                                tv_next_date_update,
                                                tv_Status_selection,
                                                et_payment_update,
                                                et_notes,
                                                checkValue,
                                                case_next_date,
                                                case_id
                                            )
                                        }

                                    }
                                )
                            rv_tommorrow_list.adapter = adapter

                            rv_tommorrow_list.setTranslationY(rv_tommorrow_list.getHeight().toFloat())
                            rv_tommorrow_list.setAlpha(0f)
                            rv_tommorrow_list.animate()
                                .translationY(0f)
                                .setDuration(1500)
                                .alpha(1f)
                                .setInterpolator(AccelerateDecelerateInterpolator())
                                .start()

                            //  card_view_no_tommorrow_case.visibility = View.GONE
                        } else {
                            //  card_view_no_tommorrow_case.visibility = View.VISIBLE
                        }
                    } else {
                        // card_view_no_tommorrow_case.visibility = View.VISIBLE
                    }

                    tv_pending_case_total.text = "0"
                    if (homeExample.getResponse()!!.getData()!!.getPendingCasesArr() != null) {
                        if (homeExample.getResponse()!!.getData()!!.getPendingCasesArr()!!.size > 0) {
                            tv_pending_case_total.text =
                                "" + homeExample.getResponse()!!.getData()!!.getPendingCasesArr()!!.size

                            val manager = LinearLayoutManager(activity)
                            rv_pending_list_home.layoutManager = manager
                            val adapter =
                                RVHomePendingAdapter(
                                    activity,
                                    homeExample.getResponse()!!.getData()!!.getPendingCasesArr(),
                                    homeFragment,
                                    purposeList, object : OnclickListener {
                                        override fun onClcikListener(value: String, selectedPosition: Int) {

                                            val jsonObject = JSONObject(value)

                                            var tv_next_date_update = jsonObject.getString("tv_next_date_update");
                                            var tv_Status_selection = jsonObject.getString("tv_Status_selection");
                                            var et_payment_update = jsonObject.getString("et_payment_update");
                                            var et_notes = jsonObject.getString("et_notes");
                                            var checkValue = jsonObject.getString("checkValue");
                                            var case_next_date = jsonObject.getString("case_next_date");
                                            var case_id = jsonObject.getString("case_id");

                                            updateCasesMethod(
                                                tv_next_date_update,
                                                tv_Status_selection,
                                                et_payment_update,
                                                et_notes,
                                                checkValue,
                                                case_next_date,
                                                case_id
                                            )


                                        }

                                    }
                                )
                            rv_pending_list_home.adapter = adapter

                            rv_pending_list_home.setTranslationY(rv_pending_list_home.getHeight().toFloat())
                            rv_pending_list_home.setAlpha(0f)
                            rv_pending_list_home.animate()
                                .translationY(0f)
                                .setDuration(1500)
                                .alpha(1f)
                                .setInterpolator(AccelerateDecelerateInterpolator())
                                .start()

                            //   card_view_no_pending_case.visibility = View.GONE
                        } else {
                            //     card_view_no_pending_case.visibility = View.VISIBLE
                        }
                    } else {
                        //   card_view_no_pending_case.visibility = View.VISIBLE
                    }

                }
                homeView.setEmptyValues(1)


                //setNoDataValue(1)
            }

            override fun onError(message: String) {
                homeView.hideDialog()
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

        homeView.showDialog(activity)
        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.updateCasseAPICall(
            tv_next_date_update.toString().trim(),
            purposeId,
            et_payment_update.toString().trim(),
            et_notes.toString().trim(),
            checkValue, previousCaseDate, CaseId, userId, object : CaseUpdateHandler {
                override fun onSuccess(caseUpdateExample: CaseUpdateExample) {
                    homeView.hideDialog()
                    if (caseUpdateExample != null) {
                        getAllHomeData("")
                    }
                }

                override fun onError(message: String) {
                    homeView.hideDialog()
                }


            })
    }

    private lateinit var purposeList: List<CasePurposesDatum>

    fun getAllPuposesMethod(loaderType: String) {
        if (loaderType.equals("")) {
            homeView.showDialog(activity)
        }
        WebServices.Factory1.getInstance()?.getAllPurposesMthod(object : CasePurposesHandler {

            override fun onSuccess(example: CasePurposesExample) {
                //  addCaseDetailView.hideDialog()
                getAllHomeData(loaderType)
                if (example != null) {

                    // homeView.setCasePurposesList(activity, example.getResponse()!!.getData()!!.getData()!!)
                    purposeList = example.getResponse()!!.getData()!!.getData()!!

                }
            }

            override fun onError(message: String) {
                // addCaseDetailView.hideDialog()
                getAllHomeData("")
            }
        })
    }


    fun setNoDataValue(seletcedPosition: Int) {
        card_view_no_today_case.visibility = View.GONE
        card_view_no_tommorrow_case.visibility = View.GONE
        card_view_no_pending_case.visibility = View.GONE

        rv_today_list.visibility = View.GONE
        rv_tommorrow_list.visibility = View.GONE
        rv_pending_list_home.visibility = View.GONE

        if (seletcedPosition == 1) {
            if (tv_total_today_case.text.toString().trim().toInt() == 0) {
                card_view_no_today_case.visibility = View.VISIBLE
            } else {
                card_view_no_today_case.visibility = View.GONE
                rv_today_list.visibility = View.VISIBLE
            }
        } else
            if (seletcedPosition == 2) {
                if (tv_total_tommorrow_case.text.toString().trim().toInt() == 0) {
                    card_view_no_tommorrow_case.visibility = View.VISIBLE
                } else {
                    card_view_no_tommorrow_case.visibility = View.GONE
                    rv_tommorrow_list.visibility = View.VISIBLE
                }
            } else
                if (seletcedPosition == 3) {
                    if (tv_pending_case_total.text.toString().trim().toInt() == 0) {
                        card_view_no_pending_case.visibility = View.VISIBLE
                    } else {
                        card_view_no_pending_case.visibility = View.GONE
                        rv_pending_list_home.visibility = View.VISIBLE
                    }
                }
    }
}