package com.example.woowamailapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.woowamailapp.model.Mail
import com.example.woowamailapp.model.User
import com.example.woowamailapp.repository.MailRepository
import com.example.woowamailapp.utils.*

class MainViewModel : ViewModel(){
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user
    private val _mails = MutableLiveData<MutableList<Mail>>()
    val mails : LiveData<MutableList<Mail>> = _mails
    private val _type = MutableLiveData<Type>()
    val type : LiveData<Type> = _type


    private val mailRepository = MailRepository()

    init {
        _mails.postValue(mailRepository.getPrimaryMails().toMutableList())
        _type.postValue(Type.PRIMARY)
    }

    fun initUser(user : User){
        _user.postValue(user)
    }
    fun selectType(selectedType : Type){
        _type.postValue(selectedType)
        getTypedMails(selectedType)
    }

    private fun getTypedMails(type : Type){
        _mails.postValue(
            (when(type){
                Type.PRIMARY -> mailRepository.getPrimaryMails()
                Type.SOCIAL -> mailRepository.getSocialMails()
                Type.PROMOTION -> mailRepository.getPromotionMails()
            }).toMutableList()
        )
    }
    fun isPrimaryTypeNow() : Boolean {
        return type.value == Type.PRIMARY
    }

}