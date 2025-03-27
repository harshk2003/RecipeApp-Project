package com.example.myrecepieapp

data class GeminiRequest(
    val contents: List<MessageContent>
)

data class MessageContent(
    val parts: List<MessagePart>
)

data class MessagePart(
    val text: String
)

data class GeminiResponse(
    val candidates: List<Candidate>
)

data class Candidate(
    val content: MessageContent
)
