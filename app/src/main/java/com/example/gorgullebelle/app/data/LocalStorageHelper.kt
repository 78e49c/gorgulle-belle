package com.example.gorgullebelle.app.data

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LocalStorageHelper {
    private const val PREFERENCES_NAME = "chat_preferences"
    private const val KEY_SESSIONS = "sessions"
    private const val KEY_SCORE = "score"
    private const val KEY_TOPICS = "topics"

    // Oturumları JSON formatında kaydeder.
    fun saveSessions(context: Context, sessions: Map<Int, List<String>>) {
        val json = Gson().toJson(sessions)
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(KEY_SESSIONS, json)
        }
        Log.d("LocalStorageHelper", "Oturumlar kaydedildi: $sessions")
    }

    // Kaydedilmiş oturumları JSON formatından geri yükler.
    fun loadSessions(context: Context): Map<Int, List<String>> {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(KEY_SESSIONS, null) ?: return emptyMap()
        val type = object : TypeToken<Map<Int, List<String>>>() {}.type
        val sessions = Gson().fromJson<Map<Int, List<String>>>(json, type)
        Log.d("LocalStorageHelper", "Oturumlar yüklendi: $sessions")
        return sessions
    }

    // Skoru kaydeder.
    fun saveScore(context: Context, score: Int) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putInt(KEY_SCORE, score)
        }
        Log.d("LocalStorageHelper", "Skor kaydedildi: $score")
    }

    // Skoru yükler.
    fun loadScore(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val score = sharedPreferences.getInt(KEY_SCORE, 0)
        Log.d("LocalStorageHelper", "Skor yüklendi: $score")
        return score
    }

    // Konuları kaydeder.
    fun saveTopics(context: Context, topics: List<String>) {
        val json = Gson().toJson(topics)
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(KEY_TOPICS, json)
        }
        Log.d("LocalStorageHelper", "Konular kaydedildi: $topics")
    }

    // Konuları yükler.
    fun loadTopics(context: Context): List<String> {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(KEY_TOPICS, null) ?: return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        val topics = Gson().fromJson<List<String>>(json, type)
        Log.d("LocalStorageHelper", "Konular yüklendi: $topics")
        return topics
    }
}
