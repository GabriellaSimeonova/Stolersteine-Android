package com.example.stolpersteine


import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

@Composable
fun ApiConnector(text: String) {
    val result = remember { mutableStateOf("") }

//    DisposableEffect(Unit) {
//        tellStory(text) { response ->
//            result.value = response
//        }
//
//        onDispose {
//            // Cancel any ongoing API requests if needed
//        }
//    }
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = Color(0xFFfff2e9)
//    ) {
//        // Use the result value in your UI
//        Text(text = result.value)
//    }
}


fun tellStory(text: String, callback: (String) -> Unit) {

    val client = OkHttpClient()
    val text = text


    val apiKey = "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"
    val url = "https://api.openai.com/v1/engines/text-davinci-003/completions"

    val requestBody = """
    {"prompt": "${text}",
    "max_tokens": 1000,
    "temperature": 0}
""".trimIndent()


    val request = Request.Builder()
        .url(url)
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization", "Bearer $apiKey")
        .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("error", "API failed", e)
        }





        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            if (body != null) {
                Log.v("data", body)
                try {
                    val jsonObject = JSONObject(body)
                    val jsonArray: JSONArray = jsonObject.getJSONArray("choices")
                    val textResult = jsonArray.getJSONObject(0).getString("text")
                    callback(textResult)
                } catch (e: JSONException) {
                    Log.e("error", "Error parsing JSON response", e)
                    callback("") // Invoke the callback with an empty string or appropriate error handling
                }
            } else {
                Log.v("data", "empty")
                callback("") // Invoke the callback with an empty string or appropriate error handling
            }
        }

    })
}