package com.advocatediary.fragment.myCase.myCasesPresenter

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.adapter.RVMyCasesAdapter
import com.advocatediary.fragment.myCase.myCasesView.MyCasesView
import com.advocatediary.handler.MyCasesHandler
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.myCases.MyCasesDatum
import com.advocatediary.model.myCases.MyCasesExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices
import java.lang.Exception
import android.view.animation.AccelerateDecelerateInterpolator
import android.support.v4.view.ViewCompat.animate
import android.R.attr.translationY
import android.R.attr.start
import android.opengl.ETC1.getHeight
import android.support.v4.view.ViewCompat.setTranslationY
import android.widget.LinearLayout
import com.github.clans.fab.FloatingActionMenu


class MyCasesPresenter(
    var activity: Activity,
    var myCasesView: MyCasesView,
    var rv_my_cases: RecyclerView,
    var tv_case_number: TextView,
    var search_value: String,
    var no_case_found: RelativeLayout,
    var menu_fab: FloatingActionMenu,
    var linear_top_mycase: LinearLayout
) {
    private var pageCount = 1

    private var adapter: RVMyCasesAdapter? = null

    fun setRefreshData(loaderType: String) {
        //  this.search_value = search_value
        pageCount = 1
        adapter = null
        getAllMyCases(loaderType)
    }

    fun setSearchData(loaderType: String, search_value: String) {
        this.search_value = search_value
        pageCount = 1
        adapter = null
        getAllMyCases(loaderType)
    }

    fun getAllMyCases(loaderType: String) {
        if (loaderType.equals("")) {
            myCasesView.showDialog(activity)
        }

        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.getMyCasesMethod(userId, pageCount, search_value, object : MyCasesHandler {
            override fun onSuccess(myCasesExample: MyCasesExample) {
                menu_fab.visibility = View.VISIBLE
                linear_top_mycase.visibility = View.VISIBLE
                if (loaderType.equals("")) {
                    myCasesView.hideDialog()
                }

                var list2 = ArrayList<MyCasesDatum>()
                list2.clear()

                if (adapter == null) {
                    rv_my_cases.layoutManager = LinearLayoutManager(activity)
                    rv_my_cases.adapter = adapter
                }

                if (myCasesExample != null) {
                    if (adapter == null) {
                        if (myCasesExample.getResponse()!!.getData()!!.getFrom() != null) {
                            var currentPage = myCasesExample.getResponse()!!.getData()!!.getCurrentPage()!!.toInt()

                            pageCount = currentPage + 1
                            //  pageCount = pageCount + 1
                            adapter =
                                RVMyCasesAdapter(activity, myCasesExample.getResponse()!!.getData()!!.getData()!!, "",
                                    object : ScrollListener {
                                        override fun onScrollListener() {
                                            /* if(pageCount>2){
                                                 getAllMyCases("")
                                             }else
                                             {
                                                 getAllMyCases(loaderType)
                                             }*/
                                            getAllMyCases(loaderType)
                                        }
                                    })
                            rv_my_cases.layoutManager = LinearLayoutManager(activity)
                            rv_my_cases.adapter = adapter
                            tv_case_number.text =
                                "Total (" + myCasesExample.getResponse()!!.getData()!!.getData()!!.size + ")"

                            no_case_found.visibility = View.GONE
                            rv_my_cases.visibility = View.VISIBLE
                            tv_case_number.visibility = View.VISIBLE


                            rv_my_cases.setTranslationY(rv_my_cases.getHeight().toFloat())
                            rv_my_cases.setAlpha(0f)
                            rv_my_cases.animate()
                                .translationY(0f)
                                .setDuration(1500)
                                .alpha(1f)
                                .setInterpolator(AccelerateDecelerateInterpolator())
                                .start()

                        } else {
                            if (adapter == null) {
                                adapter =
                                    RVMyCasesAdapter(activity, list2, "",
                                        object : ScrollListener {
                                            override fun onScrollListener() {
                                                /* if(pageCount>2){
                                                     getAllMyCases("")
                                                 }else
                                                 {
                                                     getAllMyCases(loaderType)
                                                 }*/
                                                // getAllMyCases(loaderType)
                                            }
                                        })

                                rv_my_cases.layoutManager = LinearLayoutManager(activity)
                                rv_my_cases.adapter = adapter
                                tv_case_number.text =
                                    "Total (" + 0 + ")"
                                no_case_found.visibility = View.VISIBLE
                                rv_my_cases.visibility = View.GONE
                                tv_case_number.visibility = View.GONE
                            }
                        }
                    } else {
                        if (myCasesExample.getResponse()!!.getData()!!.getFrom() != null) {
                            //   pageCount = pageCount + 1
                            var currentPage = myCasesExample.getResponse()!!.getData()!!.getCurrentPage()!!.toInt()

                            pageCount = currentPage + 1

                            for ((idx, value) in myCasesExample.getResponse()!!.getData()!!.getData()!!.withIndex()) {
                                var checkValue = ""
                                for ((idx1, value1) in adapter!!.myCasesList.withIndex()) {
                                    if (adapter!!.myCasesList.get(idx1).getCaseId().equals(
                                            myCasesExample.getResponse()!!.getData()!!.getData()!!.get(
                                                idx
                                            ).getCaseId()
                                        )
                                    ) {
                                        checkValue = "true"
                                        break
                                    }
                                }
                                if (checkValue.equals("")) {
                                    adapter!!.addSingleData(
                                        myCasesExample.getResponse()!!.getData()!!.getData()!!.get(
                                            idx
                                        )
                                    )
                                    adapter!!.notifyDataSetChanged()
                                }
                            }

                            /*   adapter!!.addAllData(myCasesExample.getResponse()!!.getData()!!.getData()!!)
                               adapter!!.notifyDataSetChanged()*/
                            tv_case_number.text =
                                "Total (" + adapter!!.myCasesList.size + ")"
                            no_case_found.visibility = View.GONE
                            rv_my_cases.visibility = View.VISIBLE
                            tv_case_number.visibility = View.VISIBLE
                        } else {
                            adapter!!.stopLoadingMethod("Stop")
                            adapter!!.notifyDataSetChanged()
                            myCasesView.showMessage(activity, "No More Data")
                        }
                    }
                } else {
                    if (adapter == null) {
                        adapter =
                            RVMyCasesAdapter(activity, list2, "",
                                object : ScrollListener {
                                    override fun onScrollListener() {
                                        /* if(pageCount>2){
                                             getAllMyCases("")
                                         }else
                                         {
                                             getAllMyCases(loaderType)
                                         }*/
                                        // getAllMyCases(loaderType)
                                    }
                                })

                        rv_my_cases.layoutManager = LinearLayoutManager(activity)
                        rv_my_cases.adapter = adapter
                        tv_case_number.text =
                            "Total (" + 0 + ")"
                        no_case_found.visibility = View.VISIBLE
                        rv_my_cases.visibility = View.GONE
                        tv_case_number.visibility = View.GONE
                    }
                }
            }

            override fun onError(message: String) {
                if (loaderType.equals("")) {
                    myCasesView.hideDialog()
                }
                try {
                    adapter!!.stopLoadingMethod("Stop")
                    adapter!!.notifyDataSetChanged()
                } catch (e: Exception) {
                }
            }
        })
    }
}