package com.ekzak.solidexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ekzak.solidexample.services.NotificationService
import com.ekzak.solidexample.services.UpdateUiService
import com.ekzak.solidexample.ui.theme.SolidExampleTheme

class MainActivity : ComponentActivity() {

    private val notificationService = NotificationService()
    private val updateUiService = UpdateUiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolidExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen(
                        notificationService = notificationService,
                        updateUiService = updateUiService
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    SolidExampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MainScreen(
                notificationService = NotificationService(),
                updateUiService = UpdateUiService()
            )
        }
    }
}
