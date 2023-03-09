package rubber.dutch.hat.app.dto

import io.swagger.v3.oas.annotations.media.Schema
import rubber.dutch.hat.domain.model.User
import java.util.*

data class UserResponseWithSecurityInfo(
    @field:Schema(description = "ID созданного пользователя")
    override val id: UUID,
    @field:Schema(description = "Рандомно сгенерированное имя")
    override val name: String,
    @field:Schema(description = "JWT токен")
    val accessToken: String
) : AbstractUserResponse

fun User.toResponseWithSecurityInfo(): UserResponseWithSecurityInfo =
    UserResponseWithSecurityInfo(id = id, name = name, accessToken = accessToken)
