package data

import util.Caller
import domain.Quiz
import domain.QuizRepository
import io.ktor.client.request.get

class KtorQuizRepository : QuizRepository {

    override suspend fun getQuiz(): Quiz? {
        return Caller.safeNetworkCall<QuizDto?> {
            KtorClient.instance.get(QUIZ_ENDPOINT)
        }?.toQuiz()
    }

    companion object {
        private const val QUIZ_ENDPOINT = "https://opentdb.com/api.php?amount=10&category=17&difficulty=easy"
    }
}