package domain

data class Quiz(
    val responseCode: Int? = null,
    val results: List<Question>? = null
) {
    data class Question(
        val category: String? = null,
        val type: String? = null,
        val difficulty: String? = null,
        val question: String? = null,
        val correctAnswer: String? = null,
        val incorrectAnswers: List<String>? = null
    )
}
