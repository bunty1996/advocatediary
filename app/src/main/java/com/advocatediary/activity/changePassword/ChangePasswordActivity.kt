package com.advocatediary.activity.changePassword

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import com.advocatediary.activity.changePassword.changePasswordPresenter.ChangePasswordPresenter
import com.advocatediary.activity.changePassword.changeView.ChangeView
import com.advocatediary.utils.Utils
import com.andexert.library.RippleView
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_change_password.*

class ChangePasswordActivity : AppCompatActivity(), View.OnClickListener, ChangeView {
    override fun showDialog(activity: Activity) {
        Utils.showDialog(activity)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        when (v) {
            //      back22 -> finish()
            bt_change_password -> submitPassword()
            bt_change_password11 -> submitPassword()
            linear_title -> Utils.hideSoftKeyboard(activity)
            rel_text_data -> Utils.hideSoftKeyboard(activity)
            rel_change_top -> Utils.hideSoftKeyboard(activity)
            linear_change_dataa -> Utils.hideSoftKeyboard(activity)
        }
    }

    fun submitPassword() {
        if (Utils.Factory.isNetworkAvailable(activity)) {
            presenter.changePasswordCall(
                et_old_password, et_new_password, et_confirm_password
            )
        } else {
            Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    lateinit var activity: Activity
    lateinit var presenter: ChangePasswordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        initUI()
    }

    private fun initUI() {
        activity = this

        presenter = ChangePasswordPresenter(activity, this)

        ripple_effect_change_password.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                finish()
            }
        })

        //back22.setOnClickListener(this)
        bt_change_password.setOnClickListener(this)
        bt_change_password11.setOnClickListener(this)
        linear_title.setOnClickListener(this)
        rel_text_data.setOnClickListener(this)
        rel_change_top.setOnClickListener(this)
        linear_change_dataa.setOnClickListener(this)

        rel_top_change.getViewTreeObserver().addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            rel_top_change.getWindowVisibleDisplayFrame(r)

            val screenHeight = rel_top_change.getRootView().getHeight()
            Log.e("screenHeight", screenHeight.toString())
            val heightDiff = screenHeight - (r.bottom - r.top)
            Log.e("heightDiff", heightDiff.toString())
            val visible = heightDiff > screenHeight / 3
            Log.e("visible", visible.toString())
            if (visible) {
                bt_change_password11.visibility = View.VISIBLE
                bt_change_password.visibility = View.GONE
            } else {
                bt_change_password11.visibility = View.GONE
                bt_change_password.visibility = View.VISIBLE
            }
        });
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
