package org.essexstreet

import io.micronaut.function.aws.MicronautRequestHandler
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import io.micronaut.json.JsonMapper;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

class FunctionRequestHandler : MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>() {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Inject
    lateinit var objectMapper: JsonMapper

    override fun execute(input: APIGatewayProxyRequestEvent): APIGatewayProxyResponseEvent {

        log.info("input: {}", input)

        val response = APIGatewayProxyResponseEvent()
        try {
            val json = String(objectMapper.writeValueAsBytes(mapOf("message" to "Hello Melbourne")))
            response.statusCode = 200
            response.body = json
        } catch (e: IOException) {
            response.statusCode = 500
        }
        return response
    }
}
