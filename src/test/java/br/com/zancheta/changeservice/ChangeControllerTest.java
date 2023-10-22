package br.com.zancheta.changeservice;

import br.com.zancheta.changeservice.facade.ChangeControllerFacade;
import br.com.zancheta.changeservice.response.ChangeResponse;
import br.com.zancheta.changeservice.stub.CoinsInventoryStub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@MockBean(classes = {ChangeControllerFacade.class})
@WebFluxTest(controllers = ChangeController.class)
public class ChangeControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebTestClient webTestClient;
    @MockBean
    ChangeControllerFacade changeControllerFacade;

    @Test
    @DisplayName("should be return change value")
    void shouldReturn200WhenCallAdhesion() throws JsonProcessingException {
        Mockito.when(changeControllerFacade.getChange(1D))
                .thenReturn(Mono.just(CoinsInventoryStub.getInventory()));

        final var expectedJson = objectMapper.writeValueAsString(new ChangeResponse(CoinsInventoryStub.getInventory()));

        webTestClient
                .get()
                .uri("/v1/change/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedJson);
    }

    @Test()
    @DisplayName("should be return change value")
    void shouldReturn400WhenCallAdhesion() {
        var path = "12change";
        webTestClient
                .get()
                .uri("/v1/change/".concat(path))
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

}
