package com.advocatediary.fragment.clientDetail.clientDetailPresenter

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.advocatediary.adapter.RVParticularClientAdapter
import com.advocatediary.fragment.clientDetail.clientDetailView.ClientDetailView
import com.advocatediary.handler.ClientDetailHandler
import com.advocatediary.handler.ParticularClientHandler
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.clientDeatail.ClientDetailExample
import com.advocatediary.model.particularClient.ParticularClientExample
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.WebServices
import java.lang.Exception

class ClientDetailPresenter(
    var activity: Activity,
    var clientDetailView: ClientDetailView,
    var rv_client_details: RecyclerView, var tv_total_cases: TextView
) {

    fun resetAllData(clientId: String, loaderType: String) {
        adaapter = null
        pageCount = 1;
        getClicentDetail(clientId, loaderType)
    }

    private var pageCount: Int = 0
    fun getClicentDetail(clientId: String, loaderType: String) {
        var user_id = CsPreferences.readString(activity, Constants.USER_ID)
        clientDetailView.showDialog(activity)
        WebServices.Factory1.getInstance()!!.getClientDetailsMethod(user_id, clientId, object : ClientDetailHandler {
            override fun onSuccess(example: ClientDetailExample) {
                pageCount = 1;
                if (example != null) {
                    clientDetailView.setClientData(example.getResponse()!!.getData()!!)
                    getParticularClient(clientId)
                }
            }

            override fun onError(message: String) {
                clientDetailView.hideDialog()
            }
        })
    }

    private var adaapter: RVParticularClientAdapter? = null

    fun getParticularClient(clientId: String) {
        var user_id = CsPreferences.readString(activity, Constants.USER_ID)
        WebServices.Factory1.getInstance()!!.getAllCassForParticularClient(
            user_id,
            clientId,
            pageCount,
            "",
            object : ParticularClientHandler {
                override fun onSuccess(example: ParticularClientExample) {
                    clientDetailView.hideDialog()
                    if (example != null) {

                        if (adaapter == null) {
                            pageCount = pageCount + 1;
                            adaapter =
                                RVParticularClientAdapter(activity, example.getResponse()!!.getData()!!.getData()!!, "",
                                    object : ScrollListener {
                                        override fun onScrollListener() {
                                            getParticularClient(clientId)
                                        }
                                    })
                            rv_client_details.layoutManager = LinearLayoutManager(activity)
                            rv_client_details.adapter = adaapter
                            tv_total_cases.text =
                                "Cases (" + (example.getResponse()!!.getData()!!.getData()!!.size).toString() + ")"
                        } else {
                            pageCount = pageCount + 1;
                            if (example.getResponse()!!.getData()!!.getData()!!.size > 0) {
                                adaapter!!.addAnotherData(example.getResponse()!!.getData()!!.getData()!!)
                                adaapter!!.notifyDataSetChanged()
                            } else {
                                adaapter!!.stopLoadingMethod("stop")
                                adaapter!!.notifyDataSetChanged()
                            }
                        }
                    } else {
                        try {
                            adaapter!!.stopLoadingMethod("stop")
                            adaapter!!.notifyDataSetChanged()
                        } catch (e: Exception) {
                        }
                    }

                    if (adaapter == null) {
                        if (example.getResponse()!!.getData()!!.getFrom().equals("")) {
                            tv_total_cases.text = "Cases (0)"
                        }
                    }
                }

                override fun onError(message: String) {
                    clientDetailView.hideDialog()
                    try {
                        adaapter!!.stopLoadingMethod("stop")
                        adaapter!!.notifyDataSetChanged()
                        rv_client_details.visibility = View.GONE
                        tv_total_cases.text = "Cases (0)"
                    } catch (e: Exception) {
                        rv_client_details.visibility = View.GONE
                        tv_total_cases.text = "Cases (0)"
                    }
                }
            })
    }
}