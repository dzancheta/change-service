package br.com.zancheta.changeservice;

import br.com.zancheta.changeservice.enuns.CoinEnum;
import br.com.zancheta.changeservice.exception.BillException;
import br.com.zancheta.changeservice.exception.InsufficientCoinsException;
import br.com.zancheta.changeservice.properties.ChangeProperties;
import br.com.zancheta.changeservice.validator.BillValidator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ChangeService {
    private final Map<BigDecimal, Integer> coinsInventory;
    private final ChangeProperties changeProperties;

    public ChangeService(ChangeProperties changeProperties) {
        this.changeProperties = changeProperties;
        this.coinsInventory = new HashMap<>();
        Arrays.stream(CoinEnum.values())
                .forEach(coin -> this.coinsInventory.put(coin.getValue(), changeProperties.getInitializeCoin()));
    }

    public Mono<Map<BigDecimal, Integer>> getChange(BigDecimal amount) {
        if (!BillValidator.validator(amount)) {
            return Mono.error(new BillException());
        }

        Map<BigDecimal, Integer> changeCoins = new TreeMap<>((a, b) -> b.compareTo(a));
        BigDecimal remainingChange = amount;

        for (Map.Entry<BigDecimal, Integer> entry : coinsInventory.entrySet()) {
            var coinValue = entry.getKey();
            var coinCount = entry.getValue();

            if (remainingChange.compareTo(BigDecimal.ZERO) > 0 && coinValue.compareTo(remainingChange) <= 0 && coinCount > 0) {
                var numberOfCoinsToUse = remainingChange.divide(coinValue, 0, BigDecimal.ROUND_DOWN).intValue();
                numberOfCoinsToUse = Math.min(numberOfCoinsToUse, coinCount);

                changeCoins.put(coinValue, numberOfCoinsToUse);

                remainingChange = remainingChange.subtract(coinValue.multiply(BigDecimal.valueOf(numberOfCoinsToUse)));
                coinsInventory.put(coinValue, coinCount - numberOfCoinsToUse);
            }
        }

        if (remainingChange.compareTo(BigDecimal.ZERO) == 0) {
            return Mono.just(changeCoins);
        } else {
            return Mono.error(new InsufficientCoinsException());
        }
    }
}



