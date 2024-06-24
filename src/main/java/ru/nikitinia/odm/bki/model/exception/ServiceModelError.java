package ru.nikitinia.odm.bki.model.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
        name = "error",
        description = "Ошибка обработки"
)
public record ServiceModelError(
        @Schema(
                name = "code",
                description = "Код ошибки",
                type = "String",
                example = "421",
                minLength = 1,
                maxLength = 3,
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        String code,

        @Schema(
                name = "message",
                description = "Validation fields is failed",
                type = "String",
                example = "String",
                minLength = 1,
                maxLength = 255,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String message

) {
}