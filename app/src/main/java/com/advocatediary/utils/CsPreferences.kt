package com.advocatediary.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class CsPreferences {
    companion object Factory {
        fun getPreference(activity: Activity): SharedPreferences {
            return activity.getSharedPreferences("pref", Context.MODE_PRIVATE)
        }

        fun putString(activity: Activity, key: String, value: String) {
            getPreference(activity).edit().putString(key, value).commit()
        }

        fun readString(activity: Activity, key: String): String {
            return getPreference(activity).getString(key, "")
        }


        fun putInt(activity: Activity, key: String, value: Int) {
            getPreference(activity).edit().putInt(key, value).commit()
        }

        fun readInt(activity: Activity, key: String): Int {
            return getPreference(activity).getInt(key, 0)
        }

        fun clearPreferences(activity: Activity)
        {
            getPreference(activity).edit().clear().commit()
        }
    }
}