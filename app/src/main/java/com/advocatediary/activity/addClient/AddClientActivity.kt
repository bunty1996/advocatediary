package com.advocatediary.activity.addClient

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.advocatediary.activity.addClient.addClientPresenter.AddClientPresenter
import com.advocatediary.activity.addClient.addClientView.AddClientView
import com.advocatediary.model.clientDeatail.ClientDetailData
import com.advocatediary.utils.Utils
import com.andexert.library.RippleView
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_add_client.*
import kotlinx.android.synthetic.main.content_forgot_password.*
import java.lang.Exception

class AddClientActivity : AppCompatActivity(), View.OnClickListener, AddClientView {


    private lateinit var activity: Activity
    override fun onClick(v: View?) {
        when (v) {
            //  back_client_add -> finish()
            bt_next_case -> submit()
            bt_next_case11 -> submit()
            tv_next_client -> submit()
            linear_status_bar -> Utils.hideSoftKeyboard(activity)
            linear_add_Clientt -> Utils.hideSoftKeyboard(activity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_client)
        activity = this;
        initUI()
    }

    private lateinit var presenter: AddClientPresenter

    private var client_id: String? = null

    private lateinit var et_name_client: EditText
    private lateinit var receiver: BroadcastReceiver

    private fun initUI() {
        et_name_client = findViewById(R.id.et_name_client) as EditText
        client_id = intent.getStringExtra("client_id")

        ripple_effect_add_client.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                finish()
            }
        })

        //  back_client_add.setOnClickListener(this)
        bt_next_case.setOnClickListener(this)
        bt_next_case11.setOnClickListener(this)
        tv_next_client.setOnClickListener(this)
        linear_status_bar.setOnClickListener(this)
        linear_add_Clientt.setOnClickListener(this)

        editTextListener()

        presenter = AddClientPresenter(activity, this, scrollview_add_client)

        rel_top_add_client.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            rel_top_add_client.getWindowVisibleDisplayFrame(r)

            val screenHeight = rel_top_add_client.getRootView().getHeight()
            Log.e("screenHeight", screenHeight.toString())
            val heightDiff = screenHeight - (r.bottom - r.top)
            Log.e("heightDiff", heightDiff.toString())
            val visible = heightDiff > screenHeight / 3
            Log.e("visible", visible.toString())
            if (visible) {
                bt_next_case11.visibility = View.VISIBLE
                bt_next_case.visibility = View.GONE
            } else {
                bt_next_case11.visibility = View.GONE
                bt_next_case.visibility = View.VISIBLE
            }
        });


        if (!(client_id.equals(""))) {
            presenter.getClientDetail(client_id!!)
        } else {
            scrollview_add_client.visibility = View.VISIBLE
        }


        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                try {
                    finish()
                } catch (e: Exception) {

                }
            }
        }

        registerReceiver(receiver, object : IntentFilter("case_update") {})
    }

    private fun editTextListener() {
        et_name_client.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_name_client.text.toString().trim().length == 0) {
                    tv_client_full_name.visibility = View.VISIBLE
                } else {
                    tv_client_full_name.visibility = View.VISIBLE
                }
            }
        })

        et_phone_client.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_phone_client.text.toString().trim().length == 0) {
                    tv_client_phone.visibility = View.VISIBLE
                } else {
                    tv_client_phone.visibility = View.VISIBLE
                }
            }
        })

        et_email_client.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_email_client.text.toString().trim().length == 0) {
                    tv_client_email.visibility = View.VISIBLE
                } else {
                    tv_client_email.visibility = View.VISIBLE
                }
            }
        })

        et_address_client.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_address_client.text.toString().trim().length == 0) {
                    tv_client_place.visibility = View.VISIBLE
                } else {
                    tv_client_place.visibility = View.VISIBLE
                }
            }
        })
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

    fun submit() {
        if (Utils.Factory.isNetworkAvailable(activity)) {
            val selectedId = radio_group.checkedRadioButtonId

            // find the radiobutton by returned id
            val radioSexButton = findViewById(selectedId) as RadioButton
            Log.e("wdaD", radioSexButton.text.toString())
            if (client_id.equals("")) {
                presenter.addClientMethod2(
                    et_name_client,
                    et_phone_client,
                    et_email_client,
                    et_address_client,
                    radioSexButton.text.toString().trim()
                )
            } else {
                presenter.editClient(
                    et_name_client,
                    et_phone_client,
                    et_email_client,
                    et_address_client,
                    radioSexButton.text.toString().trim(), client_id!!
                )
            }
        } else {
            Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    override fun setClientData(data: ClientDetailData?) {

        scrollview_add_client.visibility = View.VISIBLE

        et_name_client.setText(data!!.getFullName())

        et_phone_client.setText(data!!.getPhone())
        et_email_client.setText(data.getEmail())
        et_address_client.setText(data.getAddress())
        if ((data!!.getGender().equals("Male")) || (data!!.getGender().equals("male"))) {
            rb_male.isChecked = true
            rb_female.isChecked = false
        } else {
            rb_male.isChecked = false
            rb_female.isChecked = true
        }

        et_name_client.setSelection(et_name_client.text.toString().trim().length)
        et_phone_client.setSelection(et_phone_client.text.toString().trim().length)
        et_email_client.setSelection(et_email_client.text.toString().trim().length)
        et_address_client.setSelection(et_address_client.text.toString().trim().length)


        if (et_name_client.text.toString().trim().length == 0) {
            tv_client_full_name.visibility = View.VISIBLE
        } else {
            tv_client_full_name.visibility = View.VISIBLE
        }

        if (et_phone_client.text.toString().trim().length == 0) {
            tv_client_phone.visibility = View.VISIBLE
        } else {
            tv_client_phone.visibility = View.VISIBLE
        }

        if (et_email_client.text.toString().trim().length == 0) {
            tv_client_email.visibility = View.VISIBLE
        } else {
            tv_client_email.visibility = View.VISIBLE
        }

        if (et_address_client.text.toString().trim().length == 0) {
            tv_client_place.visibility = View.VISIBLE
        } else {
            tv_client_place.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
