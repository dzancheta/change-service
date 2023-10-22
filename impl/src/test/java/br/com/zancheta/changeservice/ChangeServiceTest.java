package br.com.zancheta.changeservice;

import br.com.zancheta.changeservice.exception.BillException;
import br.com.zancheta.changeservice.properties.ChangeProperties;
import br.com.zancheta.changeservice.stub.CoinsInventoryStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/application.yml")
@ContextConfiguration(classes = {ChangeService.class})
class ChangeServiceTest {
    @MockBean
    private ChangeProperties changeProperties;

    @Autowired
    private ChangeService changeService;

    @BeforeEach
    public void setUp() {
        Mockito.when(changeProperties.getInitializeCoin()).thenReturn(100);
    }

    @Test
    @Disabled
    @DisplayName("should be return change value")
    void getChange() {
        //give
        var expected = CoinsInventoryStub.getInventory();
        //when
        var serviceResponse = changeService.getChange(BigDecimal.ONE.setScale(2));
        //then
        StepVerifier.create(serviceResponse)
                .assertNext(response -> Assertions.assertEquals(expected, response))
                .verifyComplete();
    }

    @Test
    @DisplayName("should be return bad request")
    void giveReturnBadRequest() {
        //give
        final var expected = new BillException();

        //when
        var response = changeService.getChange(BigDecimal.valueOf(13));
        //then

        StepVerifier.create(response)
                .expectErrorSatisfies(throwable -> {
                    final var exception = (BillException) throwable;
                    Assertions.assertEquals(expected.getMessage(), exception.getMessage());
                })
                .verify();

    }

}