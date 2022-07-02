package com.advocatediary.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.e.advocatediary.R
import android.support.v4.view.ViewPager

class RVIntroViewPagerAdapter(var context: Context,var imageList:ArrayList<Int>): PagerAdapter() {
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 === p1
    }

    override fun getCount(): Int {
        return imageList.size
    }



    override fun destroyItem(container: ViewGroup, position: Int, object1: Any) {
   //     super.destroyItem(container, position, `object`)
        (container as ViewPager).removeView(object1 as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var view=LayoutInflater.from(context).inflate(R.layout.intro_view_pager,container,false)
        var iv_pager_image=view!!.findViewById<ImageView>(R.id.iv_pager_image)
       // iv_pager_image.setImageResource(imageList.get(position))
        return view
      //  return super.instantiateItem(container, position)
    }
}