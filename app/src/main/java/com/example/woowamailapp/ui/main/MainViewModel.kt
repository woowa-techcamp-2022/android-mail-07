package com.example.woowamailapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.woowamailapp.model.User

class MainViewModel : ViewModel(){
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    fun initUser(user : User){
        _user.postValue(user)
    }
}