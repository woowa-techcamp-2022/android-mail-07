package com.example.woowamailapp.model

data class Mail(val id : Int,
                val from : String,
                val title : String,
                val content : String,
                val date: String,
                val type : Int){
    val firstEnglish : Boolean
        get() = from.isNotBlank() && ((from.first() in 'A'..'Z') || (from.first() in 'a'..'z'))
}
