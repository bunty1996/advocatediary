package com.advocatediary.activity.resetPassword

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast
import com.advocatediary.activity.resetPassword.resetPasswordView.ResetPasswordView
import com.advocatediary.activity.resetPassword.resetPaswordPresenter.ResetPasswordPresenter
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_reset_password_22.*

class ResetPasswordActivity : AppCompatActivity(), View.OnClickListener, ResetPasswordView {

    private lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        activity = this
        initUI()
    }

    private var phoneNumber: String? = null

    private lateinit var presenter: ResetPasswordPresenter

    private fun initUI() {
        //   intent.putExtra("phone", phoneNumber)
       phoneNumber = intent.getStringExtra("phone")


        gif_image_reset.setImageResource(R.raw.forgot_password_icon_gif)
        btn_reset_password.setOnClickListener(this)
        linear_reset.setOnClickListener(this)
        presenter = ResetPasswordPresenter(activity, this)

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

    override fun onClick(v: View?) {
        when (v) {
            btn_reset_password -> submitPassword()
            linear_reset -> Utils.hideSoftKeyboard(activity)
        }
    }

    private fun submitPassword() {
        if (Utils.isNetworkAvailable(activity)) {
            presenter.resetPasswordMethod(phoneNumber!!, et_passwordFirst, et_confirm_passwordFirst)
        } else {
            Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }

}
