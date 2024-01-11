package com.justparokq.feature.chat.api

import androidx.lifecycle.ViewModel
import com.justparokq.feature.chat.internal.model.CurrentUserMessageUIModel
import com.justparokq.feature.chat.internal.model.OtherUserMessageUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor() : ViewModel() {

    var textInputStr = ""
    val updateInput: (String) -> Unit = { str: String -> textInputStr = str }
    val onSendClick = { textInputStr = "Sent" }

    fun getMessages() = listOf(
        OtherUserMessageUIModel(
            text = "Hello",
            time = "13:00",
            userName = "P"
        ),
        CurrentUserMessageUIModel(
            text = "HI!",
            time = "13:01",
            isSent = true
        )
    )
}

/**
 * Сообщение отправляем.
 * Пока сообщение отправляется - около него крутится кружок отправки
 * как только отпраивлось - от сервера приходит ответ с ID сообщения что оно дошло.
 * тогда замена крутяшки на галочку.
 *
 * чат с сообщениями
 * можно отправлять
 * сообщения видны всем в чате
 * чат один
 * Имя пользователя генерируется при входе. Буква рандомная (не занятая?)
 * Выдается сервером
 *
 * 1 бэкграунд
 * 2 элемент сообщения
 *  - иконка пользователя. Буква в кружке
 *  - сообщение
 *  - было ли отправлено
 *  - время получения
 * */