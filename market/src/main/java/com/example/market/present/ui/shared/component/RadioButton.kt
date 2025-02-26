package com.example.market.present.ui.shared.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.market.present.theme.Surface05
import com.example.market.present.theme.Surface06
import com.example.market.present.theme.Surface07
import com.example.market.present.theme.Surface08
import com.example.market.present.utils.extension.noRippleClickable


@Preview
@Composable
fun PreviewRadioButton() {
    RadioButton(
        text = "수입"
    )
}

@Composable
fun RadioButton(
    modifier: Modifier = Modifier,
    text: String = "",
    mainColor: Color = Color.Black,
    isActive: Boolean = false,
    onClick: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if(isActive) mainColor else Surface08,
                shape = RoundedCornerShape(5.dp)
            )
            .background(
                color = if(isActive) Color.White else Surface08,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(5.dp)
            .noRippleClickable {
                onClick()
            }
    ) {
        val textComponent = createRef()

        Text(
            text = text,
            color = if(isActive) mainColor else Surface05,
            modifier = Modifier
                .constrainAs(textComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}