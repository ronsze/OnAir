package kr.sdbk.network.interceptor

import android.util.Log
import jakarta.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class CurlLoggingInterceptor @Inject constructor() : Interceptor {
    companion object {
        private const val TAG = "CURL"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val curlCommand = buildString {
            append("curl -X ${request.method}")

            request.headers.forEach { header ->
                append(" -H \"")
                append(header.first)
                append(": ")
                append(header.second)
                append("\"")
            }

            request.body?.let { body ->
                val buffer = okio.Buffer()
                body.writeTo(buffer)
                val bodyString = buffer.readUtf8().replace("\"", "\\\"")
                if (bodyString.isNotBlank()) {
                    append(" --data-raw \"")
                    append(bodyString)
                    append("\"")
                }
            }

            append(" \"")
            append(request.url)
            append("\"")
        }

        Log.d(TAG, curlCommand)

        return chain.proceed(request)
    }
}
