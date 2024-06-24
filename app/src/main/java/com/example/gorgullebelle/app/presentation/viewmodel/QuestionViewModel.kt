package com.example.gorgullebelle.app.presentation.viewmodel
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gorgullebelle.app.data.ApiRepository
import com.example.gorgullebelle.app.data.dataclass.Choice
import com.example.gorgullebelle.app.data.dataclass.Question
import com.example.gorgullebelle.app.data.questionPrompts

class QuestionViewModel : ViewModel() {
    private val _concept = MutableLiveData<String>()
    val concept: LiveData<String> get() = _concept

    private val _apiResponse = MutableLiveData<String>()
    val apiResponse: LiveData<String> get() = _apiResponse

    private val apiRepository = ApiRepository()

    fun setConcept(concept: String) {
        Log.d("ExerciseViewModel", "Setting concept: $concept")
        _concept.value = concept
    }

    fun fetchQuestion(context: Context) {
        val concept = _concept.value ?: run {
            Log.d("ExerciseViewModel", "Concept is null, returning")
            return
        }
        Log.d("ExerciseViewModel", "fetchQuestion called")
        val promptIndex = when (concept) {
            "konuTespiti" -> 0
            "gereklilikTespiti" -> 1
            "uygunEylem" -> 2
            else -> {
                Log.d("ExerciseViewModel", "Invalid concept, returning")
                return
            }
        }

        val promptMessages = questionPrompts[promptIndex]
        Log.d("ExerciseViewModel", "Sending message to API")

        apiRepository.sendMessage(context, 1, promptMessages) { response ->
            Log.d("ExerciseViewModel", "Received response: $response")
            _apiResponse.postValue(response)
        }
    }

    fun parseApiResponse(apiResponse: String): Question? {
        val explanationPattern = Regex("""explanation:\s*"([^"]*)"""", RegexOption.DOT_MATCHES_ALL)
        val mainTextPattern = Regex("""mainText:\s*"([^"]*)"""", RegexOption.DOT_MATCHES_ALL)
        val answerScorePattern = Regex("""answer(\d+):\s*"([^"]*)"\s*score\1:\s*"([^"]*)"""", RegexOption.DOT_MATCHES_ALL)

        val explanationMatch = explanationPattern.find(apiResponse)
        val mainTextMatch = mainTextPattern.find(apiResponse)
        val answerScoreMatches = answerScorePattern.findAll(apiResponse)

        if (explanationMatch != null && mainTextMatch != null && answerScoreMatches.count() == 4) {
            val explanation = explanationMatch.groupValues[1].trim()
            val mainText = mainTextMatch.groupValues[1].trim()
            val choices = answerScoreMatches.map { match ->
                Choice(match.groupValues[2].trim(), match.groupValues[3].trim().toInt())
            }.toList()

            return Question(explanation, mainText, choices)
        }

        return null
    }
}
