package br.com.zancheta.changeservice.response;

import java.math.BigDecimal;
import java.util.Map;

public record ChangeResponse(Map<BigDecimal, Integer> value) {

}
