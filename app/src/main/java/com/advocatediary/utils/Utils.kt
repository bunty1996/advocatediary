package com.advocatediary.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.content.ContextCompat.getSystemService
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import com.e.advocatediary.R
import dmax.dialog.SpotsDialog

class Utils {

    companion object Factory {

        lateinit var spotDialog: AlertDialog

        // for show progress dialog
        fun showDialog(context: Context) {
            spotDialog = SpotsDialog.Builder()
                .setContext(context)
                .setTheme(R.style.Custom)
                .setMessage("Loading...")
                .build()
            if (!(spotDialog.isShowing)) {
                spotDialog.show()
            }else
            {
                spotDialog.cancel()
            }
        }

        // for hide progress dialog
        fun hideDialog() {
            try {
                if (spotDialog != null) {
                    spotDialog.dismiss()
                }
            } catch (e: Exception) {

            }
        }


        fun screenOpenCloseAnimation(activity: Activity) {
            activity.overridePendingTransition(R.anim.grow_from_middle, R.anim.shrink_to_middle);
        }

        // for get color from color name
        fun getColor(nameValue: String): String {
            var colorName = ""
            if (nameValue.equals("a", ignoreCase = true)) {

                colorName = kApphomeBawaTheme
            } else if (nameValue.equals("b", ignoreCase = true)) {
                colorName = kApphomeGreenTheme
            } else if (nameValue.equals("c", ignoreCase = true)) {
                colorName = kApphomeBlueTheme
            } else if (nameValue.equals("d", ignoreCase = true)) {
                colorName = kAppAlphabetyellow
            } else if (nameValue.equals("e", ignoreCase = true)) {
                colorName = kAppAlphabetlemon
            } else if (nameValue.equals("f", ignoreCase = true)) {
                colorName = kAppAlphabetlightYellow
            } else if (nameValue.equals("g", ignoreCase = true)) {
                colorName = kApphomeGreenTheme
            } else if (nameValue.equals("h", ignoreCase = true)) {
                colorName = kAppAlphabetyellow
            } else if (nameValue.equals("i", ignoreCase = true)) {
                colorName = kAppAlphabetskyblue
            } else if (nameValue.equals("j", ignoreCase = true)) {
                colorName = kAppAlphabetpinkish
            } else if (nameValue.equals("k", ignoreCase = true)) {
                colorName = kAppAlphabetskyblue
            } else if (nameValue.equals("l", ignoreCase = true)) {
                colorName = kApplightGreenTheme
            } else if (nameValue.equals("m", ignoreCase = true)) {
                colorName = kAppAlphabetlightred

            } else if (nameValue.equals("n", ignoreCase = true)) {
                colorName = kApplightPurpleTheme
            } else if (nameValue.equals("o", ignoreCase = true)) {
                colorName = kApplightGreenTheme

            } else if (nameValue.equals("p", ignoreCase = true)) {
                colorName = kAppAlphabetlightred
            } else if (nameValue.equals("q", ignoreCase = true)) {
                colorName = kAppAlphabetlightYellow
            } else if (nameValue.equals("r", ignoreCase = true)) {
                colorName = kAppAlphabetsealGreen
            } else if (nameValue.equals("s", ignoreCase = true)) {
                colorName = kAppAlphabetpinkPurple
            } else if (nameValue.equals("t", ignoreCase = true)) {
                colorName = kAppAlphabetpurple
            } else if (nameValue.equals("u", ignoreCase = true)) {
                colorName = kAppAlphabetlemon
            } else if (nameValue.equals("v", ignoreCase = true)) {
                colorName = kAppAlphabetlightBawa
            } else if (nameValue.equals("w", ignoreCase = true)) {
                colorName = kAppAlphabetlightGreen

            } else if (nameValue.equals("x", ignoreCase = true)) {
                colorName = kAppAlphabetmustardGreen

            } else if (nameValue.equals("y", ignoreCase = true)) {
                colorName = kAppAlphabetsealGreen

            } else if (nameValue.equals("z", ignoreCase = true)) {
                colorName = kAppAlphabetorange

            } else {
                colorName = kAppAlphabetlightYellow
            }
            return colorName
        }

        val kAppAlphabetpink = "#ecc8dc"
        val kAppAlphabetyellow = "#f1e4ad"
        val kAppAlphabetlemon = "#d8f4b4"
        val kAppAlphabetskyblue = "#b4d8f4"
        val kAppAlphabetpinkPurple = "#EAD1DC"
        val kAppAlphabetlightBawa = "#FCE5CD"
        val kAppAlphabetlightGreen = "#c3e8c8"
        val kAppAlphabetmustardGreen = "#d9d79c"
        val kAppAlphabetsealGreen = "#b4f4d0"
        val kAppAlphabetorange = "#f69f70"
        val kAppAlphabetpurple = "#c2adf1"
        val kAppAlphabetpinkish = "#f7cae4"
        val kAppAlphabetlightYellow = "#feff8f"
        val kAppAlphabetlightred = "#f58b84"
        val kApphomeBlueTheme = "#c2d9f1"
        val kApphomeBawaTheme = "#f6cfba"
        val kApphomeGreenTheme = "#dcecc8"
        val kApplightGreenTheme = "#F6FBF5"
        val kApplightPurpleTheme = "#F0F3FA"
        val kApplightRedTheme = "#fef4f3"


        fun hideSoftKeyboard(mActivity: Activity) {
            // Check if no view has focus:
            val view = mActivity.currentFocus
            if (view != null) {
                val inputManager = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        @SuppressLint("ServiceCast")
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        fun showAnimationList(context:Context,view:View)
        {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_from_right)
            animation.setInterpolator(AccelerateDecelerateInterpolator())
            view.setAnimation(animation)
            animation.start()
        }


        fun showFromTopToBottomAnimation(context:Context,view:View)
        {
            val animation = AnimationUtils.loadAnimation(context, R.anim.top_to_bottom)
            animation.setInterpolator(AccelerateDecelerateInterpolator())
            view.setAnimation(animation)
            animation.start()
        }
    }



}