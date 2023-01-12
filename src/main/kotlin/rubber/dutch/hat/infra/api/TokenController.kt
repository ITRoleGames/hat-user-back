package rubber.dutch.hat.infra.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * Контроллер вызовов связанных с JWT токенами.
 */
@RestController
class TokenController {

    /**
     * Получает на вход JWT токен и отдает данные включающие ID пользователя.
     */
    @GetMapping("/api/v1/tokens/{token}")
    fun getToken(@PathVariable token: String): TokenDTO {

//        throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return TokenDTO(token, UUID.randomUUID())
    }

    data class TokenDTO(val token: String, val userId: UUID)
}
