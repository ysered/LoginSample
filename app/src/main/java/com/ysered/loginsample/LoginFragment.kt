package com.ysered.loginsample

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class LoginFragment : Fragment() {

    private var loginButton: LoginButton? = null

    private val loginManager by lazy { LoginManager.getInstance() }
    private val callbackManager by lazy { CallbackManager.Factory.create() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton = view?.findViewById(R.id.login_button) as LoginButton?
        loginButton?.apply {
            setReadPermissions("email", "public_profile")
            fragment = this@LoginFragment
            registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
                override fun onCancel() {

                }

                override fun onError(exception: FacebookException?) {

                }

                override fun onSuccess(loginResult: LoginResult?) {
                    println("Success!!!")
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}