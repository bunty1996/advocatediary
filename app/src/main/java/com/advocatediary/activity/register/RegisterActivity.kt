package com.advocatediary.activity.register

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.advocatediary.activity.phoneVerification.PhoneVerificationActivity
import com.advocatediary.activity.register.registerPresenter.RegisterPresenter
import com.advocatediary.activity.register.registerView.RegisterView
import com.advocatediary.adapter.RVStateRegisterAdapter
import com.advocatediary.model.state.StateDatum
import com.advocatediary.model.state.StateSelectedData
import com.advocatediary.utils.Constants
import com.advocatediary.utils.Utils
import com.bumptech.glide.Glide
import com.e.advocatediary.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.content_register.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class RegisterActivity : AppCompatActivity(), View.OnClickListener, RegisterView {

    private lateinit var stateList: List<StateDatum>

    override fun setStateList(stateList: List<StateDatum>?) {
        this.stateList = stateList!!;
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

    lateinit var activity: Activity
    lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        activity = this;
        initUI()
    }

    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private fun initUI() {

        // Glide.with(this).load(Constants.Factory.FLAG_URL + "in.png").into(login_image_register)
        tv_code_register.text = "+91"

        imgBack.setOnClickListener(this)
        tv_state.setOnClickListener(this)
        iv_state_arrow.setOnClickListener(this)
        tv_district.setOnClickListener(this)
        iv_district_arrow.setOnClickListener(this)
        //  linear_register_screen.setOnClickListener(this)
        linear_check_text.setOnClickListener(this)

        presenter = RegisterPresenter(activity, this)

        //  editTextListener()

        rel_register.setOnClickListener(this)
        register.setOnClickListener(this)
        // for get all states and districts
        presenter.getStateList()

        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // verification completed
                showSnackbar("onVerificationCompleted:" + credential)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked if an invalid request for verification is made,
                // for instance if the the phone number format is invalid.
                //showSnackbar("onVerificationFailed")

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request

                    showSnackbar("Invalid phone number.")
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    showSnackbar("Quota exceeded.")
                }

            }

            override fun onCodeSent(
                verificationId: String?,
                token: PhoneAuthProvider.ForceResendingToken?
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                showSnackbar("onCodeSent:" + verificationId)
                // Save verification ID and resending token so we can use them later
                var mVerificationId = verificationId
                var mResendToken = token

                var intent = Intent(activity, PhoneVerificationActivity::class.java)
                intent.putExtra("verificationId", verificationId)
                intent.putExtra("phone", tv_code_register.text.toString().trim() + "" + tv_phone.text.toString().trim())


                var stateId: String = ""
                var districtId: String = ""
                var stateIdx: Int = 0
                for ((idx, value) in stateList.withIndex()) {
                    if (tv_state.text.toString().trim().equals(value.getTitle())) {
                        stateId = value.getId()!!
                        stateIdx = idx;
                        break;
                    }
                }


                for ((idx2, value) in stateList.get(stateIdx).getDistricts()!!.withIndex()) {
                    if (tv_district.text.toString().trim().equals(value.getTitle())) {
                        districtId = value.getId()!!

                        break;
                    }
                }


                var jsonObject = JSONObject()

                jsonObject.put("user_name", tv_user_name.text.toString().trim())
                jsonObject.put("password", et_passwordFirst.text.toString().trim())
                jsonObject.put("screen_type", "register")

                jsonObject.put("email", et_emailFirst.text.toString().trim())
                jsonObject.put("state", stateId)
                jsonObject.put("district", districtId)
                intent.putExtra("reg_data", jsonObject.toString())
                startActivity(intent)
                Utils.screenOpenCloseAnimation(activity)
            }

            override fun onCodeAutoRetrievalTimeOut(verificationId: String?) {
                // called when the timeout duration has passed without triggering onVerificationCompleted
                super.onCodeAutoRetrievalTimeOut(verificationId)
            }
        }

        // showStateDialog(tv_state, stateList, "state", tv_state.text.toString().trim(), "")
    }

    private fun editTextListener() {

        tv_user_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (tv_user_name.text.toString().trim().length == 0) {
                    tv_reg_name_title.visibility = View.GONE
                } else {
                    tv_reg_name_title.visibility = View.VISIBLE
                }
            }
        })
        tv_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (tv_phone.text.toString().trim().length == 0) {
                    tv_reg_phone_title.visibility = View.GONE
                } else {
                    tv_reg_phone_title.visibility = View.VISIBLE
                }
            }
        })


        et_passwordFirst.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_passwordFirst.text.toString().trim().length == 0) {
                    tv_reg_password_title.visibility = View.GONE
                } else {
                    tv_reg_password_title.visibility = View.VISIBLE
                }
            }
        })


        et_emailFirst.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_emailFirst.text.toString().trim().length == 0) {
                    tv_reg_email_title.visibility = View.GONE
                } else {
                    tv_reg_email_title.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            imgBack -> finish()

            register -> presenter.checkUserExistsMethod(
                tv_code_register.text.toString().trim() + "" + tv_phone.text.toString().trim(),
                tv_user_name,
                tv_phone,
                et_passwordFirst,
                et_emailFirst,
                tv_state,
                tv_district, checkbox
            )

            /* presenter.hitRegister(
            tv_user_name,
            tv_phone,
            et_passwordFirst,
            et_emailFirst,
            tv_state,
            tv_district
        )*/

            tv_state -> showStateData()
            iv_state_arrow -> showStateData()
            tv_district -> showDistrictData()
            iv_district_arrow -> showDistrictData()
            //  linear_register_screen -> Utils.hideSoftKeyboard(activity)
            linear_check_text -> changeTermsBox()
        }
    }

    private fun changeTermsBox() {
        if (checkbox.isChecked) {
            checkbox.isChecked = false
        } else {
            checkbox.isChecked = true
        }
    }

    // for state data
    fun showStateData() {
        tv_state.setError(null)
        showStateDialog(tv_state, stateList, "State", tv_state.text.toString(), "")
    }

    // for district data
    fun showDistrictData() {
        if (!(tv_state.text.toString().trim().equals(""))) {
            showStateDialog(tv_district, stateList, "District", tv_district.text.toString(), "")
        } else {
            tv_state.setError("Please select state first")
        }
    }


    private lateinit var dialog: Dialog

    fun showStateDialog(
        et_register_country: TextView, countryList: List<StateDatum>, typeData: String,
        countryName: String, stateName: String
    ) {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.selection_state_dialog)
        dialog.show()
        var rv_country_list = dialog.findViewById(R.id.rv_country_list) as RecyclerView
        var tv_cancel_popup = dialog.findViewById(R.id.tv_cancel_popup) as TextView
        var tv_done_button = dialog.findViewById(R.id.tv_done_button) as TextView
        var tv_title_popup = dialog.findViewById(R.id.tv_title_popup) as TextView
        tv_done_button.visibility = View.INVISIBLE

        var list = ArrayList<StateSelectedData>()

        if (typeData.contentEquals("State")) {
            tv_title_popup.text = "Select State"
            for ((idx, value) in stateList.withIndex()) {
                var state1 = StateSelectedData()

                state1.setId(stateList.get(idx).getId()!!)
                state1.setName(stateList.get(idx).getTitle()!!)

                if (tv_state.getText().toString().trim().contentEquals(stateList.get(idx).getTitle()!!)) {

                    state1.setChekValue("true")
                } else {
                    state1.setChekValue("false")
                }
                list.add(state1)
            }
        } else {
            tv_title_popup.text = "Select District"
            var stateId: String
            // for District
            for ((idx, value) in stateList.withIndex()) {
                var state1 = StateSelectedData()
                state1.setId(stateList.get(idx).getId()!!)
                state1.setName(stateList.get(idx).getTitle()!!)
                if (tv_state.getText().toString().trim().contentEquals(stateList.get(idx).getTitle()!!)) {

                    for ((idx2, value) in stateList.get(idx).getDistricts()!!.withIndex()) {
                        var state1 = StateSelectedData()

                        state1.setId(stateList.get(idx).getDistricts()!!.get(idx2).getId()!!)
                        state1.setName(stateList.get(idx).getDistricts()!!.get(idx2).getTitle()!!)

                        if (tv_district.getText().toString().trim().contentEquals(
                                stateList.get(idx).getDistricts()!!.get(
                                    idx2
                                ).getTitle()!!
                            )
                        ) {

                            state1.setChekValue("true")
                        } else {
                            state1.setChekValue("false")
                        }
                        list.add(state1)
                    }

                    break;
                }
            }
        }

        var adapter = RVStateRegisterAdapter(activity, list, typeData)
        rv_country_list.layoutManager = LinearLayoutManager(activity)
        rv_country_list.adapter = adapter

        tv_cancel_popup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.cancel()
            }
        })
    }

    fun setData(typeData: String, value: String) {
        dialog.dismiss()
        if (typeData.equals("State")) {
            tv_state.text = value
            tv_district.text = ""

            if (value.equals("")) {
                tv_state.text = ""
            }
        } else {
            tv_district.text = value
            if (value.equals("")) {
                tv_district.text = ""
            }
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            60,             // Timeout duration
            TimeUnit.SECONDS,   // Unit of timeout
            this,           // Activity (for callback binding)
            mCallbacks
        )        // OnVerificationStateChangedCallbacks
    }

    override fun callForPhone(phone: String) {
        startPhoneNumberVerification(phone)
    }

    fun showSnackbar(message: String) {
        //  Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Utils.screenOpenCloseAnimation(activity)
    }
}
