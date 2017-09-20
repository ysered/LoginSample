package com.ysered.loginsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.AccessToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isExpired = AccessToken.getCurrentAccessToken()?.isExpired ?: true
        if (isExpired) {
            println("Expired!!!")
            replaceFragment(android.R.id.content, LoginFragment())
        } else {
            println("Logged in!!!")
        }
    }
}
