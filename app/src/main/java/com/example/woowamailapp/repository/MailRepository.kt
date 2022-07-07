package com.example.woowamailapp.repository

import com.example.woowamailapp.model.Mail
import com.example.woowamailapp.utils.PRIMARY
import com.example.woowamailapp.utils.PROMOTION
import com.example.woowamailapp.utils.SOCIAL

class MailRepository {
    fun getPrimaryMails() : List<Mail> {
        return listOf(Mail(1,"GOOGLE", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PRIMARY),
        Mail(2,"David", "Let's Connect!", "Dear Ivy, We are seeking senior software engineer","20220705", PRIMARY),
        Mail(3,"구자경", "안녕하세요, 긴급 요청건으로 연락드립니다.", "첨부한 파일 확인 부탁드립니다.","20220705", PRIMARY),
        Mail(4,"크롱", "초대장 : 우아한 테크캠프 수업 - 2022년 7월", "다음 일정에 초대되었습니다. 우아한 테크캠프","20220705", PRIMARY),
        Mail(5,"Slido", "Your how-to guide for managing slido", "Hi, there. Welcome to Slido. ","20220705", PRIMARY))
    }
    fun getSocialMails() : List<Mail> {
        return listOf(Mail(6,"LinkedIn", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", SOCIAL),
            Mail(7,"김영희", "Let's Connect!", "Dear Ivy, We are seeking senior software engineer","20220705", SOCIAL),
            Mail(8,"Facebook", "안녕하세요, 긴급 요청건으로 연락드립니다.", "첨부한 파일 확인 부탁드립니다.","20220705", SOCIAL),
            Mail(9,"크롱", "초대장 : 우아한 테크캠프 수업 - 2022년 7월", "다음 일정에 초대되었습니다. 우아한 테크캠프","20220705", SOCIAL),
            Mail(10,"Slido", "Your how-to guide for managing slido", "Hi, there. Welcome to Slido. ","20220705", SOCIAL))
    }
    fun getPromotionMails() : List<Mail> {
        return listOf(Mail(11,"Zoom", "보안 알림", "Android에서 새로 로그인 abc@gmail Android","20220705", PROMOTION),
            Mail(12,"Udemy", "Let's Connect!", "Dear Ivy, We are seeking senior software engineer","20220705", PROMOTION),
            Mail(13,"우아한 테크캠프", "안녕하세요, 긴급 요청건으로 연락드립니다.", "첨부한 파일 확인 부탁드립니다.","20220705", PROMOTION),
            Mail(14,"Slack", "초대장 : 우아한 테크캠프 수업 - 2022년 7월", "다음 일정에 초대되었습니다. 우아한 테크캠프","20220705", PROMOTION),
            Mail(15,"Slido", "Your how-to guide for managing slido", "Hi, there. Welcome to Slido. ","20220705", PROMOTION))
    }
}