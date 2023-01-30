package com.ekzak.solidexample.logger

import android.util.Log

open class NotificationLogger {
    open fun logNotification(msg: String) {
        Log.d("checkData", "NotificationLogger logNotification $msg")
    }
}

class EmailNotificationLogger : NotificationLogger() {

    override fun logNotification(msg: String) {
        Log.d("checkData", "EmailNotificationLogger logNotification $msg")
    }
}

class ToastNotificationLogger : NotificationLogger() {

    override fun logNotification(msg: String) {
        Log.d("checkData", "ToastNotificationLogger logNotification $msg")
    }
}
