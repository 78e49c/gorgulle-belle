package com.example.gorgullebelle.app.data.api

import android.content.Context
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.data.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ApiRepository {

    private val client = OkHttpClient()
    private val sessions = mutableMapOf<Int, MutableList<String>>()

    private fun getApiKey(context: Context): String {
        return context.getString(R.string.api_key)
    }

    // API'ye mesaj gönderir
    fun sendMessage(context: Context, sessionId: Int, messages: List<Message>, callback: (String) -> Unit) {
        val apiKey = getApiKey(context)
        val url = "https://api.openai.com/v1/chat/completions"

        val messagesJson = messages.joinToString(", ") { "{\"role\": \"${it.role}\", \"content\": \"${it.content}\"}" }

        val json = """
        {
            "model": "gpt-3.5-turbo",
            "messages": [
                $messagesJson
            ],
            "stream": false
        }
    """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = json.toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback("Network Error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    callback(handleError(response))
                    return
                }

                response.body?.let { responseBody ->
                    val responseString = responseBody.string()
                    val content = parseJsonResponse(responseString)
                    sessions[sessionId]?.add("""{"role": "assistant", "content": "$content"}""")
                    callback(content)
                } ?: run {
                    callback("Error: Empty response body")
                }
            }
        })
    }

    // API'ye senkron mesaj gönderir
    suspend fun sendChatRequest(json: String): String {
        return withContext(Dispatchers.IO) {
            val url = "https://api.openai.com/v1/chat/completions"
            val apiKey = "YOUR_API_KEY_HERE" // Bu değeri config dosyasından veya güvenli bir yerden alın.

            val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer $apiKey")
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                response.body?.string() ?: "Empty response"
            } else {
                "Error: ${response.message}"
            }
        }
    }

    fun parseJsonResponse(responseString: String): String {
        return try {
            val jsonResponse = JSONObject(responseString)
            val choices = jsonResponse.getJSONArray("choices")

            if (choices.length() > 0) {
                choices.getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content").trim()
            } else {
                "Error: No choices found in response"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error parsing response: ${e.message}"
        }
    }

    fun handleError(response: Response): String {
        return "HTTP Error: ${response.code} - ${response.message}"
    }
}
