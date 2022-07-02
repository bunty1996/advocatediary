package com.advocatediary.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.activity.common.CommonActivity
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.particularClient.ParticularClientDatum
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import java.text.SimpleDateFormat

class RVParticularClientAdapter(
    var context: Context,
    var cassesList: ArrayList<ParticularClientDatum>, stopLoader: String,
    var scrollListener: ScrollListener
) :
    RecyclerView.Adapter<RVParticularClientAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_particular_client_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cassesList.size
    }

    private var stopLoader: String? = ""

    fun stopLoadingMethod(stopLoader: String) {
        this.stopLoader = stopLoader
        notifyDataSetChanged()
    }

    fun addAnotherData(subCassesList: ArrayList<ParticularClientDatum>) {
        cassesList.addAll(subCassesList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.tv_case_type.text = cassesList.get(p1).getCaseType()
        p0.tv_court_nameee.text = cassesList.get(p1).getCourtTitle()

        p0.tv_statuss.text = cassesList.get(p1).getPurposeHearing()

        if (!(cassesList.get(p1).getCaseNextDate().equals(""))) {
            var inputFormat = SimpleDateFormat("yyyy-MM-dd")
            var outputFormat = SimpleDateFormat("MMM dd, yyyy")
            var date1 = inputFormat.parse(cassesList.get(p1).getCaseNextDate())
            var date2 = outputFormat.format(date1)
            p0.tv_court_datee.text = date2
        }


        p0.linear_detail_case.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(context, CommonActivity::class.java)
                intent.putExtra("case_id", cassesList.get(p1).getId())
                intent.putExtra("screen_type_", "case_detail")
                context.startActivity(intent)
                Utils.screenOpenCloseAnimation(context as Activity)
            }
        })

        if (stopLoader.equals("")) {
            if (cassesList.size - 1 == p1) {
                p0.rel_progress_bar.visibility = View.VISIBLE
                scrollListener.onScrollListener()
            } else {
                p0.rel_progress_bar.visibility = View.GONE
            }
        } else {
            p0.rel_progress_bar.visibility = View.GONE
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_case_type = itemView.findViewById<TextView>(R.id.tv_case_type)
        var tv_court_nameee = itemView.findViewById<TextView>(R.id.tv_court_nameee)
        var tv_court_datee = itemView.findViewById<TextView>(R.id.tv_court_datee)
        var tv_statuss = itemView.findViewById<TextView>(R.id.tv_statuss)
        var rel_progress_bar = itemView.findViewById<RelativeLayout>(R.id.rel_progress_bar)
        var linear_detail_case = itemView.findViewById<LinearLayout>(R.id.linear_detail_case)
    }
}