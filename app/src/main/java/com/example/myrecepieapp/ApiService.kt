package com.example.myrecepieapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

private const val API_KEY = "AIzaSyBoRok5xbrzfTwE-WRdx_a-T20r4jRK7l4"

val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(100, TimeUnit.SECONDS)  // Increase connection timeout
    .readTimeout(100, TimeUnit.SECONDS)     // Increase read timeout
    .writeTimeout(100, TimeUnit.SECONDS)    // Increase write timeout
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://generativelanguage.googleapis.com/v1beta/")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = retrofit.create(GeminiService::class.java)

interface GeminiService {
    @Headers("Content-Type: application/json")
    @POST("models/gemini-1.5-pro-002:generateContent?key=$API_KEY")
    suspend fun getRecipe(@Body request: GeminiRequest): GeminiResponse
}
