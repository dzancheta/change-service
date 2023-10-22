package br.com.zancheta.changeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ChangeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChangeServiceApplication.class, args);
    }

}
