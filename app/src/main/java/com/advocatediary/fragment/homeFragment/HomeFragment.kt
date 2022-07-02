package com.advocatediary.fragment.homeFragment

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.advocatediary.activity.home.HomeActivity
import com.advocatediary.fragment.homeFragment.homePresenter.HomePresenter
import com.advocatediary.fragment.homeFragment.homeView.HomeView
import com.advocatediary.model.casePurposes.CasePurposesDatum
import com.advocatediary.utils.Constants
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import com.advocatediary.activity.addClient.AddClientActivity
import com.advocatediary.activity.allClient.AllClientActivity
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import kotlinx.android.synthetic.main.case_detail.*
import pl.droidsonroids.gif.GifImageView
import java.lang.Exception


class HomeFragment : Fragment(), View.OnClickListener, HomeView {
    override fun setEmptyValues(selectedPosition: Int) {
        setTabVisibility(1)
    }

    lateinit var context: Activity

    lateinit var linear_today: LinearLayout
    lateinit var linear_tomorrow: LinearLayout
    lateinit var linear_pending: LinearLayout
    lateinit var rv_today_list: RecyclerView
    lateinit var rv_pending_list_home: RecyclerView
    lateinit var rv_tommorrow_list: RecyclerView
    lateinit var tv_today_time: TextView
    lateinit var tv_tommorrow_time: TextView

    lateinit var tv_total_today_case: TextView
    lateinit var tv_total_tommorrow_case: TextView
    lateinit var tv_pending_case_total: TextView
    lateinit var rel_top_internet: RelativeLayout
    lateinit var linear_full_layout: LinearLayout


    lateinit var img_today_down: ImageView
    lateinit var img_tomorrow_down: ImageView
    lateinit var img_pending_down: ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(
            R.layout.home_fragment, container,
            false
        ) as View
        context = this!!.activity!!

        initUI(view)

