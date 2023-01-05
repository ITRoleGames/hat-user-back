package rubber.dutch.hat.app.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class UserResponse(
    @field:Schema(description = "id созданного пользователя")
    val id: UUID,

    @field:Schema(description = "Сохраняется в local-storage для последующих запросов")
    val accessToken: UUID,

    @field:Schema(description = "Рандомно сгенерированное имя")
    val name: String
)
