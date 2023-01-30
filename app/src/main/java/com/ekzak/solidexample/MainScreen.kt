package com.ekzak.solidexample

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ekzak.solidexample.MainActivity.NotificationType
import com.ekzak.solidexample.listener.SwipeListener
import com.ekzak.solidexample.logger.NotificationLogger
import com.ekzak.solidexample.services.EmailNotification
import com.ekzak.solidexample.services.ToastNotification
import com.ekzak.solidexample.services.UpdateUiService
import kotlin.math.roundToInt

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    context: Context,
    notificationType: NotificationType,
    updateUiService: UpdateUiService,
    notificationLogger: NotificationLogger,
    listener: SwipeListener,
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val width = 96.dp
    val squareSize = 48.dp
    val swipableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Box(
            modifier = Modifier
                .width(width)
                .swipeable(
                    state = swipableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
                .background(Color.LightGray)
        ) {
            when (swipableState.currentValue) {
                0 -> listener.onSwipeLeft()
                1 -> listener.onSwipeRight()
            }
            Box(
                modifier = Modifier
                    .offset { IntOffset(swipableState.offset.value.roundToInt(), 0) }
                    .size(squareSize)
                    .background(Color.DarkGray))
        }

        Text(
            modifier = Modifier
                .combinedClickable(
                    onClick = {},
                    onLongClick = { }
                ),
            text = "Text field: ${updateUiService.updateText(textState.value.text)}")
        TextField(value = textState.value, onValueChange = { textState.value = it })
        Button(
            onClick = {
                when (notificationType) {
                    NotificationType.TOAST -> {
                        ToastNotification(context).sendNotification(textState.value.text)
                        notificationLogger.logNotification(textState.value.text)
                    }
                    NotificationType.EMAIL -> {
                        EmailNotification().sendNotification(textState.value.text)
                        notificationLogger.logNotification(textState.value.text)
                    }
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Send")
        }
    }
}
