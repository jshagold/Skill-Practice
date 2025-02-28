package com.example.market.present.ui.budget.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.market.R
import com.example.market.present.ui.shared.component.EditText
import com.example.market.present.utils.extension.noRippleClickable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun PreviewDatetimeTab() {
    DatetimeTab()
}

@Composable
fun DatetimeTab(
    modifier: Modifier = Modifier,
    inputDate: LocalDateTime? = null,
    mainColor: Color = MaterialTheme.colorScheme.primary,
    onClickTab: () -> Unit = {},
) {

    val density = LocalDensity.current
    var componentHeight by remember { mutableStateOf(0.dp) }
    val isFocusedEditText = remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (labelComponent, contentsComponent) = createRefs()
        val labelGuideLine = createGuidelineFromStart(fraction = 0.2f)

        Text(
            text = stringResource(R.string.datetime_tab_label),
            modifier = Modifier
                .padding(vertical = 10.dp)
                .constrainAs(labelComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(labelGuideLine)
                    width = Dimension.fillToConstraints
                }
                .onGloballyPositioned {
                    componentHeight = with(density) {
                        it.size.height.toDp()
                    }
                }
        )


        ConstraintLayout(
            modifier = Modifier
                .constrainAs(contentsComponent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(labelGuideLine)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .heightIn(min = componentHeight + 20.dp)
                .noRippleClickable {
                    onClickTab()
                }
        ) {
            val (bottomDivider, textComponent) = createRefs()

            if(inputDate == null) {
                Text(
                    text = stringResource(R.string.datetime_tab_label_placeholder),
                    color = MaterialTheme.colorScheme.surfaceDim,
                    modifier = Modifier
                        .constrainAs(textComponent) {
                            top.linkTo(parent.top)
                            bottom.linkTo(bottomDivider.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                )
            } else {
                Text(
                    text = inputDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd (E) a hh:mm")),
                    color = Color.Black,
                    modifier = Modifier
                        .constrainAs(textComponent) {
                            top.linkTo(parent.top)
                            bottom.linkTo(bottomDivider.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                )
            }



            HorizontalDivider(
                thickness = 1.dp,
                color = if(isFocusedEditText.value) mainColor else MaterialTheme.colorScheme.surfaceDim,
                modifier = Modifier
                    .constrainAs(bottomDivider) {
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}