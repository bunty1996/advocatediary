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
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.advocatediary.activity.common.CommonActivity
import com.advocatediary.handler.ScrollListener
import com.advocatediary.model.myClient.MyClientDatum
import com.advocatediary.utils.Utils
import android.R.attr.start
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.AnimationUtils
import com.e.advocatediary.R
import android.view.animation.AlphaAnimation


class RVMyClientAdapter(
    var context: Context,
    var arrayList: ArrayList<MyClientDatum>?, stopLoading: String,
    var scrollListener: ScrollListener
) :
    RecyclerView.Adapter<RVMyClientAdapter.MyViewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewholder {
        var view = LayoutInflater.from(context).inflate(R.layout.my_client_adapter, p0, false)
        return MyViewholder(view)
    }

    fun removeAllData() {
        arrayList!!.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return arrayList!!.size
    }

    fun addNewData(list: ArrayList<MyClientDatum>) {
        arrayList!!.addAll(list)
        notifyDataSetChanged()
    }

    fun addSingleNewData(datum: MyClientDatum) {
        arrayList!!.add(datum)
        notifyDataSetChanged()
    }

    private var stopLoading: String? = ""

    fun stopLoadingMethod(stopLoading: String) {
        this.stopLoading = stopLoading
    }

    private var lastAnimatedPosition: Int = 0

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        //  holder.progressBar_cyclic.visibility = View.GONE

        var value = arrayList!!.get(position).getFullName() as String


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


        holder.tv_user_name.text = arrayList!!.get(position).getFullName()

        holder.tv_total_cases.text = "Total case (" + arrayList!!.get(position).getTotalCase() + ")"

        if (position > lastAnimatedPosition) {

            lastAnimatedPosition = position
            Utils.showAnimationList(context, holder.rel_top)
        }

        holder.rel_top.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(context, CommonActivity::class.java)
                intent.putExtra("client_id", arrayList!!.get(position).getId())
                intent.putExtra("screen_type_", "client_detail")
                context.startActivity(intent)
                Utils.screenOpenCloseAnimation(context as Activity)
            }
        })

        if (stopLoading.equals("")) {
            holder.view_line_client.visibility = View.VISIBLE
            if (arrayList!!.size - 1 == position) {
                holder.linear_progress.visibility = View.VISIBLE
                scrollListener.onScrollListener()
            } else {
                holder.linear_progress.visibility = View.GONE
            }
        } else {

            if (arrayList!!.size - 1 == position) {
                holder.linear_progress.visibility = View.INVISIBLE
                holder.view_line_client.visibility = View.VISIBLE
            } else {
                holder.linear_progress.visibility = View.GONE
                holder.view_line_client.visibility = View.VISIBLE
            }

        }
    }

    class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var progressBar_cyclic = itemView.findViewById(R.id.progressBar_cyclic) as ProgressBar
        var tv_name_first_value = itemView.findViewById(R.id.tv_name_first_value) as TextView
        var tv_user_name = itemView.findViewById(R.id.tv_user_name) as TextView
        var tv_total_cases = itemView.findViewById(R.id.tv_total_cases) as TextView
        var rel_top = itemView.findViewById<RelativeLayout>(R.id.rel_top)

        var linear_progress = itemView.findViewById<RelativeLayout>(R.id.linear_progress)
        var view_line_client = itemView.findViewById<View>(R.id.view_line_client)
    }

}