package com.challenge.price.infrastructure.input.adapter.rest.resources;

import com.challenge.price.application.input.PriceUseCase;
import com.challenge.price.commons.helper.Constants;
import com.challenge.price.domain.ErrorResponse;
import com.challenge.price.infrastructure.input.adapter.rest.command.response.PriceDto;
import com.challenge.price.infrastructure.input.adapter.rest.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = Constants.APP_SAFE, methods = RequestMethod.GET)
@RequiredArgsConstructor
@Tag(name = "Price Controller", description = "Controlador de precio a aplicar")
public class PriceController {

  private final PriceUseCase useCase;

  private final PriceMapper mapper;

  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))})
  @ApiResponse(responseCode = "400", description = "Bad Request", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
  @Operation(summary = "GetPriceApply", description = "Precio a aplicar")
  @GetMapping("/price")
  public ResponseEntity<PriceDto> getPriceApply(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
      @RequestParam Long productId, @RequestParam Long brandId) {
    return ResponseEntity.ok(mapper.toDto(useCase.getPrice(date, productId, brandId)));
  }
}
