package com.ysered.loginsample

import android.net.Uri

data class ProfileInfo(
        val profilePictureUri: Uri,
        val name: String,
        val email: String
)
