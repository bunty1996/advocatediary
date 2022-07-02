package com.advocatediary.fragment.privacyPolicy

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.advocatediary.utils.Utils
import com.e.advocatediary.R

class PrivacyPolicyFragment : Fragment() {

   lateinit var policyActivity: Activity
    var url: String = "https://westhave.com/acd/privacy-policy"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.privacypolicy_fragment, container, false)
        policyActivity = activity!!
        initUI(view)
        return view
    }

    private fun initUI(view: View?) {
        var myWebView = view!!.findViewById<WebView>(R.id.myWebView)
        myWebView.loadUrl(url)
        myWebView.webViewClient=MyClient()
        Utils.showDialog(policyActivity)
    }

    class MyClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Utils.hideDialog()
            view!!.loadUrl(url)
        }
    }
}