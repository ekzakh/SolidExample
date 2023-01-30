package com.ekzak.solidexample.logger

import android.util.Log
import com.ekzak.solidexample.services.DataBase

open class NotificationLogger {
    open fun logNotification(msg: String) {
        Log.d("checkData", "NotificationLogger logNotification $msg")
    }
}

class EmailNotificationLogger(private val dataBase: DataBase) : NotificationLogger() {

    override fun logNotification(msg: String) {
        Log.d("checkData", "EmailNotificationLogger logNotification $msg")
        dataBase.save(msg)
    }
}

class ToastNotificationLogger : NotificationLogger() {

    override fun logNotification(msg: String) {
        Log.d("checkData", "ToastNotificationLogger logNotification $msg")
    }
}
