package di

import dev.icerock.moko.mvvm.compose.ViewModelFactory
import dev.icerock.moko.mvvm.compose.viewModelFactory
import presentation.QuizViewModel

object ViewModelModule {

    val quizViewModelFactory: ViewModelFactory<QuizViewModel> by lazy {
        viewModelFactory {
            QuizViewModel(getQuiz = UseCaseModule.getQuizUseCase)
        }
    }
}