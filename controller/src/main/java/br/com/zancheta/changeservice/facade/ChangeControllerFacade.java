package br.com.zancheta.changeservice.facade;

import br.com.zancheta.changeservice.ChangeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

@Component
@AllArgsConstructor
public class ChangeControllerFacade {
    private ChangeService changeService;

    public Mono<Map<BigDecimal, Integer>> getChange(Double value) {
        var copyValue = new BigDecimal(value).setScale(2);
        return changeService.getChange(copyValue);
    }
}
