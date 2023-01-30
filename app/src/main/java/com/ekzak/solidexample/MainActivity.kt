package com.ekzak.solidexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.ekzak.solidexample.listener.SwipeListener
import com.ekzak.solidexample.logger.EmailNotificationLogger
import com.ekzak.solidexample.logger.ToastNotificationLogger
import com.ekzak.solidexample.services.UpdateUiService
import com.ekzak.solidexample.ui.theme.SolidExampleTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity(), SwipeListener {

    enum class NotificationType {
        TOAST, EMAIL
    }

    private val updateUiService = UpdateUiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationType = NotificationType.TOAST
        val notificationLogger = when (notificationType) {
            NotificationType.TOAST -> ToastNotificationLogger()
            NotificationType.EMAIL -> EmailNotificationLogger()
        }
        setContent {
            SolidExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen(
                        context = this,
                        notificationType = notificationType,
                        updateUiService = updateUiService,
                        notificationLogger = notificationLogger,
                        listener = this
                    )
                }
            }
        }
    }

    override fun onSwipeLeft() {
        Log.d("checkData", "onSwipeLeft")
    }

    override fun onSwipeRight() {
        Log.d("checkData", "onSwipeRight")
    }
}
