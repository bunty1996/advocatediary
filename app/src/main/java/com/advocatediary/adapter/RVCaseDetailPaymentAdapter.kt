package com.advocatediary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.advocatediary.model.caseDetail.CaseDetailCasePaymentHistoryArr
import com.e.advocatediary.R
import java.text.SimpleDateFormat

class RVCaseDetailPaymentAdapter(var context: Context, var paymentList: List<CaseDetailCasePaymentHistoryArr>) :
    RecyclerView.Adapter<RVCaseDetailPaymentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_payment_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var inputSimpleFormat = SimpleDateFormat("yyyy-MM-dd")
        var outputSimpleFormat = SimpleDateFormat("MMM dd, yyyy")
        var date1 = inputSimpleFormat.parse(paymentList.get(position).getCreatedAt())
        var date2 = outputSimpleFormat.format(date1)

        holder.tv_date_view.text = date2

        if (paymentList.get(position).getDeposit().equals(null)) {
            holder.tv_payment_view.text = context.getString(R.string.Rs_symbol) + "0"
        } else {
            holder.tv_payment_view.text = context.getString(R.string.Rs_symbol) + paymentList.get(position).getDeposit()
        }


        if (position == paymentList.size - 1) {
            holder.view_line_payment.visibility = View.GONE
        } else {
            holder.view_line_payment.visibility = View.VISIBLE
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_date_view = itemView.findViewById<TextView>(R.id.tv_date_view)
        var tv_payment_view = itemView.findViewById<TextView>(R.id.tv_payment_view)
        var view_line_payment = itemView.findViewById<View>(R.id.view_line_payment)
    }
}