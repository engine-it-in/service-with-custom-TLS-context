package ru.nikitinia.odm.bki.model.bki.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
        name = "response",
        description = "Rest модель ответа "
)
public record Response(

) {
}
