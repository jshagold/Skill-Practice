package com.example.market.present.ui.category.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.market.R

@Composable
fun CategoryTab(
    text: String,
    onClickBtn: () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        val (textComponent, deleteBtn) = createRefs()

        Text(
            text = text,
            modifier = Modifier
                .constrainAs(textComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(R.drawable.icon_close),
            contentDescription = "icon_close",
            colorFilter = ColorFilter.tint(Color.Red),
            modifier = Modifier
                .clickable {
                    onClickBtn()
                }
                .constrainAs(deleteBtn) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
    }

}