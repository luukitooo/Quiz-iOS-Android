import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import data.KtorQuizRepository
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.ViewModelModule
import domain.GetQuiz
import presentation.QuizScreen
import presentation.QuizViewModel

@Composable
fun App() {
    MaterialTheme {
        val quizViewModel = getViewModel(Unit, ViewModelModule.quizViewModelFactory)
        QuizScreen(quizViewModel)
    }
}

expect fun getPlatformName(): String