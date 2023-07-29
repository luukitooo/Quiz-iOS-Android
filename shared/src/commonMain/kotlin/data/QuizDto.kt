package data

import domain.Quiz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizDto(
    @SerialName("response_code")
    val responseCode: Int? = null,
    val results: List<QuestionDto>? = null
) {
    @Serializable
    data class QuestionDto(
        val category: String? = null,
        val type: String? = null,
        val difficulty: String? = null,
        val question: String? = null,
        @SerialName("correct_answer")
        val correctAnswer: String? = null,
        @SerialName("incorrect_answers")
        val incorrectAnswers: List<String>? = null
    )
}

fun QuizDto.QuestionDto.toQuestion() = Quiz.Question(
    category = category,
    type = type,
    difficulty = difficulty,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers
)

fun QuizDto.toQuiz() = Quiz(
    responseCode = this.responseCode,
    results = this.results?.map { it.toQuestion() }
)