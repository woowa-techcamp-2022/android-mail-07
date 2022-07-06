package com.example.woowamailapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.ActivityLoginBinding
import com.example.woowamailapp.utils.afterTextChanged
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeOn()

        binding.etNickname.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(it)
            }
        }

        binding.etEmail.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(binding.etNickname.editableText.toString(),it)
            }
        }
    }
    private fun subscribeOn() {
        loginViewModel.loginFormState.observe(this) {
            val loginState = it ?: return@observe

            binding.btNext.isEnabled = loginState.isDataValid

            if(loginState.nicknameError != null){
               binding.etNickname.error = getString(R.string.invalid_nickname)
            }
            if(loginState.emailError != null){
                binding.etEmail.error = getString(R.string.invalid_email)
            }
        }
    }
}