package com.example.market.present.ui.shared.component

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.market.R
import com.example.market.present.theme.LightColorScheme
import com.example.market.present.theme.Surface01
import com.example.market.present.utils.extension.bottomBorder
import com.example.market.present.utils.extension.noRippleClickable

@Preview
@Composable
fun PreviewTopTabBackBtn() {
    TopTabBackBtn(
        text = "지출"
    )
}


@Composable
fun TopTabBackBtn(
    modifier: Modifier = Modifier,
    text: String = "",
    mainColor: Color = Color.Black,
    toBackScreen: () -> Unit = {},
) {
    Column {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val (textComponent, backBtn) = createRefs()

            Image(
                painter = painterResource(R.drawable.icon_arrow_left),
                contentDescription = R.drawable.icon_arrow_left.toString(),
                colorFilter = ColorFilter.tint(color = Surface01),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .noRippleClickable { toBackScreen() }
            )

            Text(
                text = text,
                color = Surface01,
                modifier = Modifier
                    .constrainAs(textComponent) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(backBtn.end, 10.dp)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = mainColor
        )
    }
}