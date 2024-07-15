package org.essexstreet;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.Map;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

/*
    @Get
    public Map<String, Object> index() {
        return Collections.singletonMap("message", "Hello World");
    }
*/

    @Post
    public Map<String, Object> index(@Valid @Body Card card) {
        LOGGER.info("card: {}", card);

        return Collections.singletonMap("message", "Hello World");
    }
}
