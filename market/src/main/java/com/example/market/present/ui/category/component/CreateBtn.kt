package com.example.market.present.ui.category.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.market.R
import com.example.market.present.utils.extension.noRippleClickable
import com.example.market.present.theme.PrimaryGreen


@Composable
fun CreateBtn(
    modifier: Modifier = Modifier,
    onClickBtn: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(color = PrimaryGreen, shape = CircleShape)
            .size(75.dp)
            .noRippleClickable {
                onClickBtn()
            }
    ) {
        Image(
            painter = painterResource(R.drawable.icon_plus),
            contentDescription = "icon_plus",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}