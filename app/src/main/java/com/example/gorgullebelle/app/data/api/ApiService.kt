package com.example.gorgullebelle.app.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("v1/chat/completions")
    suspend fun sendChatRequest(@Body json: String): String
}