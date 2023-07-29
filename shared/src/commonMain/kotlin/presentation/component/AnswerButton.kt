package presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import presentation.UIQuestion
import presentation.style.AppColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnswerButton(
    modifier: Modifier = Modifier,
    answer: UIQuestion.Answer,
    onClick: UIQuestion.Answer.() -> Unit,
) {

    val buttonDelay = 400L

    val scope = rememberCoroutineScope()

    var buttonColor by remember {
        mutableStateOf(AppColors.Yellow)
    }

    Card(
        modifier = modifier,
        onClick = {
            scope.launch {
                buttonColor = if (answer.isCorrect) {
                    AppColors.Green
                } else {
                    AppColors.Red
                }
                delay(buttonDelay)
                buttonColor = AppColors.Yellow
                onClick.invoke(answer)
            }
        },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = buttonColor
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = answer.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}