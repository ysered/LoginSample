package com.ysered.loginsample

import android.arch.lifecycle.LiveData
import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.Profile
import com.facebook.internal.ImageRequest


class FbProfileLiveData(private val profilePictureSize: Int) : LiveData<Resource<ProfileInfo>>() {

    private val request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken()) { json, response ->
        value = if (response != null) {
            val pictureUri = ImageRequest.getProfilePictureUri(Profile.getCurrentProfile().id, profilePictureSize, profilePictureSize)
            val payload = ProfileInfo(pictureUri, json["name"].toString(), json["email"].toString())
            Resource.Success(payload)
        } else {
            Resource.Error()
        }
    }

    override fun onActive() {
        super.onActive()
        value = Resource.Loading()
        request.parameters = Bundle().apply { putString("fields", "name,email") }
        request.executeAsync()
    }
}