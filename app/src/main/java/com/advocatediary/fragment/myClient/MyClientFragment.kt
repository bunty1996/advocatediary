package com.advocatediary.fragment.myClient

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.*
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.advocatediary.activity.addClient.AddClientActivity
import com.advocatediary.activity.allClient.AllClientActivity
import com.advocatediary.activity.home.HomeActivity
import com.advocatediary.adapter.RVMyClientAdapter
import com.advocatediary.fragment.myClient.myClientPresenter.MyClientPresenter
import com.advocatediary.fragment.myClient.myClientView.MyClientView
import com.advocatediary.utils.Constants
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import kotlinx.android.synthetic.main.content_change_password.*
import pl.droidsonroids.gif.GifImageView
import java.util.*

class MyClientFragment : Fragment(), MyClientView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {

            fab_add_client_client -> gotoClientScreen()
            fab_add_case_client -> gotoAddCaseScreen()
            iv_sound_search->{
                startVoiceInput()
            }
            iv_cross_search->{
                resetSearchData()
            }
            tv_cancel_search->{
                slideUp(linear_search)
                var intent2 = Intent()
                intent2.setAction("search_display")
                intent2.putExtra("serach_position", 3)
                activity!!.sendBroadcast(intent2)
                resetSearchData()
            }
        }
    }

    fun resetSearchData()
    {
        et_search_cases.setText("")
        searchValue("")
        iv_cross_search.visibility=View.GONE
        iv_sound_search.visibility=View.VISIBLE
    }

    fun gotoClientScreen() {
        menu_fab_client.close(true)
        var intent = Intent(activity, AddClientActivity::class.java)
        intent.putExtra("client_id", "")
        startActivity(intent)
        Utils.screenOpenCloseAnimation(clientActivity)
    }

    fun gotoAddCaseScreen() {
        menu_fab_client.close(true)
        var addCaseInfoActivity = Intent(activity, AllClientActivity::class.java)
        startActivity(addCaseInfoActivity)
        Utils.screenOpenCloseAnimation(clientActivity)
    }

    lateinit var clientActivity: Activity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.my_client_fragment, container, false)
        clientActivity = this.activity!!
        initUI(view)
        return view
    }

    private lateinit var presenter: MyClientPresenter

    private lateinit var receiver: BroadcastReceiver
    private lateinit var menu_fab_client: FloatingActionMenu
    private lateinit var fab_add_client_client: FloatingActionButton
    private lateinit var fab_add_case_client: FloatingActionButton
    private lateinit var linear_search: LinearLayout
    private lateinit var et_search_cases: EditText
    private lateinit var iv_sound_search: ImageView
    private lateinit var iv_cross_search: ImageView
    private lateinit var tv_cancel_search: TextView

    private lateinit var iv_gif_no_internet: GifImageView
    private lateinit var gif_no_data_found: GifImageView
    private lateinit var card_view_clientss: CardView


    private val REQ_CODE_SPEECH_INPUT = 100
    override fun onResume() {
        super.onResume()
        // for set serach image display
        if (linear_search.visibility == View.GONE) {
            var intent2 = Intent()
            intent2.setAction("search_display")
            intent2.putExtra("serach_position", 3)
            activity!!.sendBroadcast(intent2)
        }
    }

    private fun initUI(view: View?) {
        iv_gif_no_internet = view!!.findViewById<GifImageView>(R.id.iv_gif_no_internet)
        iv_gif_no_internet.setImageResource(R.raw.no_internet_connection)


        gif_no_data_found = view!!.findViewById<GifImageView>(R.id.gif_no_data_found)
        gif_no_data_found.setImageResource(R.raw.no_data_found_gif)

        linear_search = view!!.findViewById<LinearLayout>(R.id.linear_search)
        et_search_cases = view!!.findViewById<EditText>(R.id.et_search_cases)
        iv_sound_search = view!!.findViewById<ImageView>(R.id.iv_sound_search)
        iv_cross_search = view!!.findViewById<ImageView>(R.id.iv_cross_search)
        tv_cancel_search = view!!.findViewById<TextView>(R.id.tv_cancel_search)
        card_view_clientss = view!!.findViewById<CardView>(R.id.card_view_clientss)

        iv_sound_search.setOnClickListener(this)
        iv_cross_search.setOnClickListener(this)
        tv_cancel_search.setOnClickListener(this)

        menu_fab_client = view!!.findViewById<FloatingActionMenu>(R.id.menu_fab_client)
        fab_add_client_client = view!!.findViewById<FloatingActionButton>(R.id.fab_add_client_client)
        fab_add_case_client = view!!.findViewById<FloatingActionButton>(R.id.fab_add_case_client)

        fab_add_client_client.setOnClickListener(this)
        fab_add_case_client.setOnClickListener(this)

        var rv_my_clients = view!!.findViewById(R.id.rv_my_clients) as RecyclerView
        var refresh_swipe_layout = view!!.findViewById(R.id.refresh_swipe_layout) as SwipeRefreshLayout

        var rel_top_internet = view.findViewById(R.id.rel_top_internet) as RelativeLayout
        var card_view_my_client = view.findViewById(R.id.card_view_my_client) as CardView
        var linear_all_my_client = view.findViewById(R.id.linear_all_my_client) as LinearLayout



        var no_client_found = view.findViewById(R.id.no_client_found) as RelativeLayout
        var listArray = ArrayList<String>()
        listArray.add("First")
        listArray.add("Second")
        listArray.add("Third")
        listArray.add("Fourth")
        listArray.add("Fifth")
        listArray.add("Sixth")
        listArray.add("Seventh")

        var tv_case_number = view.findViewById(R.id.tv_case_number) as TextView

        if (Utils.Factory.isNetworkAvailable(clientActivity)) {
            linear_all_my_client.visibility = View.GONE
            menu_fab_client.visibility = View.GONE

            presenter = MyClientPresenter(clientActivity, this, rv_my_clients, tv_case_number, "", no_client_found,card_view_clientss,
                linear_all_my_client,menu_fab_client)
            presenter.getAllMyClient("")
            rel_top_internet.visibility = View.GONE
            card_view_my_client.visibility = View.VISIBLE


        } else {
            rel_top_internet.visibility = View.VISIBLE
            card_view_my_client.visibility = View.GONE
            linear_all_my_client.visibility = View.GONE
            menu_fab_client.visibility = View.GONE

        }

        refresh_swipe_layout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refresh_swipe_layout.isRefreshing = false
                presenter.refreshData("")
            }
        })

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

                slideDown(linear_search)
            }
        }
        activity!!.registerReceiver(receiver, object : IntentFilter(Constants.MY_CLIENT_UPDATE) {})

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
                searchValue(et_search_cases.text.toString().trim())
            }

        })


    }

    fun searchValue(searchValue:String)
    {
        var search_value = ""
        try {
            search_value = searchValue
            //   presenter.startgetMyClient(search_value, "update")
        } catch (e: Exception) {
            search_value = ""
        }

        presenter.startgetMyClient(search_value, "update")
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
                if (resultCode == Activity.RESULT_OK && null != data) {
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
                  //  presenter.setSearchData("update", result[0])

                    presenter.startgetMyClient(result[0], "update")
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