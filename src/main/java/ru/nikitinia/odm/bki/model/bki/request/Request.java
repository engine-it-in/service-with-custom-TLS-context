package ru.nikitinia.odm.bki.model.bki.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
        name = "request",
        description = "Rest модель запроса"
)
public record Request(

) {
}
