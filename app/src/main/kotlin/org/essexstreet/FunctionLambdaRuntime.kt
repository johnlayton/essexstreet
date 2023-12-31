package org.essexstreet

import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.micronaut.function.aws.runtime.AbstractMicronautLambdaRuntime
import java.net.MalformedURLException

class FunctionLambdaRuntime : AbstractMicronautLambdaRuntime<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent, APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>() {

    override fun createRequestHandler(vararg args: String?): RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
        return FunctionRequestHandler()
    }

    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            try {
                FunctionLambdaRuntime().run(*args)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
        }
    }
}
