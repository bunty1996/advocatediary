package com.advocatediary.activity.allClient.allClientPresenter

import android.app.Activity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.advocatediary.activity.allClient.allClientView.AllClientView
import com.advocatediary.adapter.RVAllClientAdapter
import com.advocatediary.handler.AllClientHandler
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.allClients.AllClientDatum
import com.advocatediary.model.allClients.AllClientExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices

class AllClientPresenter(
    var activity: Activity,
    var allClientView: AllClientView,
    var rv_my_clients: RecyclerView,
    var tv_case_number: TextView, var et_client_search: EditText, var no_client_found: RelativeLayout,
   var card_view_all_client:CardView,var linear_add_all_clientss:LinearLayout,var bt_add_client:Button
) {

    private var pageCount: Int = 1
    private var adapter: RVAllClientAdapter? = null

    private lateinit var loaderValue: String

    fun getFirstTimeData(et_client_search: EditText, loaderValue: String) {
        this.et_client_search = et_client_search
        this.loaderValue = loaderValue
        pageCount = 1
        adapter = null
        getAllClient(loaderValue)
    }

    fun getAllClient(loaderValue: String) {
        if (loaderValue.equals("")) {
            if (et_client_search.text.toString().trim().equals("")) {
                allClientView.showDialog(activity)
            }
        }
        var userID = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.getAllMyClientMethod(
            userID,
            et_client_search.text.toString().trim(),
            pageCount,
            object : AllClientHandler {
                override fun onSuccess(example: AllClientExample) {

                    linear_add_all_clientss.visibility=View.VISIBLE
                    bt_add_client.visibility=View.VISIBLE

                    if (loaderValue.equals("")) {

                        if (et_client_search.text.toString().trim().equals("")) {
                            allClientView.hideDialog()
                        }
                    }
                    var emptylist = ArrayList<AllClientDatum>()
                    emptylist.clear()

                    if (adapter == null) {
                        rv_my_clients.layoutManager = LinearLayoutManager(activity)
                        rv_my_clients.adapter = adapter
                    }

                    if (example != null) {

                        if (adapter == null) {
                            if (!(example.getResponse()!!.getData().getFrom().equals(""))) {

                                var currentPage=example.getResponse()!!.getData().getCurrentPage()!!.toInt()
                                pageCount = currentPage + 1
                             //   pageCount = pageCount + 1
                                adapter =
                                    RVAllClientAdapter(activity, example.getResponse()!!.getData().getData()!!,
                                        object : ScrollListener {
                                            override fun onScrollListener() {
                                                getAllClient(loaderValue)
                                            }
                                        })
                                rv_my_clients.layoutManager = LinearLayoutManager(activity)
                                rv_my_clients.adapter = adapter!!

                                tv_case_number.text = "Client (" + adapter!!.getAllList().size + ")"

                                no_client_found.visibility = View.GONE
                                tv_case_number.visibility = View.VISIBLE
                                rv_my_clients.visibility = View.VISIBLE

                                card_view_all_client.visibility=View.VISIBLE
                            } else {

                                adapter = RVAllClientAdapter(activity, emptylist,
                                    object : ScrollListener {
                                        override fun onScrollListener() {
                                        }
                                    })
                                rv_my_clients.layoutManager = LinearLayoutManager(activity)
                                rv_my_clients.adapter = adapter!!
                                tv_case_number.text = "Client (" + 0 + ")"

                                no_client_found.visibility = View.VISIBLE
                                tv_case_number.visibility = View.GONE
                                rv_my_clients.visibility = View.GONE

                                card_view_all_client.visibility=View.GONE
                            }
                        } else {
                            if (!(example.getResponse()!!.getData().getFrom().equals(""))) {
                              //  pageCount = pageCount + 1

                                var currentPage=example.getResponse()!!.getData().getCurrentPage()!!.toInt()
                                pageCount = currentPage + 1

                                for ((idx, value) in example.getResponse()!!.getData().getData()!!.withIndex()) {
                                    var checkValue = ""
                                    for ((idx1, value1) in adapter!!.getAllList().withIndex()) {
                                        if (example.getResponse()!!.getData().getData()!!.get(idx).getId().equals(
                                                adapter!!.getAllList().get(idx1).getId()
                                            )
                                        ) {
                                            checkValue = "true"
                                            break
                                        }
                                    }
                                    if (checkValue.equals("")) {
                                        adapter!!.addSingleData(
                                            example.getResponse()!!.getData().getData()!!.get(
                                                idx
                                            )
                                        )
                                        adapter!!.notifyDataSetChanged()
                                    }
                                }

                                //   adapter!!.addNewData(example.getResponse()!!.getData().getData()!!)

                                tv_case_number.text = "Client (" + adapter!!.getAllList().size + ")"

                                no_client_found.visibility = View.GONE
                                tv_case_number.visibility = View.VISIBLE
                                rv_my_clients.visibility = View.VISIBLE
                                card_view_all_client.visibility=View.VISIBLE

                            } else {
                                allClientView.showMessage(activity, "No More Data")
                                adapter!!.stopLoadingMethod("stop")
                                adapter!!.notifyDataSetChanged()
                            }
                        }
                    } else {
                        adapter = RVAllClientAdapter(activity, emptylist,
                            object : ScrollListener {
                                override fun onScrollListener() {
                                }
                            })
                        rv_my_clients.layoutManager = LinearLayoutManager(activity)
                        rv_my_clients.adapter = adapter!!
                        tv_case_number.text = "Client (" + 0 + ")"

                        no_client_found.visibility = View.VISIBLE
                        tv_case_number.visibility = View.GONE
                        rv_my_clients.visibility = View.GONE
                        card_view_all_client.visibility=View.GONE
                    }
                }

                override fun onError(message: String) {
                    allClientView.hideDialog()
                }
            })
    }
}