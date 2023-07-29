package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    currentValue: Int = 0,
    maxValue: Int = 100,
    colorGradient: List<Color> = listOf(
        MaterialTheme.colors.primary,
        MaterialTheme.colors.primary
    ),
) {

    Row(
        modifier = modifier
            .height(8.dp)
            .background(MaterialTheme.colors.background)
    ) {
        if (currentValue != 0) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(brush = Brush.linearGradient(colorGradient))
                    .weight(currentValue.toFloat())
            )
        }
        if (maxValue - currentValue != 0) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(maxValue.toFloat() - currentValue.toFloat())
            )
        }
    }
}