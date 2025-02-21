package com.example.market.present.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.market.domain.model.Budget
import com.example.market.present.theme.PieGraphColor1
import com.example.market.present.theme.PieGraphColor2
import com.example.market.present.theme.PieGraphColor3
import com.example.market.present.theme.PieGraphColor4
import com.example.market.present.theme.PrimaryGreen
import com.example.market.present.theme.PrimaryLightBlue


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    HomeScreen(
        modifier = modifier,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val total: Float = 100f
    val budgetList: List<Pair<String, Float>> = listOf(Pair("적금", 45.9f), Pair("주식",15f), Pair("지출", 23f))

    val pieList = budgetList.sortedBy { it.second }.map { pair ->
        Pair(pair.first, pair.second / total * 360f)
    }

    val colorList = listOf(PieGraphColor1, PieGraphColor2, PieGraphColor3, PieGraphColor4)



    Column(
        modifier = modifier
            .fillMaxSize()
    ) {


        Text(text = "${pieList}")



        Canvas(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
        ) {

            var startAngle: Float = -90f
            pieList.forEachIndexed { index, pie ->
                val sweepAngle: Float = startAngle + pie.second

                drawArc(
                    color = colorList[ index % 4 ],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    style = Fill
                )
                startAngle += sweepAngle
            }







        }

    }
}