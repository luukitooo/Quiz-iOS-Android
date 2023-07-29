package presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.GetQuiz
import domain.Quiz
import kotlinx.coroutines.launch

class QuizViewModel(
    private val getQuiz: GetQuiz,
) : ViewModel() {

    init { refreshQuiz() }

    var currentQuestionState by mutableStateOf(UIQuestion())
        private set

    var isQuizFinishedState by mutableStateOf(false)
        private set

    var progressState by mutableStateOf(ProgressInfo())
        private set

    private var currentQuestionIndex = -1

    var correctAnswersCount = 0
        private set

    private var quiz: Quiz? = null

    private suspend fun fetchQuiz() {
        quiz = getQuiz.execute()
    }

    fun getNextQuestion(wasPreviousAnswerCorrect: Boolean) {
        if (wasPreviousAnswerCorrect) {
            correctAnswersCount++
        }
        currentQuestionIndex++
        if (currentQuestionIndex >= (quiz?.results?.lastIndex ?: 0)) {
            isQuizFinishedState = true
            return
        }
        currentQuestionState = UIQuestion(
            text = quiz?.results?.get(currentQuestionIndex)?.question ?: "",
            answers = quiz?.results?.get(currentQuestionIndex)
                ?.incorrectAnswers
                ?.map {
                    UIQuestion.Answer(
                        text = it,
                        isCorrect = false
                    )
                }
                ?.toMutableList()
                .apply {
                    this?.add(
                        UIQuestion.Answer(
                            text = quiz?.results?.get(currentQuestionIndex)?.correctAnswer ?: "",
                            isCorrect = true
                        )
                    )
                }?.shuffled() ?: emptyList()
        )
        updateProgress(quiz?.results ?: emptyList())
    }

    private fun updateProgress(questions: List<Quiz.Question>) {
        progressState = ProgressInfo(
            currentValue = currentQuestionIndex + 1,
            maxValue = questions.lastIndex
        )
    }

    fun refreshQuiz() = viewModelScope.launch {
        currentQuestionState = UIQuestion()
        progressState = ProgressInfo()
        correctAnswersCount = 0
        currentQuestionIndex = -1
        isQuizFinishedState = false
        fetchQuiz()
        getNextQuestion(wasPreviousAnswerCorrect = false)
    }
}

data class UIQuestion(
    val text: String = "",
    val answers: List<Answer> = emptyList(),
) {
    data class Answer(
        val text: String,
        val isCorrect: Boolean,
    )

    fun isEmpty(): Boolean {
        return text.isBlank() || answers.isEmpty()
    }
}

data class ProgressInfo(
    val currentValue: Int = 0,
    val maxValue: Int = 100
)