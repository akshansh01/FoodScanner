package com.acstudio.foodscanner

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


object NetworkAPI {
    private val client = OkHttpClient()

   suspend fun run(urlLink: String, myCallback: (String) -> Unit) {
        withContext(Dispatchers.IO) {
            val request = Request.Builder().url(urlLink).build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code/ result")
                }

                for ((name, value) in response.headers) {
                    println("$name : $value")
                }
                myCallback.invoke(response.body!!.string())
            }
        }
    }
}