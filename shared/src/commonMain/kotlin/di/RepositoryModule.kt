package di

import data.KtorQuizRepository
import domain.QuizRepository

object RepositoryModule {

    val quizRepository : QuizRepository by lazy {
        KtorQuizRepository()
    }
}