package domain

class GetQuiz(private val quizRepository: QuizRepository) {

    suspend fun execute(): Quiz? {
        return quizRepository.getQuiz()
    }
}