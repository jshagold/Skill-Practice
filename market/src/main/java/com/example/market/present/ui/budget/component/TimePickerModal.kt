package com.example.market.present.ui.budget.component

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.time.LocalDateTime

@Preview
@Composable
fun PreviewTimePickerModal() {
    TimePickerModal(onConfirm = {}, onDismiss = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerModal(
    initialLocalDateTime: LocalDateTime? = null,
    onConfirm: (dateTime: LocalDateTime) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = initialLocalDateTime ?: LocalDateTime.now()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.hour,
        initialMinute = currentTime.minute,
        is24Hour = true,
    )

    BasicAlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(),
        modifier = Modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(5.dp))
                .padding(10.dp)
        ) {
            TimePicker(
                state = timePickerState,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = onDismiss) {
                    Text("cancel")
                }
                Button(onClick = {
                    onConfirm(
                        LocalDateTime.of(currentTime.year, currentTime.month, currentTime.dayOfMonth, timePickerState.hour, timePickerState.minute)
                    )
                }) {
                    Text("ok")
                }
            }
        }
    }

}