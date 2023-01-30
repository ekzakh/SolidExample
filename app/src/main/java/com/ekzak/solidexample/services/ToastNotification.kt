package com.ekzak.solidexample.services

import android.content.Context
import android.util.Log
import android.widget.Toast

class ToastNotification(private val context: Context): NotificationService {
    override fun sendNotification(text: String) {
        Log.d("ToastNotification", "toast notification: $text")
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
