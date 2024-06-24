package ru.nikitinia.odm.bki.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitinia.odm.bki.model.bki.request.Request;
import ru.nikitinia.odm.bki.model.bki.response.Response;
import ru.nikitinia.odm.bki.model.exception.ServiceModelException;
import ru.nikitinia.odm.bki.service.BKIIncomeService;

@Slf4j
@RestController
@RequestMapping("/equifax")
@Tag(name = "Сервис получения данных по доходам")
@RequiredArgsConstructor
public class ServiceWithCustomTLSContextController {

    private final BKIIncomeService service;

    @Operation(description = "Получить данные о доходе из БКИ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка при выполнении запроса",
                    content = @Content(schema = @Schema(implementation = ServiceModelException.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ошибка валидации входных параметров",
                    content = @Content(schema = @Schema(implementation = ServiceModelException.class))),
            @ApiResponse(
                    responseCode = "422",
                    description = "Ошибка обработки запроса",
                    content = @Content(schema = @Schema(implementation = ServiceModelException.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка на стороне сервера",
                    content = @Content(schema = @Schema(implementation = ServiceModelException.class))),
            @ApiResponse(
                    responseCode = "511",
                    description = "Ошибка при аутентификации",
                    content = @Content(schema = @Schema(implementation = ServiceModelException.class))),
    })
    @PostMapping(
            value = "/income-cache",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Response processResponse(@RequestBody Request request) {
        log.info("Request income: {}", request);
        return service.processResponse(request);
    }

}