package com.advocatediary.activity.home

import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.advocatediary.activity.addCaseInfo.AddCaseInfoActivity
import com.advocatediary.activity.addClient.AddClientActivity
import com.advocatediary.activity.allClient.AllClientActivity
import com.advocatediary.activity.login.LoginActivity
import com.advocatediary.adapter.RVNavigationAdapter
import com.advocatediary.fragment.homeFragment.HomeFragment
import com.advocatediary.fragment.myCase.MyCaseFragment
import com.advocatediary.fragment.myClient.MyClientFragment
import com.advocatediary.fragment.settings.Settings
import com.advocatediary.model.navigation.NavigationData
import com.advocatediary.utils.Constants
import com.advocatediary.utils.CsPreferences
import com.advocatediary.utils.Utils
import com.andexert.library.RippleView
import com.e.advocatediary.R
import kotlinx.android.synthetic.main.app_bar_navigation__drawer.*
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.nav_header_navigation__drawer.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private var checkExitValue: Boolean = false

    override fun onClick(v: View?) {
        when (v) {
            //  navi -> openDrawerLayout()
            // img_search -> searchShow()
            iv_cross_data -> hideData()
            //   img_add -> addCaseButton()
        }
    }

    fun addCaseButton() {
        // for add case button
        if (serach_position == 0 || serach_position == 1) {
            var addCaseInfoActivity = Intent(activity, AllClientActivity::class.java)
            startActivity(addCaseInfoActivity)
            Utils.screenOpenCloseAnimation(activity)
        } else {
            var intent = Intent(activity, AddClientActivity::class.java)
            intent.putExtra("client_id", "")
            startActivity(intent)
            Utils.screenOpenCloseAnimation(activity)
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        var currentTime = System.currentTimeMillis() + 86400000
        Log.e("efcwefdsc", currentTime.toString())
        activity = this;
        initUI()
    }

    private lateinit var trans: FragmentTransaction

    private lateinit var rvNavigationAdapter: RVNavigationAdapter
    private lateinit var img_search: ImageView

    private lateinit var receiver: BroadcastReceiver

    private var serach_position: Int = 0

    private fun initUI() {
        img_search = findViewById(R.id.img_search)
        // set side bar and top data
        // home_menu_ripple = findViewById(R.id.home_menu_ripple);


        tv_side_bar_name.text = CsPreferences.readString(activity, Constants.Factory.USER_NAME)
        tv_side_bar_number.text = CsPreferences.readString(activity, Constants.Factory.USER_PHONE)
        tv_advocate_title.text = CsPreferences.readString(activity, Constants.Factory.USER_NAME)
        var value: String = CsPreferences.readString(activity, Constants.Factory.USER_NAME)
        val firstLetter = value.substring(0, 1)
        tv_sidebar_title_big.setText(firstLetter.toUpperCase())


        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("MMM d, yyyy")
        val date1 = dateFormat.format(cal.time)
        tv_date_current.text = date1

        try {
            // for set the App Current Version
            val pInfo = packageManager.getPackageInfo(packageName, 0)
            val version = pInfo.versionName
            tv_version_number.text = "Version : $version"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }


        var drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(view: View, v: Float) {

            }

            override fun onDrawerOpened(view: View) {
                hideKeyboard(activity)
            }

            override fun onDrawerClosed(view: View) {
                hideKeyboard(activity)
            }

            override fun onDrawerStateChanged(i: Int) {

            }
        })


        menu_home_ripple.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                openDrawerLayout()
            }
        })

        ripple_view_search.setOnRippleCompleteListener(object : RippleView.OnRippleCompleteListener {
            override fun onComplete(rippleView: RippleView?) {
                searchShow()
            }
        })

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        // navi.setOnClickListener(this)

        val navigationDataArrayList = ArrayList<NavigationData>()
        val navigationData = NavigationData()
        navigationData.setCheckStatus("true")
        navigationData.setName("Home")
        //  navigationData.setImage(R.drawable.home_btn)
        navigationData.setImage(R.drawable.home_new_iconn)

        val navigationData1 = NavigationData()
        navigationData1.setCheckStatus("false")
        navigationData1.setName("My Cases")
        //   navigationData1.setImage(R.drawable.may_cases_btn_active)
        navigationData1.setImage(R.drawable.my_cases_iconn)

        val navigationData7 = NavigationData()
        navigationData7.setCheckStatus("false")
        navigationData7.setName("Add New Case")
        // navigationData7.setImage(R.drawable.plus_new_icon)
        navigationData7.setImage(R.drawable.add_new_case_iconn)

        val navigationData2 = NavigationData()
        navigationData2.setCheckStatus("false")
        navigationData2.setName("My Clients")
        //navigationData2.setImage(R.drawable.clients_btn)
        navigationData2.setImage(R.drawable.my_client_new_iconss)

        val navigationData8 = NavigationData()
        navigationData8.setCheckStatus("false")
        navigationData8.setName("Add New Client")
        // navigationData8.setImage(R.drawable.new_client_add)
        navigationData8.setImage(R.drawable.add_new_clientt_iconn)

        val navigationData4 = NavigationData()
        navigationData4.setCheckStatus("false")
        navigationData4.setName("Settings")
        //navigationData4.setImage(R.drawable.settings)
        navigationData4.setImage(R.drawable.setting_iconn)

        val navigationData5 = NavigationData()
        navigationData5.setCheckStatus("false")
        navigationData5.setName("Logout")
        //  navigationData5.setImage(R.drawable.logout)
        navigationData5.setImage(R.drawable.logout_new_iconn)


        navigationDataArrayList.add(navigationData)
        navigationDataArrayList.add(navigationData1)
        navigationDataArrayList.add(navigationData7)

        navigationDataArrayList.add(navigationData2)
        navigationDataArrayList.add(navigationData8)
        // navigationDataArrayList.add(navigationData3);
        navigationDataArrayList.add(navigationData4)
        navigationDataArrayList.add(navigationData5)
        rvNavigationAdapter = RVNavigationAdapter(this, navigationDataArrayList)
        val linearLayoutManager = LinearLayoutManager(this)
        rv_navigation_view.setLayoutManager(linearLayoutManager)
        rv_navigation_view.setAdapter(rvNavigationAdapter)

        img_search.setOnClickListener(this)
        iv_cross_data.setOnClickListener(this)

