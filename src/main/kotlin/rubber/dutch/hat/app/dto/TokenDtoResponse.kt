package rubber.dutch.hat.app.dto

import java.util.*

data class TokenDtoResponse (
    val token: String,
    val expired: Boolean,
    val userId: UUID?
)