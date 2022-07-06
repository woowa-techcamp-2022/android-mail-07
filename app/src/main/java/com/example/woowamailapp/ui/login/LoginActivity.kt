package com.example.woowamailapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.ActivityLoginBinding
import com.example.woowamailapp.ui.MainActivity
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

        binding.btNext.setOnClickListener {
            startMainActivity()
        }
    }
    private fun subscribeOn() {
        loginViewModel.loginFormState.observe(this) {
            val loginState = it ?: return@observe

            binding.btNext.isEnabled = loginState.isDataValid

            if(loginState.nicknameError ==  R.string.invalid_nickname){
                binding.tiNickname.error = getString(R.string.invalid_nickname)
            }
            else {
                binding.tiNickname.isErrorEnabled = false
            }
            if(loginState.emailError != null){
                binding.tiEmail.error = getString(R.string.invalid_email)
            }
            else {
                binding.tiEmail.isErrorEnabled = false
            }
        }
    }
    private fun startMainActivity(){
        Intent(this, MainActivity::class.java).apply {
            this.putExtra("nickname",binding.etNickname.text.toString())
            this.putExtra("email",binding.etEmail.text.toString())
            startActivity(this)
        }
    }
}