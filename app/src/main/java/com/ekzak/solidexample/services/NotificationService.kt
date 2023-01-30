package com.ekzak.solidexample.services

import android.util.Log

class NotificationService {
    fun sendNotification(text: String) {
        Log.d("sendNotification","some notification $text")
    }
}
