package di

import domain.GetQuiz

object UseCaseModule {

    val getQuizUseCase: GetQuiz by lazy {
        GetQuiz(quizRepository = RepositoryModule.quizRepository)
    }
}