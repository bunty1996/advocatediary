package com.advocatediary.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.activity.addCaseInfo.AddCaseInfoActivity
import com.advocatediary.activity.common.CommonActivity
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.allClients.AllClientDatum
import com.advocatediary.utils.Utils
import com.e.advocatediary.R

class RVAllClientAdapter(
    var context: Context,
    var clientList: ArrayList<AllClientDatum>,
    var scrollListener: ScrollListener
) : RecyclerView.Adapter<RVAllClientAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_all_client_adapter, p0, false)
        return MyViewHolder(view)
    }

    private var stopLoading: String = ""

    fun stopLoadingMethod(stopLoading: String) {
        this.stopLoading = stopLoading
    }

    fun addNewData(clientList1: ArrayList<AllClientDatum>) {
        clientList.addAll(clientList1)
        notifyDataSetChanged()
    }

    fun getAllList(): ArrayList<AllClientDatum> {
        return clientList;
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    fun addSingleData(dataum: AllClientDatum) {
        clientList.add(dataum)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tv_user_name.text = clientList.get(position).getFullName()!!.trim()

        var value = clientList!!.get(position).getFullName() as String
        val firstLetter = value.substring(0, 1)
        holder.tv_name_first_value.setText(firstLetter.toUpperCase())

        val gd = GradientDrawable()
        gd.setColor(Color.parseColor(Utils.getColor(firstLetter)))
        // (no gradient)
        //gd.setStroke(2, Color.BLACK);
        gd.cornerRadius = 20f
        gd.shape = GradientDrawable.OVAL
        gd.gradientType = GradientDrawable.RADIAL_GRADIENT
        //gd.setGradientRadius(iv.getWidth()/2);
        holder.tv_name_first_value.setBackground(gd)

        holder.tv_total_cases.text = "Total Case (" + clientList.get(position).getTotalCase() + ")"

        if (stopLoading!!.equals("")) {
            if (clientList.size - 1 == position) {
                holder.linear_progress_clients.visibility = View.VISIBLE
                scrollListener.onScrollListener()
            } else {
                holder.linear_progress_clients.visibility = View.GONE
            }
        } else {
            holder.linear_progress_clients.visibility = View.GONE
        }


        holder.rel_top.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(context, AddCaseInfoActivity::class.java)
                intent.putExtra("name", clientList.get(position).getFullName()!!.toString())
                intent.putExtra("email", clientList.get(position).getEmail().toString())
                intent.putExtra("client_id", clientList.get(position)!!.getId().toString())
                intent.putExtra("case_id", "")

                context.startActivity(intent)
                Utils.screenOpenCloseAnimation(context as Activity)
            }

        })
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_name_first_value = itemView.findViewById<TextView>(R.id.tv_name_first_value)
        var tv_user_name = itemView.findViewById<TextView>(R.id.tv_user_name)
        var tv_total_cases = itemView.findViewById<TextView>(R.id.tv_total_cases)
        var linear_progress_clients = itemView.findViewById<LinearLayout>(R.id.linear_progress_clients)
        var rel_top = itemView.findViewById<RelativeLayout>(R.id.rel_top)
    }
}