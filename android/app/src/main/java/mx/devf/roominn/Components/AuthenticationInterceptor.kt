package mx.devf.roominn.Components

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Luis Rios on 08/11/2017.
 */
class AuthenticationInterceptor(private val authToken: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
                .header("x-access-token", authToken)
                //.header("Authorization", authToken)

        val request = builder.build()
        return chain.proceed(request)
    }
}