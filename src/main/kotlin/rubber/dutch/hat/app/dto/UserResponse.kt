package rubber.dutch.hat.app.dto

import io.swagger.v3.oas.annotations.media.Schema

data class UserResponse(
    @field:Schema(description = "id созданного пользователя")
    val id: String,

    @field:Schema(description = "Сохраняется в local-storage для последующих запросов")
    val accessToken: String,

    @field:Schema(description = "Рандомно сгенерированное имя")
    val name: String
)
