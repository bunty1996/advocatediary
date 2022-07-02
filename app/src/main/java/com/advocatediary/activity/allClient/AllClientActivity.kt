package com.advocatediary.activity.allClient

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.*
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.advocatediary.activity.addClient.AddClientActivity
import com.advocatediary.activity.allClient.allClientPresenter.AllClientPresenter
import com.advocatediary.activity.allClient.allClientView.AllClientView
import com.advocatediary.utils.Utils
import com.andexert.library.RippleView
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.case_search_layout.*
import kotlinx.android.synthetic.main.content_all_client.*
import kotlinx.android.synthetic.main.no_internet_connection.*
import pl.droidsonroids.gif.GifImageView
import java.util.*

class AllClientActivity : AppCompatActivity(), AllClientView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            tv_cancel -> showSerachLayout(3)
            bt_add_client -> gotoAddClient()
            iv_sound_search -> {
                startVoiceInput()
            }
            iv_cross_search -> {
                resetSearchData()
            }
            tv_cancel_search -> {
                ripple_search_all_client.visibility = View.VISIBLE
                resetSearchData()
                slideUp(linear_search)
            }
        }
    }

    fun resetSearchData() {
        et_search_cases.setText("")
        searchValueData("")
        iv_cross_search.visibility = View.GONE
        iv_sound_search.visibility = View.VISIBLE
    }

    fun searchValueData(searchValue: String) {
        presenter.getFirstTimeData(et_search_cases, "")
    }

    fun gotoAddClient() {
        var intent = Intent(allClientActivity, AddClientActivity::class.java)
        intent.putExtra("client_id", "")
        startActivity(intent)
        Utils.screenOpenCloseAnimation(allClientActivity)
    }

    fun showSerachLayout(position: Int) {
        // for search
        if (Utils.isNetworkAvailable(allClientActivity)) {
            linear_search_client.visibility = View.GONE
            tv_cancel.visibility = View.GONE

            // for simple layout
            tv_title_add_case.visibility = View.GONE
            ripple_search_all_client.visibility = View.GONE
            et_client_search.clearComposingText()

            if (position == 1) {
                // for simple layout
                tv_title_add_case.visibility = View.VISIBLE
                ripple_search_all_client.visibility = View.VISIBLE

            } else
                if (position == 2) {
                    // for search
                    linear_search_client.visibility = View.VISIBLE
                    tv_cancel.visibility = View.VISIBLE

                } else
                    if (position == 3) {
                        Utils.hideSoftKeyboard(allClientActivity)
                        et_client_search.setText("")
                        tv_title_add_case.visibility = View.VISIBLE
                        ripple_search_all_client.visibility = View.VISIBLE
                        //  presenter.getFirstTimeData(et_client_search)
                    }

        } else {
            Toast.makeText(allClientActivity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    private lateinit var presenter: AllClientPresenter
    private lateinit var allClientActivity: AllClientActivity
    private val REQ_CODE_SPEECH_INPUT = 100
    private lateinit var iv_gif_no_internet: GifImageView
    override fun showDialog(activity: Activity) {
        Utils.showDialog(activity)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity, message: String) {
        Toast.makeText(allClientActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_client)
        allClientActivity = this
        initUI()
    }

    private lateinit var receiver: BroadcastReceiver

    private fun initUI() {

        iv_gif_no_internet = findViewById<GifImageView>(R.id.iv_gif_no_internet)
        iv_gif_no_internet.setImageResource(R.raw.no_internet_connection)

        gif_no_data_found.setImageResource(R.raw.no_data_found_gif)

        if (Utils.isNetworkAvailable(allClientActivity)) {
            rel_top_internet.visibility = View.GONE

            linear_add_all_clientss.visibility = View.GONE
            bt_add_client.visibility = View.GONE

            ripple_search_all_client.visibility = View.VISIBLE
            presenter = AllClientPresenter(
                allClientActivity,
                this,
                rv_my_clients,
                tv_case_number,
                et_client_search,
                no_client_found, card_view_all_client, linear_add_all_clientss, bt_add_client
            )
            presenter.getAllClient("")

            showSerachLayout(1)
        } else {
            rel_top_internet.visibility = View.VISIBLE
            linear_add_all_clientss.visibility = View.GONE
            bt_add_client.visibility = View.GONE
            ripple_search_all_client.visibility = View.INVISIBLE
        }

        //   back_client.setOnClickListener(this)


        ripple_effect_all_client.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                finish()
            }

        })

        ripple_search_all_client.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                linear_search.visibility = View.VISIBLE
                ripple_search_all_client.visibility = View.INVISIBLE
            }
        })

        tv_cancel.setOnClickListener(this)

        iv_sound_search.setOnClickListener(this)
        iv_cross_search.setOnClickListener(this)
        tv_cancel_search.setOnClickListener(this)

        bt_add_client.setOnClickListener(this)
    //    linear_search_layout.setOnClickListener(this)
        et_client_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                presenter.getFirstTimeData(et_client_search, "")

            }

        })


        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // for refresh screens
                //presenter = ClientDetailPresenter(clientDetailActivity, context, rv_client_details, tv_total_cases)
                presenter.getFirstTimeData(et_client_search, "update")
            }
        }

        allClientActivity.registerReceiver(receiver, object : IntentFilter("case_update") {

        })

        refresh_swipe_layout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refresh_swipe_layout.isRefreshing = false
                presenter.getFirstTimeData(et_client_search, "")
            }

        })

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

                presenter.getFirstTimeData(et_search_cases, "")
            }

        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(allClientActivity)
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
                    // presenter.setSearchData("update", result[0])

                    presenter.getFirstTimeData(et_search_cases, "update")
                }
            }
        }
    }


    fun slideUp(llDomestic: View) {
        llDomestic.visibility = View.GONE
        /*  val animation = ObjectAnimator.ofFloat(this, "translationY", 0f)
          animation.duration = 1000
          llDomestic.visibility = View.GONE
          animation.start()*/

    }

    // slide the view from below itself to the current position
    fun slideDown(llDomestic: View) {
        llDomestic.visibility = View.VISIBLE
        /* val animation = ObjectAnimator.ofFloat(view, "translationY", 0f)
         animation.duration = 1000
         animation.start()*/
    }
}
