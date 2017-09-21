package com.ysered.loginsample.extensions

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


inline fun <reified T : Activity> Activity.startActivity(vararg params: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    for ((key, value) in params) {
        intent.putExtra(key, value as? Parcelable)
    }
    this.startActivity(intent)
}

fun Activity.replaceFragment(fragment: Fragment,
                             id: Int = android.R.id.content,
                             addToBackStack: Boolean = false) {
    if (this is AppCompatActivity) {
        val transaction = this.supportFragmentManager
                ?.beginTransaction()
                ?.replace(id, fragment, null)
        if (addToBackStack)
            transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
