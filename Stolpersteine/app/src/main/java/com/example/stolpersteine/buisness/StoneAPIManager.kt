import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.stolpersteine.models.Stone
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import org.jsoup.Jsoup

class StoneAPIManager(private val context: Context) {

    private val jsonFileName = "stones.json"
    private val apiUrl = "https://api.struikelstenengids.nl/v2/export/stones?secret=yONOtKGoGO9u9O8pC247jKl9NcDxEl54C2b8N06nzgF9WR6S1I"

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }

    private var lastResponse: List<Stone> = emptyList()

    fun getStonesByLocation(location: String, limit: Int): List<Stone> {
        val stones = loadStonesFromJson()

        return stones.filter { it.city == location }.take(limit)
    }

    fun getStoneOfTheDay(): Stone? {
        val stones = loadStonesFromJson()
        return stones.randomOrNull()
    }

    fun getLastResponse(): List<Stone> {
        return lastResponse
    }

    fun updateStonesFromAPI() {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            apiUrl,
            null,
            { response ->
                Log.d("API_RESPONSE", response.toString())
                val stones = mutableListOf<Stone>()
                for (i in 0 until response.length()) {
                    val stoneJson = response.getJSONObject(i)
                    val stone = Gson().fromJson(stoneJson.toString(), Stone::class.java)
                    stones.add(stone)
                }

                lastResponse = stones
                saveStonesToJson(stones, "stones.json", context) // or use appropriate context
            },
            { error ->
                // Handle error
                Log.e("API_ERROR", error.toString())
            }
        )

        // Set the retry policy for the request because the response
        // of the provided api is at least 8 seconds
        jsonArrayRequest.retryPolicy = DefaultRetryPolicy(
            11000, // Timeout duration in milliseconds
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the request to the request queue and start the queue
        requestQueue.add(jsonArrayRequest)
        requestQueue.start()
    }


    private fun loadStonesFromJson(): List<Stone> {
        val json = readJsonFile()
        return if (json.isNotEmpty()) {
            Gson().fromJson(json, object : TypeToken<List<Stone>>() {}.type)
        } else {
            emptyList() // Return an empty list if the JSON is empty or null
        }
    }

    private fun saveStonesToJson(stones: List<Stone>, fileName: String, context: Context) {
        val gson = Gson()
        val jsonString = gson.toJson(stones)

        try {
            val fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(jsonString.toByteArray())
            fileOutputStream.close()
            Log.d("Save Response", "Response saved to $fileName")

            val filePath = "${context.filesDir.path}/$fileName"
            val file = File(filePath)
            val fileExists = file.exists()

            if (fileExists) {
                Log.d("File Check", "The file $fileName exists at path: $filePath")
            } else {
                Log.d("File Check", "The file $fileName does not exist.")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun readJsonFile(): String {
        return try {
            val file = File(context.filesDir, jsonFileName)
            if (!file.exists()) {
                // Create the file if it doesn't exist
                file.createNewFile()
            }
            file.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            // Handle IO exception
            ""
        }
    }


    //Beacuse the api dosn't provide with victim's story we fetch
    // the info from the provided url
    //Think for better solution, not all html tags for the urls are the same....
    fun fetchDescriptionFromUrl(context: Context, url: String, callback: (String?) -> Unit) {
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val document = Jsoup.parse(response)
                val metaDescription = document.selectFirst("meta[name=description]")?.attr("content")
                callback(metaDescription)
            },
            { error ->
                error.printStackTrace()
                callback(null)
            }
        )
        queue.add(request)
        queue.start()
    }

}
