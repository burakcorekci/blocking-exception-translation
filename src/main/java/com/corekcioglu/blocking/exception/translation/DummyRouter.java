package com.corekcioglu.blocking.exception.translation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class DummyRouter {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route()
                .GET("/dummy", DummyRouter::getDummy)
                .build();
    }

    private static Mono<ServerResponse> getDummy(final ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue(new Response());
    }

    public record Response(String body) {

        public Response() {
            this("All good");
        }
    }
}
