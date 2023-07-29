package util

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

object Caller {

    suspend inline fun <reified T> safeNetworkCall(call: () -> HttpResponse): T? {
        return try {
            val response = call.invoke()
            if (response.status.isSuccess()) {
                response.body<T>()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}