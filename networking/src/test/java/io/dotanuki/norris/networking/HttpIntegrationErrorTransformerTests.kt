package io.dotanuki.norris.networking

import io.dotanuki.burster.using
import io.dotanuki.norris.domain.errors.RemoteServiceIntegrationError
import io.dotanuki.norris.domain.errors.RemoteServiceIntegrationError.ClientOrigin
import io.dotanuki.norris.domain.errors.RemoteServiceIntegrationError.RemoteSystem
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class HttpIntegrationErrorTransformerTests {

    @Test fun `should transform proper throwable into remote integration error`() {

        using<Int, String, RemoteServiceIntegrationError> {

            burst {
                values(418, "Teapot", ClientOrigin)
                values(503, "Internal Server Error", RemoteSystem)
            }

            thenWith { status, message, expected ->
                assertTransformed(
                    from = httpException<Any>(status, message),
                    to = expected,
                    using = HttpIntegrationErrorTransformer
                )
            }
        }
    }

    @Test fun `should propagate any other error`() {
        val otherError = IllegalStateException("Houston, we have a problem!")
        assertTransformed(
            from = otherError,
            to = otherError,
            using = HttpIntegrationErrorTransformer
        )
    }

    private fun <T> httpException(statusCode: Int, errorMessage: String): HttpException {
        val jsonMediaType = "application/json".toMediaTypeOrNull()
        val body = errorMessage.toResponseBody(jsonMediaType)
        return HttpException(Response.error<T>(statusCode, body))
    }
}