package br.com.zancheta.changeservice.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum CoinEnum {
    PENNY(BigDecimal.valueOf(0.01)),
    NICKEL(BigDecimal.valueOf(0.05)),
    DIME(BigDecimal.valueOf(0.10)),
    QUARTER(BigDecimal.valueOf(0.25));

    private final BigDecimal value;


}
