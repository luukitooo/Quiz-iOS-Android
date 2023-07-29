package presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.component.AlertDialog
import presentation.component.AnswerButton
import presentation.component.CenterProgressIndicator
import presentation.component.LinearProgressBar
import presentation.style.AppColors

@Composable
fun QuizScreen(viewModel: QuizViewModel) {

    val currentQuestion = viewModel.currentQuestionState

    val isQuizFinished = viewModel.isQuizFinishedState

    val progress = viewModel.progressState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Gray)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = currentQuestion.text,
                fontSize = 22.sp,
                color = Color.White
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(currentQuestion.answers) { answer ->
                AnswerButton(
                    answer = answer,
                    onClick = {
                        viewModel.getNextQuestion(wasPreviousAnswerCorrect = isCorrect)
                    }
                )
            }
        }
        if (!currentQuestion.isEmpty()) {
            Spacer(Modifier.height(16.dp))
            LinearProgressBar(
                modifier = Modifier.height(16.dp).clip(CircleShape),
                currentValue = progress.currentValue,
                maxValue = progress.maxValue,
                colorGradient = listOf(
                    AppColors.Red,
                    AppColors.Yellow
                )
            )
        }
        Spacer(Modifier.height(16.dp))
    }

    if (isQuizFinished && !currentQuestion.isEmpty()) {
        AlertDialog(
            onDismissRequest = { },
            text = "Your score is ${viewModel.correctAnswersCount}",
            positiveButton = {
                TextButton(onClick = viewModel::refreshQuiz) {
                    Text("Restart")
                }
            }
        )
    }

    if (currentQuestion.isEmpty()) {
        CenterProgressIndicator()
    }
}