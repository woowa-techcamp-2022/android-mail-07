package com.example.woowamailapp.ui.login

data class LoginFormState(
    val nicknameError: Int? = null,
    val emailError: Int? = null,
    val isDataValid: Boolean = false
)