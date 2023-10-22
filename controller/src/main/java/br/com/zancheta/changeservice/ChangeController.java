package br.com.zancheta.changeservice;


import br.com.zancheta.changeservice.facade.ChangeControllerFacade;
import br.com.zancheta.changeservice.response.ChangeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1/change")
@Tag(name = "change controller", description = "change controller")
public class ChangeController {
    private ChangeControllerFacade changeControllerFacade;

    @GetMapping(path = "/{value}")
    @Operation(
            method = "GET",
            description = "Get change",
            summary = "Return change",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get change",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChangeResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal error",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public Mono<ChangeResponse> getChange(@NonNull @PathVariable @Pattern(regexp = "^\\d+$", message = "Invalid value for client") Double value) {
        return changeControllerFacade.getChange(value)
                .map(ChangeResponse::new);
    }
}
