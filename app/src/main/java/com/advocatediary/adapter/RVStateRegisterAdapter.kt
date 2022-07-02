package com.advocatediary.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.activity.register.RegisterActivity
import com.advocatediary.model.state.StateDatum
import com.advocatediary.model.state.StateSelectedData
import com.e.advocatediary.R

class RVStateRegisterAdapter(var context: Context, var stateList: ArrayList<StateSelectedData>,var typeData:String) :
    RecyclerView.Adapter<RVStateRegisterAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_state_register_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stateList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.tv_popup_name.text = stateList.get(p1).getName()

        if (stateList.get(p1).getCheckValue()!!.contentEquals("true")) {
            p0.iv_check.visibility = View.VISIBLE
        } else {
            p0.iv_check.visibility = View.INVISIBLE
        }

        p0.rel_checked_item.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {
                /*if(idx==p1)
                               {
                                   if(stateList.get(idx).getCheckValue()!!.contentEquals("false"))
                                   {
                                       var stateData=StateSelectedData()
                                       stateData.setChekValue("true")
                                       stateData.setName(stateList.get(idx).getName()!!)
                                       stateData.setId(stateList.get(idx).getId()!!)
                                       stateList.removeAt(idx)
                                       stateList.add(idx,stateData)
                                   }
                               }else
                               {
                                   var stateData=StateSelectedData()
                                   stateData.setChekValue("false")
                                   stateData.setName(stateList.get(idx).getName()!!)
                                   stateData.setId(stateList.get(idx).getId()!!)
                                   stateList.removeAt(idx)
                                   stateList.add(idx,stateData)
                               }*/
               // for((idx,value1) in stateList.withIndex()) {

                    (context as RegisterActivity).setData(typeData,stateList.get(p1).getName()!!)

                    //     notifyDataSetChanged()
               // }
            }
        })
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_popup_name = itemView.findViewById(R.id.tv_popup_name) as TextView
        var iv_check = itemView.findViewById(R.id.iv_check) as ImageView
        var rel_checked_item=itemView.findViewById(R.id.rel_checked_item)as RelativeLayout
    }
}

