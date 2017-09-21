package com.ysered.loginsample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.facebook.login.LoginManager


class ProfileFragment : Fragment() {

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_profile, container, false)

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        menu?.findItem(R.id.itemExit)?.apply {
            setOnMenuItemClickListener {
                LoginManager.getInstance().logOut()
                true
            }
        }
    }
}
