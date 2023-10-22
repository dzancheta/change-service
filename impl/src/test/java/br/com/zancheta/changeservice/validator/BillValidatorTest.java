package br.com.zancheta.changeservice.validator;

import br.com.zancheta.changeservice.exception.BillException;
import br.com.zancheta.changeservice.exception.GenericException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

class BillValidatorTest {
    @Test
    void shouldBeReturnFalse() {
        //given
        var value = BigDecimal.valueOf(13);

        //then
        Assertions.assertFalse(BillValidator
                .validator(value));
    }

    @Test
    void shouldBeReturnTrue() {
        //given
        var value = BigDecimal.valueOf(10).setScale(2);

        //then
        Assertions.assertTrue(BillValidator
                .validator(value));
    }
}