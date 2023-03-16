package com.tronina.proxy.modelDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Error description")
public class ExceptionDto {
    @Schema(description = "Error message")
    private String message;
    @Schema(description = "HTTP-status code")
    private int status;
}
