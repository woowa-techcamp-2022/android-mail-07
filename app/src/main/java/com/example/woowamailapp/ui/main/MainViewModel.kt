package com.example.woowamailapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.woowamailapp.model.Mail
import com.example.woowamailapp.model.User
import com.example.woowamailapp.repository.MailRepository
import com.example.woowamailapp.utils.MAIL
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


    private val mailRepository = MailRepository()

    init {
        _mails.postValue(mailRepository.getPrimaryMails().toMutableList())
        _type.postValue(PRIMARY)
    }

    fun initUser(user : User){
        _user.postValue(user)
    }
    fun selectType(selectedType : Int){
        _type.postValue(selectedType)
        getTypedMails(selectedType)
    }

    private fun getTypedMails(type : Int){
        _mails.postValue(
            (when(type){
                PRIMARY -> mailRepository.getPrimaryMails()
                SOCIAL -> mailRepository.getSocialMails()
                else -> mailRepository.getPromotionMails()
            }).toMutableList()
        )
    }
    fun isPrimaryTypeNow() : Boolean {
        return type.value == PRIMARY
    }

}