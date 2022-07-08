package com.example.woowamailapp.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.woowamailapp.R
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {

    private val _nickname = MutableLiveData<String>()
    val nickname : LiveData<String> = _nickname

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun loginDataChanged(nickname: String, email: String? = null) {
        if (!isNicknameValid(nickname)) {
            _loginForm.value = LoginFormState(nicknameError = R.string.invalid_nickname)
        }
        else if(email.isNullOrEmpty())
            _loginForm.value = LoginFormState(isDataValid = false)
        else if (!isMailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.invalid_email)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isNicknameValid(name: String): Boolean {
        return (name.length >= 5 ) && ( name.length <= 10)
    }
    
    private fun isMailValid(emailAddress: String): Boolean {
            val patterns = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            return Pattern.matches(patterns,emailAddress)
    }
}