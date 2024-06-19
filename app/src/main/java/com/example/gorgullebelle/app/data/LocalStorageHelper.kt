package com.example.gorgullebelle.app.data

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LocalStorageHelper {
    private const val PREFERENCES_NAME = "chat_preferences"
    private const val KEY_SESSIONS = "sessions"

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
}
