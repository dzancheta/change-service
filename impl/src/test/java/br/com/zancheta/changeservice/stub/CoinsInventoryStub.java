package br.com.zancheta.changeservice.stub;

import br.com.zancheta.changeservice.enuns.CoinEnum;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoinsInventoryStub {
    private static final Integer COINS_START = 10;
    public static Map<BigDecimal, Integer> getInventory() {

        Map<BigDecimal, Integer> coinsInventory = new HashMap<>();

        coinsInventory.put(BigDecimal.valueOf(0.01), COINS_START);

        return coinsInventory;
    }
}
