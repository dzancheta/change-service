package br.com.zancheta.changeservice.confg;


import br.com.zancheta.changeservice.exception.GenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class ReactiveControllerAdviceConfig {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(GenericException.class)
    public Mono<Void> handleYourSpecificException(GenericException ex, ServerWebExchange exchange) {
        var response = exchange.getResponse();
        response.setStatusCode(ex.getStatus());
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);

        List<String> details = Collections.singletonList(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), details);
        try {
            var dataBuffer =
                    response.bufferFactory().allocateBuffer().write(objectMapper.writeValueAsBytes(errorResponse));
            return response.writeWith(Mono.just(dataBuffer));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    public Mono<Void> handleGenericException(Exception ex, ServerWebExchange exchange) {
        var response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        response.setStatusCode(Optional.ofNullable((ServerWebInputException) ex)
                .map(ErrorResponseException::getStatusCode).orElse(null));

        List<String> details = Collections.singletonList(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), details);
        try {
            var dataBuffer =
                    response.bufferFactory().allocateBuffer().write(objectMapper.writeValueAsBytes(errorResponse));
            return response.writeWith(Mono.just(dataBuffer));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
