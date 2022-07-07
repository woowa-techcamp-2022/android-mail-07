package com.example.woowamailapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.woowamailapp.model.Mail
import com.example.woowamailapp.model.User
import com.example.woowamailapp.utils.PRIMARY
import com.example.woowamailapp.utils.PROMOTION
import com.example.woowamailapp.utils.SOCIAL

class MainViewModel : ViewModel(){
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user
    private val _mails = MutableLiveData<MutableList<Mail>>()
    val mails : LiveData<MutableList<Mail>> = _mails
    private val _type = MutableLiveData<Int>()
    val type : LiveData<Int> = _type

    private val dummy = listOf(
        Mail(1,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PRIMARY),
        Mail(2,"David", "Let's Connect!", "Dear Ivy, We are seeking senior software engineer","20220705", SOCIAL),
        Mail(3,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PROMOTION),
        Mail(4,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PRIMARY),
        Mail(5,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", SOCIAL),
        Mail(6,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PROMOTION),
        Mail(7,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", SOCIAL),
        Mail(8,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PROMOTION),
        Mail(9,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PRIMARY),
        Mail(10,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PRIMARY),
        Mail(11,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", SOCIAL),
        Mail(12,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", SOCIAL),
        Mail(13,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PROMOTION),
        Mail(14,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PRIMARY),
        Mail(15,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PROMOTION)
    )
    init {
        _mails.postValue(this.dummy.toMutableList())
        _type.postValue(PRIMARY)
    }

    fun initUser(user : User){
        _user.postValue(user)
    }
    fun selectType(selectedType : Int){
        _type.postValue(selectedType)
    }
}