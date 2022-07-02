package com.advocatediary.fragment.myClient.myClientPresenter

import android.app.Activity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.adapter.RVMyClientAdapter
import com.advocatediary.fragment.myClient.myClientView.MyClientView
import com.advocatediary.handler.MyClientHandler
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.myClient.MyClientDatum
import com.advocatediary.model.myClient.MyClientExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices
import com.github.clans.fab.FloatingActionMenu

import java.lang.Exception

class MyClientPresenter(
    var activity: Activity,
    var clientView: MyClientView,
    var rv_my_clients: RecyclerView,
    var tv_case_number: TextView,
    var search_value: String,
    var no_client_found: RelativeLayout,
    var card_view_clientss: CardView,

    var linear_all_my_client: LinearLayout,
    var menu_fab_client: FloatingActionMenu
) {
    private var pageCount: Int = 1
    private var rvClientAdapter: RVMyClientAdapter? = null


    fun startgetMyClient(search_value: String, loaderType: String) {
        this.search_value = search_value
        pageCount = 1
        rvClientAdapter = null
        getAllMyClient(loaderType)
    }

    fun refreshData(loaderType: String) {
        pageCount = 1
        rvClientAdapter = null
        getAllMyClient(loaderType)
    }

    fun getAllMyClient(loaderType: String) {
        if (loaderType.equals("")) {
            clientView.showDialog(activity)
        }
        var userId = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()
            ?.getAllMyClientsMethod(userId, pageCount, search_value, object : MyClientHandler {
                override fun onSuccess(myClientExample: MyClientExample) {

                    linear_all_my_client.visibility=View.VISIBLE
                    menu_fab_client.visibility=View.VISIBLE

                    if (loaderType.equals("")) {
                        clientView.hideDialog()
                    }

                    Log.e("search_valueqqqqqqqq===", search_value)
                    Log.e("pageCount", pageCount.toString())

                    var list2 = ArrayList<MyClientDatum>()
                    list2.clear()

                    if (rvClientAdapter == null) {

                        rv_my_clients.layoutManager = LinearLayoutManager(activity)
                        rv_my_clients.adapter = rvClientAdapter
                        //rv_my_clients.removeAllViews()
                        //  rvClientAdapter?.removeAllData()
                    }

                    /* tv_case_number.text =
                         "Total (" + 0 + ")"*/
                    if (myClientExample != null) {
                        if (rvClientAdapter == null) {
                            if (!(myClientExample.getResponse()!!.getData()!!.getFrom().equals(""))) {
                                if (myClientExample.getResponse()!!.getData()!!.getData()!!.size > 0) {

                                    var currentPage =
                                        myClientExample.getResponse()!!.getData()!!.getCurrentPage()!!.toInt()
                                    pageCount = currentPage + 1
                                    rvClientAdapter =
                                        RVMyClientAdapter(activity,
                                            myClientExample.getResponse()!!.getData()!!.getData()!!,
                                            "",
                                            object : ScrollListener {
                                                override fun onScrollListener() {
                                                    //  if (pageCount < 3) {
                                                    getAllMyClient(loaderType)

                                                    /*   } else {
                                                           getAllMyClient("")
                                                       }*/
                                                }
                                            })

                                    rv_my_clients.layoutManager = LinearLayoutManager(activity)
                                    rv_my_clients.adapter = rvClientAdapter

                                    tv_case_number.text =
                                        "Total (" + myClientExample.getResponse()!!.getData()!!.getData()!!.size + ")"


                                    no_client_found.visibility = View.GONE
                                    tv_case_number.visibility = View.VISIBLE
                                    rv_my_clients.visibility = View.VISIBLE
                                    card_view_clientss.visibility = View.VISIBLE


                                    rv_my_clients.setTranslationY(rv_my_clients.getHeight().toFloat())
                                    rv_my_clients.setAlpha(0f)
                                    rv_my_clients.animate()
                                        .translationY(0f)
                                        .setDuration(1500)
                                        .alpha(1f)
                                        .setInterpolator(AccelerateDecelerateInterpolator())
                                        .start()

                                }
                            } else {
                                if (rvClientAdapter == null) {
                                    rvClientAdapter =
                                        RVMyClientAdapter(activity,
                                            list2,
                                            "",
                                            object : ScrollListener {
                                                override fun onScrollListener() {
                                                }
                                            })

                                    rv_my_clients.layoutManager = LinearLayoutManager(activity)
                                    rv_my_clients.adapter = rvClientAdapter
                                    //  rvClientAdapter?.removeAllData()

                                    tv_case_number.text =
                                        "Total (" + 0 + ")"

                                    no_client_found.visibility = View.VISIBLE
                                    tv_case_number.visibility = View.GONE
                                    rv_my_clients.visibility = View.GONE
                                    card_view_clientss.visibility = View.GONE
                                }

                            }
                        } else {

                            if (!(myClientExample!!.getResponse()!!.getData()!!.getFrom().equals(""))) {
                                if (myClientExample!!.getResponse()!!.getData()!!.getData()!!.size > 0) {
                                    //pageCount = pageCount + 1

                                    var currentPage =
                                        myClientExample.getResponse()!!.getData()!!.getCurrentPage()!!.toInt()
                                    pageCount = currentPage + 1


                                    for ((idx1, value1) in myClientExample!!.getResponse()!!.getData()!!.getData()!!.withIndex()) {
                                        var checkValue = ""
                                        for ((idx, value) in rvClientAdapter!!.arrayList!!.withIndex()) {
                                            if (rvClientAdapter!!.arrayList!!.get(idx).getId().equals(
                                                    myClientExample!!.getResponse()!!.getData()!!.getData()!!.get(
                                                        idx1
                                                    ).getId()
                                                )
                                            ) {
                                                checkValue = "true"
                                                break
                                            }
                                        }
                                        if (checkValue.equals("")) {
                                            //    rvClientAdapter!!.addNewData(myClientExample.getResponse()!!.getData()!!.getData()!!)
                                            rvClientAdapter!!.addSingleNewData(
                                                myClientExample.getResponse()!!.getData()!!.getData()!!.get(
                                                    idx1
                                                )
                                            )
                                            rvClientAdapter!!.notifyDataSetChanged()
                                        }
                                    }

                                    tv_case_number.text =
                                        "Total (" + rvClientAdapter!!.arrayList!!.size.toString() + ")"

                                    no_client_found.visibility = View.GONE
                                    tv_case_number.visibility = View.VISIBLE
                                    rv_my_clients.visibility = View.VISIBLE
                                    card_view_clientss.visibility = View.VISIBLE


                                } else {
                                    rvClientAdapter!!.stopLoadingMethod("stop")
                                    rvClientAdapter!!.notifyDataSetChanged()
                                }
                            } else {

                                try {
                                    if (rvClientAdapter != null) {
                                        clientView.showMessage(activity, "No More Data")
                                        rvClientAdapter!!.stopLoadingMethod("stop")
                                        rvClientAdapter!!.notifyDataSetChanged()
                                    }
                                } catch (e: Exception) {

                                }

                            }
                        }
                    } else {
                        if (rvClientAdapter == null) {
                            rvClientAdapter =
                                RVMyClientAdapter(activity,
                                    list2,
                                    "",
                                    object : ScrollListener {
                                        override fun onScrollListener() {

                                        }
                                    })

                            rv_my_clients.layoutManager = LinearLayoutManager(activity)
                            rv_my_clients.adapter = rvClientAdapter
                            tv_case_number.text =
                                "Total (" + 0 + ")"
                            no_client_found.visibility = View.VISIBLE
                            tv_case_number.visibility = View.GONE
                            rv_my_clients.visibility = View.GONE
                            card_view_clientss.visibility = View.GONE
                            //  rvClientAdapter?.removeAllData()
                        }
                    }
                }

                override fun onError(message: String) {
                    clientView.hideDialog()

                    try {
                        rvClientAdapter!!.stopLoadingMethod("stop")
                        rvClientAdapter!!.notifyDataSetChanged()
                    } catch (e: Exception) {

                    }
                }
            })
    }
}