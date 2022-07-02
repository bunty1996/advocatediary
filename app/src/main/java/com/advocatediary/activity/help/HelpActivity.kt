package com.advocatediary.activity.help

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast
import com.advocatediary.activity.help.helpPresenter.HelpPresenter
import com.advocatediary.activity.help.helpView.HelpView
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_help.*

class HelpActivity : AppCompatActivity(), View.OnClickListener, HelpView {
    override fun showDialog(activity: Activity) {
        Utils.showDialog(activity)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun showMessage(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        activity = this
        initUI();
    }

    private lateinit var presenter: HelpPresenter

    private fun initUI() {
        presenter = HelpPresenter(activity, this)
        back.setOnClickListener(this)
        btn_save.setOnClickListener(this)
        container_toolbar.setOnClickListener(this)
      //  linear_hide.setOnClickListener(this)
        linear_top_hide.setOnClickListener(this)
        search.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            back -> finish()
            btn_save -> submitHelp()
            container_toolbar -> Utils.hideSoftKeyboard(activity)
          //  linear_hide -> Utils.hideSoftKeyboard(activity)
            linear_top_hide -> Utils.hideSoftKeyboard(activity)
            search -> Utils.hideSoftKeyboard(activity)
        }
    }

    fun submitHelp() {
        var queryMessage = et_query.text.toString().trim()
        presenter.hitHelp(et_query)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
