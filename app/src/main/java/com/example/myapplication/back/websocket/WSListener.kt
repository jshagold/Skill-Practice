package com.example.myapplication.back.websocket

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WSListener : WebSocketListener() {


    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)

        // checksum
        println(bytes)
    }

    // 메세지 수신 처리
    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        // 웹소켓으로부터 문자열 메세지를 받음
        println(text)
    }

    // 연결 실패
    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        println(response)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        // 연결이 종료되기 전에 호출
        println()
    }


    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        // 연결이 완전히 종료된 후 호출
        println()
    }
}