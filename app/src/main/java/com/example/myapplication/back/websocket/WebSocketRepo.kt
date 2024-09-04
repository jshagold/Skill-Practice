package com.example.myapplication.back.websocket

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Inject


class WebSocketRepo @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    private lateinit var websocket: WebSocket

    fun init() {
        val request = Request.Builder()
            .url("")
            .build()

        websocket = okHttpClient.newWebSocket(request, WSListener())

    }

    fun sendMessage(text: String) {
        websocket.send(text)
    }
}