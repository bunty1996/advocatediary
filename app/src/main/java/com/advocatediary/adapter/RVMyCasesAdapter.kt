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
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.activity.common.CommonActivity
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.myCases.MyCasesDatum
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import java.text.SimpleDateFormat

class RVMyCasesAdapter(
    var context: Context,
    var myCasesList: ArrayList<MyCasesDatum>,
    stopLoading: String,
    var scrollListener: ScrollListener
) :
    RecyclerView.Adapter<RVMyCasesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.rv_my_cases_adapter, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myCasesList.size
    }

    private var stopLoading: String = ""

    fun stopLoadingMethod(stopLoading: String) {
        this.stopLoading = stopLoading
    }

    fun addAllData(myCasesList2: List<MyCasesDatum>) {
        myCasesList.addAll(myCasesList2)
        notifyDataSetChanged()
    }

    fun addSingleData(data1: MyCasesDatum) {
        myCasesList.add(data1)
        notifyDataSetChanged()
    }

    private var lastAnimatedPosition: Int = 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_case_name.text = myCasesList.get(position).getFullName()
        holder.tv_case_type_value.text = myCasesList.get(position).getCaseType()
        holder.tv_place_name.text = myCasesList.get(position).getCourtTitle()

        if (!(myCasesList.get(position).getCaseNextDate().equals(""))) {
            var simpleInputFormat = SimpleDateFormat("yyyy-MM-dd")
            var simpleOutputFormat = SimpleDateFormat("MMM dd, yyyy")

            var date1 = simpleInputFormat.parse(myCasesList.get(position).getCaseNextDate())
            var date2 = simpleOutputFormat.format(date1)
            holder.tv_place_date.text = date2

        }

        val firstLetter = myCasesList.get(position).getFullName()!!.substring(0, 1)
        holder.tv_case_first_name.setText(firstLetter.toUpperCase())

        val gd = GradientDrawable()
        gd.setColor(Color.parseColor(Utils.getColor(firstLetter)))
        // (no gradient)
        //gd.setStroke(2, Color.BLACK);
        gd.cornerRadius = 20f
        gd.shape = GradientDrawable.OVAL
        gd.gradientType = GradientDrawable.RADIAL_GRADIENT
        //gd.setGradientRadius(iv.getWidth()/2);
        holder.tv_case_first_name.setBackground(gd)

        holder.rel_my_case_data.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(context, CommonActivity::class.java)
                intent.putExtra("case_id", myCasesList!!.get(position).getCaseId())
                intent.putExtra("screen_type_", "case_detail")
                context.startActivity(intent)
                Utils.screenOpenCloseAnimation(context as Activity)
            }

        })

        if (stopLoading.equals("")) {
            if (myCasesList.size - 1 == position) {
                holder.rel_progress_bar.visibility = View.VISIBLE
                scrollListener.onScrollListener()
            } else {
                holder.rel_progress_bar.visibility = View.GONE
            }
        } else {
            if (myCasesList.size - 1 == position) {
                holder.rel_progress_bar.visibility = View.INVISIBLE
            } else {
                holder.rel_progress_bar.visibility = View.GONE
            }
        }

        if (position > lastAnimatedPosition) {

            lastAnimatedPosition = position
            Utils.showAnimationList(context, holder.rel_my_case_data)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_case_name = itemView.findViewById(R.id.tv_case_name) as TextView
        var tv_case_type_value = itemView.findViewById(R.id.tv_case_type_value) as TextView
        var tv_place_name = itemView.findViewById(R.id.tv_place_name) as TextView
        var tv_place_date = itemView.findViewById(R.id.tv_place_date) as TextView
        var tv_case_first_name = itemView.findViewById(R.id.tv_case_first_name) as TextView
        var rel_progress_bar = itemView.findViewById<RelativeLayout>(R.id.rel_progress_bar)
        var rel_my_case_data = itemView.findViewById<RelativeLayout>(R.id.rel_my_case_data)
    }
}