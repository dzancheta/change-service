package br.com.zancheta.changeservice.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum BillEnum {
    ONE(BigDecimal.ONE.setScale(2)),
    TWO(BigDecimal.valueOf(2).setScale(2)),
    FIVE(BigDecimal.valueOf(5).setScale(2)),
    TEN(BigDecimal.TEN.setScale(2)),
    TWENTY(BigDecimal.valueOf(20).setScale(2)),
    FIFTY(BigDecimal.valueOf(50).setScale(2)),
    HUNDRED(BigDecimal.valueOf(100).setScale(2));

    private final BigDecimal value;
}
