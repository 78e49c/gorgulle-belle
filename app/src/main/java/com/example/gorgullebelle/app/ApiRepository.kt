package com.example.gorgullebelle.app

import android.content.Context
import com.example.gorgullebelle.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class ApiRepository {

    private val client = OkHttpClient()
    private val sessions = mutableMapOf<Int, MutableList<String>>()

    private fun getApiKey(context: Context): String {
        return context.getString(R.string.api_key)
    }

    fun sendMessage(context: Context, sessionId: Int, message: String, callback: (String) -> Unit) {
        val apiKey = getApiKey(context)
        val url = "https://api.openai.com/v1/chat/completions"

        val messages = sessions.getOrPut(sessionId) { mutableListOf() }
        messages.add("{\"role\": \"user\", \"content\": \"${message.replace("\"", "\\\"")}\"}")

        val messagesJson = messages.joinToString(", ")

        val json = """
            {
                "model": "gpt-3.5-turbo",
                "messages": [
                    {"role": "system", "content": "You are a teacher, never say you are an AI."},
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
                callback("Error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    callback("Error: ${response.message}")
                    return
                }

                response.body?.let { responseBody ->
                    val responseString = responseBody.string()
                    try {
                        val jsonResponse = JSONObject(responseString)
                        val choices = jsonResponse.getJSONArray("choices")
                        if (choices.length() > 0) {
                            val content = choices.getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content")
                            sessions[sessionId]?.add("{\"role\": \"assistant\", \"content\": \"$content\"}")
                            callback(content.trim())
                        } else {
                            callback("Error: No choices found in response")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        callback("Error parsing response: ${e.message}")
                    }
                } ?: run {
                    callback("Error: Empty response body")
                }
            }
        })
    }
}
