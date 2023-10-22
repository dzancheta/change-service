package br.com.zancheta.changeservice.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ChangeProperties {
    @Value("${initialize.coin}")
    private Integer initializeCoin;
}
