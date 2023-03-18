package com.tronina.proxy.controller;

import com.tronina.proxy.modelDto.SearchItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/queries")
public interface ControllerApi {
    @Operation(summary = "Method to fetch questions which fit the given search string.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of questions",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SearchItemDto.class))
                    })
    })
    @GetMapping
    ResponseEntity<List<SearchItemDto>> search(@Parameter(description = "search string") @NotNull @RequestParam("q") String query);

}
