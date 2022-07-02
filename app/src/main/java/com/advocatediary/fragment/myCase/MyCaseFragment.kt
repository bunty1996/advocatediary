package com.advocatediary.fragment.myCase

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.*
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.advocatediary.activity.addClient.AddClientActivity
import com.advocatediary.activity.allClient.AllClientActivity
import com.advocatediary.fragment.myCase.myCasesPresenter.MyCasesPresenter
import com.advocatediary.fragment.myCase.myCasesView.MyCasesView
import com.advocatediary.utils.Constants
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import java.util.*
import android.animation.ObjectAnimator
import pl.droidsonroids.gif.GifImageView

class MyCaseFragment : Fragment(), MyCasesView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            fab_add_client -> gotoClientScreen()
            fab_add_case -> gotoAddCaseScreen()
            tv_cancel_search -> {
              //  linear_search.visibility = View.GONE
                slideUp(linear_search)
                var intent = Intent()
                intent.setAction("search_display")
                intent.putExtra("serach_position", 1)
                activity!!.sendBroadcast(intent)
                resetSearchData()
                //   HomeActivity.img_search.visibility = View.GONE
            }
            iv_sound_search -> {
                startVoiceInput()
            }
            iv_cross_search->{
                resetSearchData()

            }
        }
    }

    fun resetSearchData()
    {
        et_search_cases.setText("")
        searchData("")
        iv_cross_search.visibility=View.GONE
        iv_sound_search.visibility=View.VISIBLE
    }


    fun gotoClientScreen() {
        menu_fab.close(true)
        var intent = Intent(activity, AddClientActivity::class.java)
        intent.putExtra("client_id", "")
        startActivity(intent)
        Utils.screenOpenCloseAnimation(activityCurrent)
    }

    fun gotoAddCaseScreen() {
        menu_fab.close(true)
        var addCaseInfoActivity = Intent(activity, AllClientActivity::class.java)
        startActivity(addCaseInfoActivity)
        Utils.screenOpenCloseAnimation(activityCurrent)
    }

    lateinit var activityCurrent: Activity
    lateinit var rv_my_cases: RecyclerView
    lateinit var tv_case_number: TextView

    lateinit var rel_top_internet: RelativeLayout
    lateinit var card_view_my_case: CardView
    private val REQ_CODE_SPEECH_INPUT = 100

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.my_cases_fragment, container, false) as View
        activityCurrent = activity!!

        initUI(view)
        return view
    }

    private lateinit var presenter: MyCasesPresenter

    private lateinit var receiver: BroadcastReceiver
    private lateinit var no_case_found: RelativeLayout
    // private lateinit var fab: FloatingActionButton
    private lateinit var fab_add_client: FloatingActionButton
    private lateinit var fab_add_case: FloatingActionButton
    private lateinit var menu_fab: FloatingActionMenu
    private lateinit var gif_no_data_found: GifImageView

    private var isFabOpen: Boolean = false

    override fun onResume() {
        super.onResume()
        // for set serach image display

        if(linear_search.visibility==View.GONE) {
            var intent = Intent()
            intent.setAction("search_display")
            intent.putExtra("serach_position", 1)
            activity!!.sendBroadcast(intent)
        }
    }

    fun animateFAB() {

        if (isFabOpen) {

            //  fab.startAnimation(rotate_backward)
            fab_add_client.startAnimation(fab_close)
            fab_add_case.startAnimation(fab_close)
            fab_add_client.setClickable(false)
            fab_add_case.setClickable(false)
            isFabOpen = false
            //  Log.d("Raj", "close")

        } else {

            // fab.startAnimation(rotate_forward)
            fab_add_client.startAnimation(fab_open)
            fab_add_case.startAnimation(fab_open)
            fab_add_client.setClickable(true)
            fab_add_case.setClickable(true)
            isFabOpen = true
            //  Log.d("Raj", "open")

        }
    }


    private var fab_open: Animation? = null

    private var fab_close: Animation? = null

    private var rotate_forward: Animation? = null

    private var rotate_backward: Animation? = null
    private lateinit var iv_cross_search: ImageView
    private lateinit var iv_sound_search: ImageView
    private lateinit var et_search_cases: EditText

    private lateinit var linear_search: LinearLayout
    private lateinit var tv_cancel_search: TextView
    private lateinit var iv_gif_no_internet: GifImageView
    private lateinit var linear_top_mycase: LinearLayout

    private fun initUI(view: View) {

        linear_top_mycase=view.findViewById<LinearLayout>(R.id.linear_top_mycase)

        et_search_cases = view.findViewById<EditText>(R.id.et_search_cases)
        iv_sound_search = view.findViewById<ImageView>(R.id.iv_sound_search)
        iv_cross_search = view.findViewById<ImageView>(R.id.iv_cross_search)

        iv_sound_search.setOnClickListener(this)
        iv_cross_search.setOnClickListener(this)

        gif_no_data_found = view!!.findViewById<GifImageView>(R.id.gif_no_data_found)
        gif_no_data_found.setImageResource(R.raw.no_data_found_gif)

        et_search_cases.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_search_cases.text.toString().trim().length > 0) {
                    iv_sound_search.visibility = View.GONE
                    iv_cross_search.visibility = View.VISIBLE
                } else {
                    iv_sound_search.visibility = View.VISIBLE
                    iv_cross_search.visibility = View.GONE
                }
                searchData(et_search_cases.text.toString().trim())
            }

        })

        iv_gif_no_internet = view!!.findViewById<GifImageView>(R.id.iv_gif_no_internet)
        iv_gif_no_internet.setImageResource(R.raw.no_internet_connection)

        linear_search = view.findViewById<LinearLayout>(R.id.linear_search)

        menu_fab = view.findViewById<FloatingActionMenu>(R.id.menu_fab)

        //  fab = view.findViewById(R.id.fab) as FloatingActionButton
        fab_add_client = view.findViewById(R.id.fab_add_client) as FloatingActionButton
        fab_add_case = view.findViewById(R.id.fab_add_case) as FloatingActionButton
        fab_open = AnimationUtils.loadAnimation(activityCurrent, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(activityCurrent, R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(activityCurrent, R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(activityCurrent, R.anim.rotate_backward);
        //fab.setOnClickListener(this);
        fab_add_client.setOnClickListener(this);
        fab_add_case.setOnClickListener(this);

        rv_my_cases = view.findViewById(R.id.rv_my_cases) as RecyclerView
        tv_case_number = view.findViewById(R.id.tv_case_number) as TextView
        rel_top_internet = view.findViewById(R.id.rel_top_internet) as RelativeLayout
        card_view_my_case = view.findViewById(R.id.card_view_my_case) as CardView
        no_case_found = view.findViewById(R.id.no_case_found) as RelativeLayout
        tv_cancel_search = view.findViewById(R.id.tv_cancel_search) as TextView

        tv_cancel_search.setOnClickListener(this)

        var refresh_swipe_layout = view.findViewById(R.id.refresh_swipe_layout) as SwipeRefreshLayout

        if (Utils.Factory.isNetworkAvailable(activityCurrent)) {
            linear_top_mycase.visibility=View.GONE
            menu_fab.visibility=View.GONE
            presenter = MyCasesPresenter(activityCurrent, this, rv_my_cases, tv_case_number, "", no_case_found,
                menu_fab,linear_top_mycase)
            presenter.getAllMyCases("")
            rel_top_internet.visibility = View.GONE
            card_view_my_case.visibility = View.VISIBLE

        } else {
            //  Toast.makeText(activityCurrent, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
            rel_top_internet.visibility = View.VISIBLE
            card_view_my_case.visibility = View.GONE
            linear_top_mycase.visibility=View.GONE
            menu_fab.visibility=View.GONE
        }

        rv_my_cases = view.findViewById(R.id.rv_my_cases)

        refresh_swipe_layout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refresh_swipe_layout.isRefreshing = false
                presenter.setRefreshData("")
            }
        })

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

            //    linear_search.visibility = View.VISIBLE

                slideDown(linear_search)

                /*    var search_value = ""
                    try {
                        search_value = intent!!.getStringExtra("search_value")
                        //   presenter.startgetMyClient(search_value, "update")
                    } catch (e: Exception) {
                        search_value = ""
                    }

                    presenter = MyCasesPresenter(
                        activityCurrent,
                        this@MyCaseFragment,
                        rv_my_cases,
                        tv_case_number,
                        search_value,
                        no_case_found
                    )

                    presenter.setSearchData("update", search_value)*/
            }
        }

        activityCurrent.registerReceiver(receiver, object : IntentFilter(Constants.MY_CASE_UPDATE) {

        })
    }


    fun searchData(searchValue: String) {
        var search_value = ""
        try {
            search_value = searchValue
            //   presenter.startgetMyClient(search_value, "update")
        } catch (e: Exception) {
            search_value = ""
        }

        presenter = MyCasesPresenter(
            activityCurrent,
            this@MyCaseFragment,
            rv_my_cases,
            tv_case_number,
            search_value,
            no_case_found
        ,menu_fab,linear_top_mycase)

        presenter.setSearchData("update", search_value)
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

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?")
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    et_search_cases.setText(result[0])
                    et_search_cases.setSelection(et_search_cases.text.toString().trim().length)

                    if (et_search_cases.text.toString().trim().length > 0) {
                        iv_sound_search.visibility = View.GONE
                        iv_cross_search.visibility = View.VISIBLE
                    }

                   /* presenter = MyCasesPresenter(
                        activityCurrent,
                        this@MyCaseFragment,
                        rv_my_cases,
                        tv_case_number,
                        result[0],
                        no_case_found
                    )*/
                    presenter.setSearchData("update", result[0])
                }
            }
        }
    }


    fun slideUp(llDomestic: View) {

        val animation = ObjectAnimator.ofFloat(view, "translationY", 0f)
        animation.duration = 1000
        llDomestic.visibility = View.GONE
        animation.start()

    }

    // slide the view from below itself to the current position
    fun slideDown(llDomestic: View) {
        llDomestic.visibility = View.VISIBLE
        val animation = ObjectAnimator.ofFloat(view, "translationY", 0f)
        animation.duration = 1000
        animation.start()
    }
}