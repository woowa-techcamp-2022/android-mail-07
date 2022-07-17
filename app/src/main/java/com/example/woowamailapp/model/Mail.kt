package com.example.woowamailapp.model

import com.example.woowamailapp.utils.Type

data class Mail(val id : Int,
                val from : String,
                val title : String,
                val content : String,
                val date: String,
                val type : Type){
    val firstEnglish : Boolean
        get() = from.isNotBlank() && ((from.first() in 'A'..'Z') || (from.first() in 'a'..'z'))
}
