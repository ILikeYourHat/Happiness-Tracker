package io.github.ilikeyourhat.happinesstracker.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PieChart(
    entries: List<PieChartData>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val startAngles = calculateStartAngles(entries)
        val total = entries.sumOf { it.value }.toFloat()
        entries.forEachIndexed { index, entry ->
            drawArc(
                color = entry.color,
                startAngle = startAngles[index],
                sweepAngle = entry.value / total * FULL_CIRCLE,
                useCenter = true,
                topLeft = Offset.Zero,
                size = this.size
            )
        }
    }
}

fun calculateStartAngles(entries: List<PieChartData>): List<Float> {
    var totalPercentage = 0f
    val total = entries.sumOf { it.value }.toFloat()
    return entries.map { entry ->
        val startAngle = totalPercentage * FULL_CIRCLE + START_ROTATION
        totalPercentage += entry.value / total
        startAngle
    }
}

const val FULL_CIRCLE = 360f
const val START_ROTATION = -90f

@Preview
@Composable
fun PieChartPreview() {
    val data = listOf(
        PieChartData("A", 30, Color.Red),
        PieChartData("B", 20, Color.Green),
        PieChartData("C", 50, Color.Blue),
    )
    PieChart(
        entries = data,
        modifier = Modifier.size(200.dp)
    )
}