// for set first tab
        setAllData(0)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                serach_position = intent!!.getIntExtra("serach_position", 0)

                rel_setting_text.visibility = View.GONE
                linear_right_nav.visibility = View.GONE
                linear_welcome_text.visibility = View.GONE
                linear_search.visibility = View.GONE

                setSelectedTab(serach_position)

                if (serach_position == 0) {
                    img_search.visibility = View.INVISIBLE
                    linear_welcome_text.visibility = View.VISIBLE
                    linear_right_nav.visibility = View.VISIBLE
                } else

                    if (serach_position == 1) {
                        img_search.visibility = View.VISIBLE
                        linear_welcome_text.visibility = View.VISIBLE
                        linear_right_nav.visibility = View.VISIBLE
                    } else
                        if (serach_position == 3) {
                            img_search.visibility = View.VISIBLE
                            linear_welcome_text.visibility = View.VISIBLE
                            linear_right_nav.visibility = View.VISIBLE
                        } else
                            if (serach_position == 5) {
                                img_search.visibility = View.INVISIBLE
                                rel_setting_text.visibility = View.VISIBLE
                            } else {
                                img_search.visibility = View.INVISIBLE
                            }

                if (!(Utils.isNetworkAvailable(activity))) {
                    img_search.visibility = View.INVISIBLE
                }
            }
        }

        registerReceiver(receiver, object : IntentFilter("search_display") {

        })

        et_client_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                /* if (serach_position == 3) {
                     // for client search
                     var intent = Intent()
                     intent.action = Constants.MY_CLIENT_UPDATE
                     intent.putExtra("search_value", et_client_search.text.toString().trim())
                     activity.sendBroadcast(intent)
                 } else
                     if (serach_position == 1) {
                         // for case search
                         var intent = Intent()
                         intent.action = Constants.MY_CASE_UPDATE
                         intent.putExtra("search_value", et_client_search.text.toString().trim())
                         activity.sendBroadcast(intent)
                     }*/
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (serach_position == 3) {
                    // for client search
                    var intent = Intent()
                    intent.action = Constants.MY_CLIENT_UPDATE
                    intent.putExtra("search_value", et_client_search.text.toString().trim())
                    activity.sendBroadcast(intent)
                } else
                    if (serach_position == 1) {
                        // for case search
                        var intent = Intent()
                        intent.action = Constants.MY_CASE_UPDATE
                        intent.putExtra("search_value", et_client_search.text.toString().trim())
                        activity.sendBroadcast(intent)
                    }
            }
        })

        et_client_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (serach_position == 3) {
                        // for client search
                        var intent = Intent()
                        intent.action = Constants.MY_CLIENT_UPDATE
                        intent.putExtra("search_value", et_client_search.text.toString().trim())
                        activity.sendBroadcast(intent)
                    } else
                        if (serach_position == 1) {
                            // for case search
                            var intent = Intent()
                            intent.action = Constants.MY_CASE_UPDATE
                            intent.putExtra("search_value", et_client_search.text.toString().trim())
                            activity.sendBroadcast(intent)
                        }
                }
                return true
            }

        })


        //  img_add.setOnClickListener(this)
    }

    // for set selected tab
    fun setSelectedTab(selectedPositions: Int) {
        var tempList = ArrayList<NavigationData>()
        tempList.clear()
        for ((idx, value) in rvNavigationAdapter.getNavigationList().withIndex()) {
            val navigationData = NavigationData()
            if (idx == selectedPositions) {
                navigationData.setCheckStatus("true")
            } else {
                navigationData.setCheckStatus("false")
            }
            navigationData.setName(rvNavigationAdapter.getNavigationList().get(idx).getName())
            navigationData.setImage(rvNavigationAdapter.getNavigationList().get(idx).getImage())
            /* rvNavigationAdapter.getNavigationList().removeAt(idx)
             rvNavigationAdapter.getNavigationList().add(idx, navigationData)*/

            tempList.add(navigationData)

        }
        rvNavigationAdapter.updateList(tempList)
        rvNavigationAdapter.notifyDataSetChanged()
        // rvNavigationAdapter.notifyDataSetChanged()
    }


    fun hideKeyboard(activity: Activity) {
        // Check if no view has focus:
        val view = activity.currentFocus
        if (view != null) {
            val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    public fun openDrawerLayout() {
        drawer_layout.openDrawer(Gravity.START)
    }

    // for set drawer items
    public fun setAllData(position: Int) {
        drawer_layout.closeDrawers()
        if (position == 0) {
            var fragment = getSupportFragmentManager().findFragmentByTag("home");
            if (fragment != null)
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();

            var homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.grow_from_middle, R.anim.shrink_to_middle)
                .replace(R.id.contain, homeFragment, "home").commit()
        } else
            if (position == 1) {

                var myCasesFragment = MyCaseFragment()
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.grow_from_middle, R.anim.shrink_to_middle)
                    .replace(R.id.contain, myCasesFragment, "my_cases")
                    .addToBackStack(null).commit()
                //  Utils.screenOpenCloseAnimation(activity)
            } else
                if (position == 2) {
                    //var addCaseInfoActivity = Intent(activity, AddClientActivity::class.java)
                    var addCaseInfoActivity = Intent(activity, AllClientActivity::class.java)
                    startActivity(addCaseInfoActivity)
                    Utils.screenOpenCloseAnimation(activity)
                    /*var addCaseInfoFragment = AddCaseInfoActivity()
                    supportFragmentManager.beginTransaction().replace(R.id.contain, addCaseInfoFragment, "my_client")
                        .commit()*/
                } else
                    if (position == 3) {

                        var myClientFragment = MyClientFragment()
                        supportFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.grow_from_middle, R.anim.shrink_to_middle)
                            .replace(R.id.contain, myClientFragment, "my_client")
                            .addToBackStack(null)
                            .commit()
                        Utils.screenOpenCloseAnimation(activity)
                    } else
                        if (position == 4) {
                            // for Add client
                            var intent = Intent(activity, AddClientActivity::class.java)
                            intent.putExtra("client_id", "")
                            startActivity(intent)
                            Utils.screenOpenCloseAnimation(activity)

                        } else
                            if (position == 5) {

                                var settingsFragment = Settings()
                                supportFragmentManager.beginTransaction()
                                    .setCustomAnimations(R.anim.grow_from_middle, R.anim.shrink_to_middle)
                                    .replace(R.id.contain, settingsFragment, "settings").addToBackStack(null)
                                    .commit()
                                Utils.screenOpenCloseAnimation(activity)
                            } else
                                if (position == 6) {
                                    logoutAlert()
                                }
    }


    lateinit var alert: AlertDialog

    fun logoutAlert() {
        var alertDialog = AlertDialog.Builder(activity)
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Are you sure you want to logout?")
        alertDialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                alert.dismiss()

                CsPreferences.clearPreferences(activity)
                var intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                finishAffinity()
                Utils.screenOpenCloseAnimation(activity)
            }
        });

        alertDialog.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                alert.cancel()
            }
        });
        alert = alertDialog.create()
        alert.show()

    }

    fun searchShow() {

        if (!(linear_search.isShown)) {
            linear_search.visibility = View.GONE

            linear_search.visibility = View.GONE
            linear_welcome_text.visibility = View.VISIBLE
            img_search.visibility = View.INVISIBLE
            /* //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
             et_client_search.text.clear()
             et_client_search.isFocusable = true
             et_client_search.requestFocus()
             val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
             imm.showSoftInput(et_client_search, InputMethodManager.SHOW_IMPLICIT)
 */
            if (serach_position == 1) {

                var intent = Intent()
                intent.action = Constants.MY_CASE_UPDATE
                intent.putExtra("search_value", et_client_search.text.toString().trim())
                activity.sendBroadcast(intent)
            } else {
                var intent = Intent()
                intent.action = Constants.MY_CLIENT_UPDATE
                intent.putExtra("search_value", et_client_search.text.toString().trim())
                activity.sendBroadcast(intent)
            }
        } else {
            linear_search.visibility = View.GONE

            et_client_search.setText("")

            linear_search.visibility = View.GONE
            linear_welcome_text.visibility = View.VISIBLE
            img_search.visibility = View.VISIBLE
            hideKeyboard(activity)
        }
    }

    fun hideData() {
        linear_search.visibility = View.GONE

        et_client_search.setText("")

        linear_search.visibility = View.GONE
        linear_welcome_text.visibility = View.VISIBLE
        img_search.visibility = View.VISIBLE
        hideKeyboard(activity)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
            Utils.screenOpenCloseAnimation(activity)
        } else {
            if (checkExitValue) {
                super.onBackPressed()
                return
            }
            checkExitValue = true
            Toast.makeText(activity, "Press once again to exit", Toast.LENGTH_LONG).show()
            var handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    checkExitValue = false
                }
            }, 2000)
        }
    }


}