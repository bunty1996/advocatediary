package com.advocatediary.fragment.clientDetail

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.advocatediary.activity.addCaseInfo.AddCaseInfoActivity
import com.advocatediary.fragment.clientDetail.clientDetailPresenter.ClientDetailPresenter
import com.advocatediary.fragment.clientDetail.clientDetailView.ClientDetailView
import com.advocatediary.model.clientDeatail.ClientDetailData
import com.advocatediary.utils.Constants
import com.advocatediary.utils.Utils
import com.e.advocatediary.R

class ClientDetailFragment : Fragment(), ClientDetailView, View.OnClickListener {
    override fun onClick(v: View?) {

        when (v) {
            img_add_CASE -> gotoAddCaseScreen()
            iv_phone_dial -> checkPermission()
            iv_email_send -> sendEmail()
        }
    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(tv_client_emails.text.toString().trim()))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Advocate Diary")
        startActivity(intent)
    }

    fun gotoAddCaseScreen() {
        var intent = Intent(activity, AddCaseInfoActivity::class.java)
        intent.putExtra("name", data.getFullName()!!.toString())
        intent.putExtra("email", data!!.getEmail().toString())
        intent.putExtra("client_id", client_id)
        intent.putExtra("case_id", "")
        clientDetailActivity.startActivity(intent)
        Utils.screenOpenCloseAnimation(clientDetailActivity)
    }


    private lateinit var data: ClientDetailData

    override fun setClientData(data: ClientDetailData) {
        this.data = data
        tv_client_name.setText(data.getFullName())
        tv_client_emails.setText(data.getEmail())
        tv_client_place.setText(data.getAddress())
        tv_client_gender.setText(data.getGender())
        tv_phone_number.setText(data.getPhone())

        val firstLetter = data.getFullName()?.substring(0, 1)
        tv_text_title.setText(firstLetter?.toUpperCase())

        val gd = GradientDrawable()
        gd.setColor(Color.parseColor(Utils.getColor(firstLetter!!)))
        // (no gradient)
        //gd.setStroke(2, Color.BLACK);
        gd.cornerRadius = 20f
        gd.shape = GradientDrawable.OVAL
        gd.gradientType = GradientDrawable.RADIAL_GRADIENT
        //gd.setGradientRadius(iv.getWidth()/2);
        tv_text_title.setBackground(gd)


        if (!(tv_client_emails.text.toString().trim().equals(""))) {
            linear_client_email_title.visibility = View.VISIBLE
        } else {
            linear_client_email_title.visibility = View.GONE
        }

        if (!(tv_client_place.text.toString().trim().equals(""))) {
            tv_client_place.visibility = View.VISIBLE
        } else {
            tv_client_place.visibility = View.GONE
        }

        linear_client_detail_title.visibility = View.VISIBLE
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

    private lateinit var clientDetailActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.client_detail, container, false)
        clientDetailActivity = activity!!
        initUI(view)
        return view
    }

    private lateinit var presenter: ClientDetailPresenter

    private lateinit var tv_client_name: TextView
    private lateinit var tv_client_emails: TextView
    private lateinit var tv_client_place: TextView
    private lateinit var tv_client_gender: TextView
    private lateinit var tv_phone_number: TextView
    private lateinit var tv_total_cases: TextView
    private lateinit var tv_text_title: TextView

    private lateinit var rv_client_details: RecyclerView
    private lateinit var linear_client_email_title: LinearLayout
    private lateinit var linear_client_detail_title: LinearLayout
    private lateinit var img_add_CASE: ImageView

    private lateinit var client_id: String
    private lateinit var iv_phone_dial: ImageView
    private lateinit var iv_email_send: ImageView

    private lateinit var receiver: BroadcastReceiver
    private lateinit var rel_no_internet_connection: RelativeLayout

    private fun initUI(view: View?) {
        tv_client_name = view!!.findViewById<TextView>(R.id.tv_client_name)
        tv_client_emails = view!!.findViewById<TextView>(R.id.tv_client_emails)
        tv_client_place = view!!.findViewById<TextView>(R.id.tv_client_place)
        tv_client_gender = view!!.findViewById<TextView>(R.id.tv_client_gender)
        tv_phone_number = view!!.findViewById<TextView>(R.id.tv_phone_number)
        tv_total_cases = view!!.findViewById<TextView>(R.id.tv_total_cases)
        tv_text_title = view!!.findViewById<TextView>(R.id.tv_text_title)

        linear_client_email_title = view!!.findViewById<LinearLayout>(R.id.linear_client_email_title)

        iv_phone_dial = view!!.findViewById<ImageView>(R.id.iv_phone_dial)
        iv_email_send = view!!.findViewById<ImageView>(R.id.iv_email_send)
        rv_client_details = view!!.findViewById<RecyclerView>(R.id.rv_client_details)

        linear_client_detail_title = view!!.findViewById<LinearLayout>(R.id.linear_client_detail_title)
        rel_no_internet_connection = view!!.findViewById<RelativeLayout>(R.id.rel_no_internet_connection)

        img_add_CASE = view!!.findViewById<ImageView>(R.id.img_add_CASE)
        client_id = arguments!!.getString("client_id")

        if (Utils.isNetworkAvailable(clientDetailActivity)) {
            presenter = ClientDetailPresenter(clientDetailActivity, this, rv_client_details, tv_total_cases)
            presenter.getClicentDetail(client_id, "")

            rel_no_internet_connection.visibility = View.GONE
        } else {
            rel_no_internet_connection.visibility = View.VISIBLE
        }

        img_add_CASE.setOnClickListener(this)
        iv_phone_dial.setOnClickListener(this)
        iv_email_send.setOnClickListener(this)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // for refresh screens
                //presenter = ClientDetailPresenter(clientDetailActivity, context, rv_client_details, tv_total_cases)
                presenter.resetAllData(client_id, "update")
            }
        }

        clientDetailActivity.registerReceiver(receiver, object : IntentFilter(Constants.CLIENT_DETAIL_UPDATE) {

        })
    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                clientDetailActivity,
                Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?

            Log.e("ffeas", "efd3eds")
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(
                clientDetailActivity,
                arrayOf(Manifest.permission.CALL_PHONE),
                42
            )


        } else {
            // Permission has already been granted
            callPhone()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "" + tv_phone_number.text.toString().trim()))
        startActivity(intent)
    }

}