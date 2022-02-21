package com.corekcioglu.blocking.exception.translation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class DummyRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithMockUser(roles = {"admin"})
    void rightRole() {
        webTestClient
                .get()
                .uri("http://localhost:8080/dummy")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    // Fails with 500 due to blocking call caught by BlockHound
    @Test
    @WithMockUser(roles = {"admin1"})
    void wrongRole() {
        webTestClient
                .get()
                .uri("http://localhost:8080/dummy")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    // Fails with 500 due to blocking call caught by BlockHound
    @Test
    void noRole() {
        webTestClient
                .get()
                .uri("http://localhost:8080/dummy")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
    }
}