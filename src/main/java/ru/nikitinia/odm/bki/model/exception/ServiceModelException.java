package ru.nikitinia.odm.bki.model.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(
        name = "error",
        description = "Ошибки обработки в сервисе"
)
public record ServiceModelException (

        @Schema(
                name = "id",
                description = "Идентификатор ошибки",
                type = "String",
                example = "123",
                minLength = 255,
                maxLength = 255,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String id,

        @Schema(
                name = "source",
                description = "Источник ошибки",
                type = "String",
                example = "Equifax",
                minLength = 255,
                maxLength = 255,
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        String source,

        @Schema(
                name = "errors",
                description = "Список ошибок обработки",
                type = "List<Error>"
        )
        List<ServiceModelError> errors

) {


}

