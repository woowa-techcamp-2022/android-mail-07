package com.example.woowamailapp.utils

enum class Type {
    PRIMARY, SOCIAL, PROMOTION
}

enum class Tab(val position : Int) {
    MAIL(0), SETTING(1)
}

const val PRIMARY = 1001
const val SOCIAL = 1002
const val PROMOTION = 1003

const val MAIL = 1101
const val SETTING = 1102

const val CURRENT_TAB = "current"