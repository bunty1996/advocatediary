package com.advocatediary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.advocatediary.handler.OnclickListener
import com.advocatediary.model.stateDistricts.StateCheckData
import com.e.advocatediary.R

class RVPurposeAdapter(
    var context: Context,
    var stateCheckList: ArrayList<StateCheckData>,
    typeData: String,
    var clickListener: OnclickListener
) : RecyclerView.Adapter<RVPurposeAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_state_register_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stateCheckList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_popup_name.text = stateCheckList.get(position).getName()

        if (stateCheckList.get(position).getCheckValue().equals("true")) {
            holder.iv_check.visibility = View.VISIBLE
        } else {
            holder.iv_check.visibility = View.INVISIBLE
        }

        holder.linear_checked.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                for (idx in stateCheckList.indices) {
                    var stateCheckData = StateCheckData()
                    if (idx == position) {
                        if (stateCheckList.get(idx).getCheckValue().equals("true")) {

                            stateCheckData.setName(stateCheckList.get(idx).getName()!!)
                            stateCheckData.setId(stateCheckList.get(idx).getId()!!)
                            stateCheckData.setCheckValue("false")
                            clickListener.onClcikListener("", -1)
                        } else {
                            stateCheckData.setName(stateCheckList.get(idx).getName()!!)
                            stateCheckData.setId(stateCheckList.get(idx).getId()!!)
                            stateCheckData.setCheckValue("true")
                            clickListener.onClcikListener(stateCheckList.get(idx).getName()!!, position)
                        }
                    } else {
                        stateCheckData.setName(stateCheckList.get(idx).getName()!!)
                        stateCheckData.setId(stateCheckList.get(idx).getId()!!)
                        stateCheckData.setCheckValue("false")
                    }

                    stateCheckList.removeAt(idx)
                    stateCheckList.add(idx, stateCheckData)
                    notifyDataSetChanged()
                }
            }

        })
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_popup_name = itemView.findViewById<TextView>(R.id.tv_popup_name)
        var iv_check = itemView.findViewById<ImageView>(R.id.iv_check)
        var linear_checked = itemView.findViewById<LinearLayout>(R.id.linear_checked)

    }
}