        return view
    }

    private lateinit var homePresenter: HomePresenter
    var card_view_no_today_case: RelativeLayout? = null
    var card_view_no_tommorrow_case: RelativeLayout? = null
    var card_view_no_pending_case: RelativeLayout? = null

    private lateinit var receiver: BroadcastReceiver
    private lateinit var linear_first: LinearLayout
    private lateinit var linear_second: LinearLayout
    private lateinit var linear_weight_third: LinearLayout
    private lateinit var linear_tommorrow_list: LinearLayout
    private lateinit var linear_pending_layout: LinearLayout
    private lateinit var todaylist: LinearLayout


    private lateinit var menu_fab_home: FloatingActionMenu
    private lateinit var fab_add_client_home: FloatingActionButton
    private lateinit var fab_add_case_home: FloatingActionButton
    private lateinit var iv_gif_no_internet: GifImageView

    override fun onResume() {
        super.onResume()
        // for set serach image display
        var intent = Intent()
        intent.setAction("search_display")
        intent.putExtra("serach_position", 0)
        context!!.sendBroadcast(intent)
    }

    private var screenheight: Int = 0

    private fun initUI(view: View?) {

        val lp = WindowManager.LayoutParams()

        val displayMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        // int height = displayMetrics.heightPixels;
        screenheight = displayMetrics.heightPixels

        iv_gif_no_internet = view!!.findViewById<GifImageView>(R.id.iv_gif_no_internet)
        iv_gif_no_internet.setImageResource(R.raw.no_internet_connection)

        menu_fab_home = view!!.findViewById<FloatingActionMenu>(R.id.menu_fab_home)
        fab_add_client_home = view!!.findViewById<FloatingActionButton>(R.id.fab_add_client_home)
        fab_add_case_home = view!!.findViewById<FloatingActionButton>(R.id.fab_add_case_home)
        fab_add_client_home.setOnClickListener(this)
        fab_add_case_home.setOnClickListener(this)

        linear_first = view!!.findViewById<LinearLayout>(R.id.linear_first)
        linear_second = view!!.findViewById<LinearLayout>(R.id.linear_second)
        linear_weight_third = view!!.findViewById<LinearLayout>(R.id.linear_weight_third)
        todaylist = view!!.findViewById<LinearLayout>(R.id.todaylist)

        linear_tommorrow_list = view!!.findViewById<LinearLayout>(R.id.linear_tommorrow_list)
        linear_pending_layout = view!!.findViewById<LinearLayout>(R.id.linear_pending_layout)

        card_view_no_today_case = view?.findViewById(R.id.card_view_no_today_case)
        card_view_no_tommorrow_case = view?.findViewById(R.id.card_view_no_tommorrow_case)
        card_view_no_pending_case = view?.findViewById(R.id.card_view_no_pending_case)

        tv_today_time = view?.findViewById(R.id.tv_today_time) as TextView
        tv_tommorrow_time = view?.findViewById(R.id.tv_tommorrow_time) as TextView
        tv_total_today_case = view?.findViewById(R.id.tv_total_today_case) as TextView
        tv_total_tommorrow_case = view?.findViewById(R.id.tv_total_tommorrow_case) as TextView
        tv_pending_case_total = view?.findViewById(R.id.tv_pending_case_total) as TextView


        linear_today = view?.findViewById(R.id.linear_today) as LinearLayout
        linear_tomorrow = view?.findViewById(R.id.linear_tomorrow) as LinearLayout
        linear_pending = view?.findViewById(R.id.linear_pending) as LinearLayout
        rv_today_list = view?.findViewById(R.id.rv_today_list) as RecyclerView
        rv_pending_list_home = view?.findViewById(R.id.rv_pending_list_home) as RecyclerView
        rv_tommorrow_list = view?.findViewById(R.id.rv_tommorrow_list) as RecyclerView

        rel_top_internet = view.findViewById(R.id.rel_top_internet) as RelativeLayout
        linear_full_layout = view.findViewById(R.id.linear_full_layout) as LinearLayout

        linear_today.setOnClickListener(this)
        linear_tomorrow.setOnClickListener(this)
        linear_pending.setOnClickListener(this)

        img_today_down = view.findViewById<ImageView>(R.id.img_today_down)
        img_tomorrow_down = view.findViewById<ImageView>(R.id.img_tomorrow_down)
        img_pending_down = view.findViewById<ImageView>(R.id.img_pending_down)

        val list2 = ArrayList<String>()
        list2.add("ACtion")
        list2.add("World")

        if (Utils.Factory.isNetworkAvailable(context)) {

            rel_top_internet.visibility = View.GONE
            linear_full_layout.visibility = View.GONE
            menu_fab_home.visibility = View.GONE

            homePresenter = HomePresenter(
                context,
                this,
                rv_today_list,
                tv_today_time,
                tv_tommorrow_time,
                tv_total_today_case,
                tv_total_tommorrow_case,
                rv_tommorrow_list,
                rv_pending_list_home,
                tv_pending_case_total,
                HomeFragment(),
                card_view_no_today_case!!,
                card_view_no_tommorrow_case!!,
                card_view_no_pending_case!!,linear_full_layout,menu_fab_home
            )

            homePresenter!!.getAllPuposesMethod("");
            //    setTabVisibility(1)
            // homePresenter.getAllHomeData()


        } else {
            rel_top_internet.visibility = View.VISIBLE
            linear_full_layout.visibility = View.GONE
            menu_fab_home.visibility = View.GONE
        }


        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // for refresh screens
                try {
                    homePresenter!!.getAllPuposesMethod("update");
                    setTabVisibility(1)
                } catch (e: Exception) {

                }
            }
        }

        context.registerReceiver(receiver, object : IntentFilter(Constants.HOME_UPDATE) {

        })
    }

    override fun onClick(v: View?) {
        when (v) {
            linear_today -> showTodayData()
            linear_tomorrow -> showTomorrowData()
            linear_pending -> showPendingData()

            fab_add_client_home -> gotoClientScreen()
            fab_add_case_home -> gotoAddCaseScreen()

        }
    }

    fun gotoClientScreen() {
        menu_fab_home.close(true)
        var intent = Intent(activity, AddClientActivity::class.java)
        intent.putExtra("client_id", "")
        startActivity(intent)
        Utils.screenOpenCloseAnimation(context)
    }

    fun gotoAddCaseScreen() {
        menu_fab_home.close(true)
        var addCaseInfoActivity = Intent(activity, AllClientActivity::class.java)
        startActivity(addCaseInfoActivity)
        Utils.screenOpenCloseAnimation(context)
    }


    public fun showTodayData() {
        setTabVisibility(1)
    }

    public fun showTomorrowData() {
        setTabVisibility(2)
    }

    public fun showPendingData() {
        setTabVisibility(3)
    }


    fun defaultValueForTabs() {
        img_today_down.setImageResource(R.drawable.down_arrow)
        rv_today_list.visibility = View.GONE
        todaylist.visibility = View.GONE
        //  setAllWeight(1,1,1);
        // todaylist.setVisibility(ChangeView.GONE);
        todaylist.post {
            val height = todaylist.height

            val params = todaylist.layoutParams

            //   params.height =  0;
            //params.width =  400;

            // todaylist.setLayoutParams(params);
        }

        img_tomorrow_down.setImageResource(R.drawable.down_arrow)
        rv_tommorrow_list.visibility = View.GONE
        linear_tommorrow_list.setVisibility(View.GONE)

        linear_tommorrow_list.post(Runnable {
            val params = linear_tommorrow_list.getLayoutParams()

            //  params.height = 0;

            // linear_tommorrow_list.setLayoutParams(params);
        })



        img_pending_down.setImageResource(R.drawable.down_arrow)
        linear_pending_layout.setVisibility(View.GONE)

        linear_pending_layout.post(Runnable {
            val height = linear_pending_layout.getHeight()

            val params = linear_pending_layout.getLayoutParams()

            // params.height = 0;
            //params.width =  400;

            // linear_pending_layout.setLayoutParams(params);
        })

    }


    public fun setTabVisibility(value: Int) {


        img_today_down.rotation = 0f
        img_tomorrow_down.rotation = 0f
        img_pending_down.rotation = 0f


        /*   val params1 = rv_today_list.getLayoutParams()
     // Changes the height and width to the specified *pixels*
             params1.height = 100
             //  params.width = 100
         rv_today_list.setLayoutParams(params1)
         rv_today_list.requestLayout()

             val params2 = linear_second.getLayoutParams()
     // Changes the height and width to the specified *pixels*
             params2.height = 100
             //  params.width = 100
             linear_second.setLayoutParams(params2)
             linear_second.requestLayout()

             val params3 = linear_weight_third.getLayoutParams()
     // Changes the height and width to the specified *pixels*
             params3.height = 100
             //  params.width = 100
             linear_weight_third.setLayoutParams(params3)
             linear_weight_third.requestLayout()*/


        defaultValueForTabs()

        if (value == 1) {
            img_today_down.rotation = 180f
            rv_today_list.visibility = View.VISIBLE
            rv_tommorrow_list.visibility = View.GONE
            rv_pending_list_home.visibility = View.GONE
            homePresenter.setNoDataValue(1)

            img_today_down.setImageResource(R.drawable.top_arrow)

            // rv_today_list.visibility = View.VISIBLE
            todaylist.visibility = View.VISIBLE

            todaylist.post {
                val height = todaylist.height

                if (height + 750 > screenheight) {

                    val params = todaylist.layoutParams

                    params.height = screenheight - 750
                    //params.width =  400;

                    todaylist.layoutParams = params
                } else {

                }


                rv_today_list.setTranslationY(rv_today_list.getHeight().toFloat())
                rv_today_list.setAlpha(0f)
                rv_today_list.animate()
                    .translationY(0f)
                    .setDuration(1100)
                    .alpha(1f)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()

            }

        } else
            if (value == 2) {
                img_tomorrow_down.rotation = 180f
                rv_today_list.visibility = View.GONE
                rv_tommorrow_list.visibility = View.VISIBLE
                rv_pending_list_home.visibility = View.GONE

                homePresenter.setNoDataValue(2)

                img_tomorrow_down.setImageResource(R.drawable.top_arrow)
                // tomorrowlist.setVisibility(ChangeView.GONE);
                // rv_tommorrow_list.visibility = View.VISIBLE

                linear_tommorrow_list.setVisibility(View.VISIBLE)
                linear_tommorrow_list.post(Runnable {
                    val height = linear_tommorrow_list.getHeight()

                    if (height + 750 > screenheight) {

                        val params = linear_tommorrow_list.getLayoutParams()

                        params.height = screenheight - 750
                        //params.width =  400;

                        linear_tommorrow_list.setLayoutParams(params)


                    } else {

                    }

                    rv_tommorrow_list.setTranslationY(rv_tommorrow_list.getHeight().toFloat())
                    rv_tommorrow_list.setAlpha(0f)
                    rv_tommorrow_list.animate()
                        .translationY(0f)
                        .setDuration(1100)
                        .alpha(1f)
                        .setInterpolator(AccelerateDecelerateInterpolator())
                        .start()

                })
            } else
                if (value == 3) {
                    img_pending_down.rotation = 180f
                    rv_today_list.visibility = View.GONE
                    rv_tommorrow_list.visibility = View.GONE
                    //   rv_pending_list_home.visibility = View.VISIBLE
                    homePresenter.setNoDataValue(3)


                    img_pending_down.setImageResource(R.drawable.top_arrow)
                    linear_pending_layout.setVisibility(View.VISIBLE)

                    linear_pending_layout.setVisibility(View.VISIBLE)
                    linear_pending_layout.post(Runnable {
                        val height = linear_pending_layout.getHeight()

                        if (height + 750 > screenheight) {

                            val params = linear_pending_layout.getLayoutParams()

                            params.height = screenheight - 750
                            //params.width =  400;

                            linear_pending_layout.setLayoutParams(params)

                        } else {

                        }

                        rv_pending_list_home.setTranslationY(rv_pending_list_home.getHeight().toFloat())
                        rv_pending_list_home.setAlpha(0f)
                        rv_pending_list_home.animate()
                            .translationY(0f)
                            .setDuration(1100)
                            .alpha(1f)
                            .setInterpolator(AccelerateDecelerateInterpolator())
                            .start()
                    })
                }


        val params = rv_today_list.getLayoutParams()
        var calculatedHeight =
            params.height + rv_tommorrow_list.layoutParams.height + rv_pending_list_home.layoutParams.height

        Log.e("efaffsc", "efdcx")
    }

    override fun showDialog(activity: Activity) {
        Utils.showDialog(activity)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private lateinit var purposeList: List<CasePurposesDatum>

    override fun setPurposeList(purposeList: List<CasePurposesDatum>?) {
        this.purposeList = purposeList!!
    }
}