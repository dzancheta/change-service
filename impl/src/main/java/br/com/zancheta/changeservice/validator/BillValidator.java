package br.com.zancheta.changeservice.validator;

import br.com.zancheta.changeservice.enuns.BillEnum;
import br.com.zancheta.changeservice.exception.BillException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;

public class BillValidator {
    public static boolean validator(BigDecimal value) {
        return Arrays.stream(BillEnum.values())
                .anyMatch(coin -> coin.getValue().equals(value));
    }
}
