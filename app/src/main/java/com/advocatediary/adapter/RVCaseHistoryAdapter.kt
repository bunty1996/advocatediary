package com.advocatediary.adapter

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.advocatediary.model.caseDetail.CaseDetailCaseAllDetail
import com.e.advocatediary.R
import java.text.SimpleDateFormat

class RVCaseHistoryAdapter(var context: Context, var historyList: List<CaseDetailCaseAllDetail>) :
    RecyclerView.Adapter<RVCaseHistoryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_case_history_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (!(historyList.get(position).getPreviousCaseDate().equals(""))) {
            var inputPrevious = SimpleDateFormat("yyyy-MM-dd")
            var outputPrevious = SimpleDateFormat("MMM dd, yyyy")
            var date1 = inputPrevious.parse(historyList.get(position).getPreviousCaseDate())
            var date2 = outputPrevious.format(date1)
            holder.tv_first_text_case.text = date2

        } else {
            var inputPrevious = SimpleDateFormat("yyyy-MM-dd")
            var outputPrevious = SimpleDateFormat("MMM dd, yyyy")
            var date1 = inputPrevious.parse(historyList.get(position).getDateOfFileing())
            var date2 = outputPrevious.format(date1)
            holder.tv_first_text_case.text = date2
        }
        var inputNext = SimpleDateFormat("yyyy-MM-dd")
        var outputNext = SimpleDateFormat("MMM dd, yyyy")
        var date3 = inputNext.parse(historyList.get(position).getCaseNextDate())
        var date4 = outputNext.format(date3)
        holder.tv_second_text_case.text = date4

        holder.tv_pending_payment.text = historyList.get(position).getPurposeHearing()

        if (historyList.get(position).getNotes()!!.trim().length > 0) {
            holder.iv_info_icon.visibility = View.VISIBLE
        } else {
            holder.iv_info_icon.visibility = View.INVISIBLE
        }

        if (historyList.size - 1 == position) {
            holder.view_line.visibility = View.GONE
        } else {
            holder.view_line.visibility = View.VISIBLE
        }

        holder.linear_nots_click.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if (!(historyList.get(position).getNotes()!!.trim().equals(""))) {
                    showDialog(context, historyList.get(position).getNotes()!!.trim())
                }
            }
        })
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_first_text_case = itemView.findViewById<TextView>(R.id.tv_first_text_case)
        var tv_second_text_case = itemView.findViewById<TextView>(R.id.tv_second_text_case)
        var tv_pending_payment = itemView.findViewById<TextView>(R.id.tv_pending_payment)
        var iv_info_icon = itemView.findViewById<ImageView>(R.id.iv_info_icon)
        var linear_nots_click = itemView.findViewById<LinearLayout>(R.id.linear_nots_click)
        var view_line = itemView.findViewById<View>(R.id.view_line)
    }

    private lateinit var alertDialog: AlertDialog

    fun showDialog(context: Context, notes: String) {
        var alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Notes")
        alertDialogBuilder.setMessage(notes)
        alertDialogBuilder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                alertDialog!!.dismiss()
            }

        })
        alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}