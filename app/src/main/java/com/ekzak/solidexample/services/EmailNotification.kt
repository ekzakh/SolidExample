package com.ekzak.solidexample.services

import android.util.Log

class EmailNotification: NotificationService {
    override fun sendNotification(text: String) {
        Log.d("EmailNotification", "email notification: $text")
    }
}
