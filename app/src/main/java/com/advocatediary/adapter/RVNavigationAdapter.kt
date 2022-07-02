package com.advocatediary.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.advocatediary.activity.home.HomeActivity
import com.advocatediary.model.navigation.NavigationData
import com.e.advocatediary.R
import java.util.ArrayList

class RVNavigationAdapter(private val context: Context, var navigationDataArrayList: ArrayList<NavigationData>) :
    RecyclerView.Adapter<RVNavigationAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_navigation_adapter, null)
        return MyViewHolder(view)
    }

    fun updateList(navigationDataArrayList:ArrayList<NavigationData>)
    {
        this.navigationDataArrayList=navigationDataArrayList
        notifyDataSetChanged()
    }

    fun getNavigationList():ArrayList<NavigationData>
    {
        return navigationDataArrayList
    }


    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.tv_drawer_items.setText(navigationDataArrayList[i].getName())
        myViewHolder.iv_image_drawer.setImageResource(navigationDataArrayList[i].getImage())
        if (navigationDataArrayList[i].getCheckStatus().contentEquals("true")) {
            myViewHolder.linear_back.setBackgroundColor(context.getColor(R.color.default_selected_color))
            myViewHolder.iv_image_drawer.setColorFilter(context.getColor(R.color.block_title_color))
            myViewHolder.tv_drawer_items.setTextColor(context.getColor(R.color.block_title_color))

        } else {
            myViewHolder.linear_back.setBackgroundColor(context.getColor(R.color.side_bar_background_color))
            myViewHolder.iv_image_drawer.setColorFilter(context.getColor(R.color.side_bar_navigation_default_color))
            myViewHolder.tv_drawer_items.setTextColor(context.getColor(R.color.side_bar_navigation_default_color))
        }

        myViewHolder.iv_plus_iconn.visibility = View.GONE
        myViewHolder.linear_back.setOnClickListener {
            for (idx in navigationDataArrayList.indices) {
                if (i != 6 && i != 2 && i != 4) {
                    if (i == idx) {
                        if (navigationDataArrayList[idx].getCheckStatus().contentEquals("true")) {
                            /* NavigationData navigationData4 = new NavigationData();
                    navigationData4.setCheckStatus("false");
                    navigationData4.setName(navigationDataArrayList.get(idx).getName());
                    navigationData4.setImage(navigationDataArrayList.get(idx).getImage());
                    navigationDataArrayList.remove(idx);
                    navigationDataArrayList.add(idx, navigationData4);*/
                        } else {
                            val navigationData4 = NavigationData()
                            navigationData4.setCheckStatus("true")
                            navigationData4.setName(navigationDataArrayList[idx].getName())
                            navigationData4.setImage(navigationDataArrayList[idx].getImage())
                            navigationDataArrayList.removeAt(idx)
                            navigationDataArrayList.add(idx, navigationData4)
                        }
                    } else {
                        val navigationData4 = NavigationData()
                        navigationData4.setCheckStatus("false")
                        navigationData4.setName(navigationDataArrayList[idx].getName())
                        navigationData4.setImage(navigationDataArrayList[idx].getImage())
                        navigationDataArrayList.removeAt(idx)
                        navigationDataArrayList.add(idx, navigationData4)
                    }
                    notifyDataSetChanged()
                }
            }
             (context as HomeActivity).setAllData(i)
        }
    }

    override fun getItemCount(): Int {
        return navigationDataArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val iv_image_drawer: ImageView
        val tv_drawer_items: TextView
        val linear_back: LinearLayout
        val iv_plus_iconn: ImageView

        init {
            iv_image_drawer = itemView.findViewById(R.id.iv_image_drawer) as ImageView
            tv_drawer_items = itemView.findViewById(R.id.tv_drawer_items) as TextView
            linear_back = itemView.findViewById(R.id.linear_back) as LinearLayout
            iv_plus_iconn = itemView.findViewById(R.id.iv_plus_iconn) as ImageView
        }
    }
}
