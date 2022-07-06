package com.example.woowamailapp.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.woowamailapp.R

class LoginViewModel : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun loginDataChanged(nickname: String, email: String? = null) {
        if (!isNicknameValid(nickname)) {
            _loginForm.value = LoginFormState(nicknameError = R.string.invalid_nickname)
        }
        else if(email.isNullOrEmpty()) _loginForm.value = LoginFormState(isDataValid = false)
        else if (!isMailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isNicknameValid(nickname: String): Boolean {
        return (nickname.length >= 5 ) && ( nickname.length <= 10)
    }

    private fun isMailValid(email: String): Boolean {
        return if (!email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }
}