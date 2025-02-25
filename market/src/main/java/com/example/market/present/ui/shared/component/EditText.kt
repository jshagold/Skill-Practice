package com.example.market.present.ui.shared.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun EditText(
    inputText: MutableState<String>,
    placeholder: String = "",
    textColor: Color = Black,
    placeholderColor: Color = Gray,
    backgroundColor: Color = White,
    shape: Shape = RoundedCornerShape(5.dp),
    borderSize: Dp = 1.dp,
    borderColor: Color = Gray,
    paddingVertical: Dp = 0.dp,
    paddingHorizontal: Dp = 0.dp,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = inputText.value,
        textStyle = TextStyle(
            color = textColor
        ),
        onValueChange = { inputText.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.then(modifier)
    ) { innerTextField ->
        ConstraintLayout (
            modifier = Modifier
                .background(backgroundColor, shape)
                .border(borderSize, borderColor, shape)
                .padding(
                    vertical = paddingVertical,
                    horizontal = paddingHorizontal,
                )
        ) {
            innerTextField()

            if(inputText.value.isEmpty()) {
                Text(
                    text = placeholder,
                    color = placeholderColor,
                    modifier = Modifier
                )
            }
        }
    }
}