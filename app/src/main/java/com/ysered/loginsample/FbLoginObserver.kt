package com.ysered.loginsample

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker


class FbLoginObserver(private val loginListener: FbLoginListener) : LifecycleObserver {

    private var tracker: AccessTokenTracker? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun addTracker() {
        tracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, newAccessToken: AccessToken?) {
                handleToken(newAccessToken)
            }
        }
        handleToken(AccessToken.getCurrentAccessToken())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun removeTracker() {
        tracker?.stopTracking()
    }

    private fun handleToken(accessToken: AccessToken?) {
        if (accessToken != null)
            loginListener.onLoggedIn()
        else
            loginListener.onLoggedOut()
    }

    interface FbLoginListener {
        fun onLoggedIn()
        fun onLoggedOut()
    }
}
