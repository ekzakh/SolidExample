package com.ekzak.solidexample

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ekzak.solidexample.MainActivity.NotificationType
import com.ekzak.solidexample.services.EmailNotification
import com.ekzak.solidexample.services.ToastNotification
import com.ekzak.solidexample.services.UpdateUiService

@Composable
fun MainScreen(
    context: Context,
    notificationType: NotificationType,
    updateUiService: UpdateUiService,
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Text field: ${updateUiService.updateText(textState.value.text)}")
        TextField(value = textState.value, onValueChange = { textState.value = it })
        Button(
            onClick = {
                when (notificationType) {
                    NotificationType.TOAST -> ToastNotification(context).sendNotification(textState.value.text)
                    NotificationType.EMAIL -> EmailNotification().sendNotification(textState.value.text)
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Send")
        }
    }
}
