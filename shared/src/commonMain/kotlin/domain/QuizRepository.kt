package domain

interface QuizRepository {

    suspend fun getQuiz(): Quiz?
}