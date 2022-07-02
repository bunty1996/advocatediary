package com.advocatediary.fragment.aboutUs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.e.advocatediary.R

class AboutUsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.aboutus_fragment, container, false)
        initUI(view)
        return view
    }

    private fun initUI(view: View?) {

    }
}