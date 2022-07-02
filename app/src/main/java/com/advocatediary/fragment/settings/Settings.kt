package com.advocatediary.fragment.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.advocatediary.activity.changePassword.ChangePasswordActivity
import com.advocatediary.activity.common.CommonActivity
import com.advocatediary.activity.help.HelpActivity
import com.advocatediary.activity.home.HomeActivity
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.setting_layout.*
import android.content.pm.PackageManager
import android.R.attr.versionName
import android.content.pm.PackageInfo
import android.widget.TextView
import com.advocatediary.utils.Utils


class Settings : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            linear_change -> gotoChangePassword()
            linear_help -> gotoHelp()
            linear_about -> gotoAboutUs()
            linear_privacy -> goToPrivacyPolicy()
        }
    }

    lateinit var settingActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.setting_layout, container, false)
        settingActivity = this!!.activity!!
        initUI(view)
        return view
    }

    private lateinit var linear_change: LinearLayout
    private lateinit var linear_help: LinearLayout
    private lateinit var linear_about: LinearLayout
    private lateinit var linear_privacy: LinearLayout
    private lateinit var tv_version_number: TextView


    override fun onResume() {
        super.onResume()
        // for set serach image display
        var intent = Intent()
        intent.setAction("search_display")
        intent.putExtra("serach_position", 5)
        activity!!.sendBroadcast(intent)

    }

    private fun initUI(view: View?) {

        //   HomeActivity.img_search.visibility=View.INVISIBLE
        linear_change = view!!.findViewById(R.id.linear_change) as LinearLayout
        linear_change.setOnClickListener(this)

        linear_help = view!!.findViewById(R.id.linear_help) as LinearLayout
        linear_help.setOnClickListener(this)

        linear_about = view!!.findViewById(R.id.linear_about) as LinearLayout
        linear_about.setOnClickListener(this)

        linear_privacy = view!!.findViewById(R.id.linear_privacy) as LinearLayout
        linear_privacy.setOnClickListener(this)

        tv_version_number = view!!.findViewById(R.id.tv_version_number) as TextView

        try {
            val pInfo = settingActivity.getPackageManager().getPackageInfo(settingActivity.getPackageName(), 0)
            val version = pInfo.versionName
            tv_version_number.text = "Version: " + version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    fun gotoChangePassword() {
        var chnagePasswordIntent = Intent(settingActivity, ChangePasswordActivity::class.java)
        settingActivity.startActivity(chnagePasswordIntent)
        Utils.screenOpenCloseAnimation(settingActivity)
    }

    fun gotoHelp() {
        var helpIntent = Intent(settingActivity, HelpActivity::class.java)
        startActivity(helpIntent)
        Utils.screenOpenCloseAnimation(settingActivity)
    }

    fun gotoAboutUs() {
        var intent = Intent(settingActivity, CommonActivity::class.java)
        intent.putExtra("screen_type_", "about_us")
        startActivity(intent)
        Utils.screenOpenCloseAnimation(settingActivity)
    }

    fun goToPrivacyPolicy() {
        var intent = Intent(settingActivity, CommonActivity::class.java)
        intent.putExtra("screen_type_", "privacy_policy")
        startActivity(intent)
        Utils.screenOpenCloseAnimation(settingActivity)
    }
}