package rubber.dutch.hat.app.dto

import io.swagger.v3.oas.annotations.media.Schema
import rubber.dutch.hat.domain.model.User
import java.util.*

data class UserResponse(
        @field:Schema(description = "ID созданного пользователя")
        override val id: UUID,
        @field:Schema(description = "Рандомно сгенерированное имя")
        override val name: String
) : AbstractUserResponse()

fun User.toResponse(): UserResponse = UserResponse(id = id, name = name)