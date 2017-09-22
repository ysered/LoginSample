package com.ysered.loginsample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel


class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val profileImageSize = application.applicationContext.resources
            .getDimensionPixelSize(com.facebook.R.dimen.com_facebook_profilepictureview_preset_size_small)

    val profileData = FbProfileLiveData(profileImageSize)
}
