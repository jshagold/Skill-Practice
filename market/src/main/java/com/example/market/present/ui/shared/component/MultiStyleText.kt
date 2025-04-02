package com.example.market.present.ui.shared.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewMultiStyleText() {
    MultiStyleText(
        textWithStyles = listOf()
    )
}



@Composable
fun MultiStyleText(
    modifier: Modifier = Modifier,
    textWithStyles: List<Pair<String, SpanStyle>>,
) {
    Text(
        text = buildAnnotatedString {
            textWithStyles.forEach {
                withStyle(style = it.second) {
                    append(it.first)
                }
            }
        },
        modifier = modifier
    )
}