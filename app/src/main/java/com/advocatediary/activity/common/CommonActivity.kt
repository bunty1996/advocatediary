package com.advocatediary.activity.common

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast
import com.advocatediary.activity.addCaseInfo.AddCaseInfoActivity
import com.advocatediary.activity.addClient.AddClientActivity
import com.advocatediary.activity.common.commonPresenter.CommonPresenter
import com.advocatediary.activity.common.commonView.CommonView
import com.advocatediary.fragment.aboutUs.AboutUsFragment
import com.advocatediary.fragment.caseDetail.CaseDetailFragment
import com.advocatediary.fragment.clientDetail.ClientDetailFragment
import com.advocatediary.fragment.privacyPolicy.PrivacyPolicyFragment
import com.advocatediary.utils.Utils
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.content_common.*
import com.andexert.library.RippleView

class CommonActivity : AppCompatActivity(), View.OnClickListener, CommonView {
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
            //iv_back_common1 -> finish()
            tv_edit_data -> gotoEditScreen()
          // linear_right_side_data -> callShowMore()
            tv_delete_data -> deleteClientCaseMethod()
            frame_shadow -> hideAllpopup()
     //       more_overflow -> callShowMore()
        }
    }

    fun hideAllpopup() {
        linear_popup_data.visibility = View.GONE
        frame_shadow.visibility = View.GONE
    }

    fun deleteClientCaseMethod() {
        if (!(client_id.equals("")) && !(client_id.equals(null))) {
            deleteClient()
        } else
            if (!(case_id.equals(""))) {
                deleteCaseMethod()
            }
    }

    fun callShowMore() {
        if (linear_popup_data.isShown) {
            linear_popup_data.visibility = View.GONE
            frame_shadow.visibility = View.GONE
        } else {
            linear_popup_data.visibility = View.VISIBLE
            frame_shadow.visibility = View.VISIBLE
        }
    }

    fun gotoEditScreen() {
        linear_popup_data.visibility = View.GONE
        frame_shadow.visibility = View.GONE

        if (screen_type_.equals("client_detail")) {
            // For Client Edit data
            var intent = Intent(activity, AddClientActivity::class.java)
            intent.putExtra("client_id", client_id)
            startActivity(intent)
            Utils.screenOpenCloseAnimation(activity)
        } else if (screen_type_.equals("case_detail")) {
            // For case Edit data
            var intent = Intent(activity, AddCaseInfoActivity::class.java)
            intent.putExtra("case_id", case_id)
            startActivity(intent)
            Utils.screenOpenCloseAnimation(activity)

        }
    }

    private lateinit var trans: FragmentTransaction

    private var screen_type_: String? = ""
    private lateinit var activity: Activity
    private var client_id: String? = null

    private lateinit var presenter: CommonPresenter

    private var case_id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        activity = this

        presenter = CommonPresenter(activity, this)


        ripple_effect_common.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                finish()
            }
        })

        iv_back_common1.setOnClickListener(this)
        tv_edit_data.setOnClickListener(this)
        linear_right_side_data.setOnClickListener(this)
        tv_delete_data.setOnClickListener(this)

        frame_shadow.setOnClickListener(this)
      //  more_overflow.setOnClickListener(this)

        linear_popup_data.visibility = View.GONE
        frame_shadow.visibility = View.GONE

        more_overflow.setOnRippleCompleteListener(RippleView.OnRippleCompleteListener {
            callShowMore()
        })


        frame_layout_common.setBackgroundColor(Color.parseColor("#00000000"))

        screen_type_ = intent.getStringExtra("screen_type_")

        if (screen_type_.equals("client_detail")) {
            linear_right_side_data.visibility = View.VISIBLE
            tv_common_title11.text = "Client Detail"
            client_id = intent.getStringExtra("client_id")

            var clientDetailFragment = ClientDetailFragment()
            var bundle = Bundle()
            bundle.putString("client_id", client_id)
            trans = supportFragmentManager.beginTransaction()
            clientDetailFragment.arguments = bundle
            trans.replace(R.id.frame_layout_common, clientDetailFragment)
            trans.commit()
        } else
            if (screen_type_.equals("about_us")) {
                linear_right_side_data.visibility = View.INVISIBLE
                tv_common_title11.text = "About Us"
                var aboutUsFragment = AboutUsFragment()
                trans = supportFragmentManager.beginTransaction()
                trans.replace(R.id.frame_layout_common, aboutUsFragment)
                trans.commit()
            } else
                if (screen_type_.equals("privacy_policy")) {
                    linear_right_side_data.visibility = View.INVISIBLE
                    tv_common_title11.text = "Privacy Policy"
                    var privacyPolicyFragment = PrivacyPolicyFragment()
                    trans = supportFragmentManager.beginTransaction()
                    trans.replace(R.id.frame_layout_common, privacyPolicyFragment)
                    trans.commit()
                } else
                    if (screen_type_.equals("case_detail")) {
                        linear_right_side_data.visibility = View.VISIBLE
                        tv_common_title11.text = "Case Detail"

                        case_id = intent.getStringExtra("case_id")
                        var bundle = Bundle()
                        bundle.putString("case_id", case_id)
                        var caseDetailFragment = CaseDetailFragment()
                        caseDetailFragment.arguments = bundle
                        trans = supportFragmentManager.beginTransaction()
                        trans.replace(R.id.frame_layout_common, caseDetailFragment)
                        trans.commit()
                    }
    }


    private lateinit var alertDialog: AlertDialog

    fun deleteClient() {
        linear_popup_data.visibility = View.GONE
        var alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setMessage("Do you want to delete this client?")
        alertDialogBuilder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (Utils.isNetworkAvailable(activity)) {
                    alertDialog.dismiss()
                    linear_popup_data.visibility = View.GONE
                    frame_shadow.visibility = View.GONE
                    presenter.deleteClient(client_id!!)
                } else {
                    Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
                }
            }
        })
        alertDialogBuilder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                linear_popup_data.visibility = View.GONE
                frame_shadow.visibility = View.GONE
                alertDialog!!.dismiss()
            }
        })

        alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    fun deleteCaseMethod() {
        linear_popup_data.visibility = View.GONE
        var alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setMessage("Do you want to delete this case?")
        alertDialogBuilder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (Utils.isNetworkAvailable(activity)) {
                    alertDialog.dismiss()
                    linear_popup_data.visibility = View.GONE
                    frame_shadow.visibility = View.GONE
                    presenter.deleteCase(case_id!!)
                } else {
                    Toast.makeText(activity, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
                }
            }
        })
        alertDialogBuilder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                linear_popup_data.visibility = View.GONE
                frame_shadow.visibility = View.GONE
                alertDialog!!.dismiss()
            }
        })

        alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
