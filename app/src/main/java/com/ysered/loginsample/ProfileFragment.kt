package com.ysered.loginsample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.facebook.login.LoginManager
import com.squareup.picasso.Picasso
import com.ysered.loginsample.extensions.debug


class ProfileFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(ProfileViewModel::class.java) }

    private lateinit var progress: ContentLoadingProgressBar
    private lateinit var profileImage: ImageView
    private lateinit var nameText: TextView
    private lateinit var emailText: TextView

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_profile, container, false)?.apply {
            progress = findViewById(R.id.progress)
            profileImage = findViewById(R.id.profileImage)
            nameText = findViewById(R.id.nameText)
            emailText = findViewById(R.id.emailText)
        }
        viewModel.profileData.observe(this, Observer<Resource<ProfileInfo>> { resource ->
            resource?.let {
                when (it) {
                    is Resource.Loading -> showLoading(isLoading = true)
                    is Resource.Success<ProfileInfo> -> {
                        showLoading(isLoading = false)
                        showUser(it.payload)
                    }
                    is Resource.Error -> throw RuntimeException("Error occurred!")
                }
            }
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        menu?.findItem(R.id.itemExit)?.apply {
            setOnMenuItemClickListener {
                LoginManager.getInstance().logOut()
                true
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progress.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showUser(profileInfo: ProfileInfo) {
        debug("Loaded user: $profileInfo")
        Picasso.with(activity).load(profileInfo.profilePictureUri).into(profileImage)
        nameText.text = profileInfo.name
        emailText.text = profileInfo.email
    }
}
