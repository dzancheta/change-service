package br.com.zancheta.changeservice.stub;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CoinsInventoryStub {
    public static Map<BigDecimal, Integer> getInventory() {
        var PENNY = BigDecimal.valueOf(0.01);

        Map<BigDecimal, Integer> coinsInventory = new HashMap<>();

        coinsInventory.put(PENNY, 10);

        return coinsInventory;
    }
}
