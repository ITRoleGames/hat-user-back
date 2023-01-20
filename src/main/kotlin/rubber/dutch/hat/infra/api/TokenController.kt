package rubber.dutch.hat.infra.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import rubber.dutch.hat.app.dto.TokenDtoResponse
import rubber.dutch.hat.domain.service.TokenService

/**
 * Контроллер вызовов связанных с JWT токенами.
 */
@RestController
class TokenController(private val tokenService: TokenService) {

    /**
     * Получает на вход JWT токен и отдает данные включающие ID пользователя.
     */
    @GetMapping("/api/v1/tokens/{token}")
    fun getToken(@PathVariable token: String): TokenDtoResponse {

        return tokenService.decode(token)
    }
}
