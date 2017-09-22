package com.ysered.loginsample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.app.AppCompatActivity
import com.ysered.loginsample.extensions.replaceFragment

class MainActivity : AppCompatActivity(), LifecycleOwner, FbLoginObserver.FbLoginListener {

    private val progressBar by lazy { findViewById<ContentLoadingProgressBar>(R.id.progress) }

    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = registry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registry.addObserver(FbLoginObserver(loginListener = this))
    }

    override fun onLoggedIn() {
        progressBar.hide()
        replaceFragment(ProfileFragment())
    }

    override fun onLoggedOut() {
        progressBar.hide()
        replaceFragment(LoginFragment())
    }
}
