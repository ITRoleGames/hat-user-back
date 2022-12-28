package rubber.dutch.hat.app

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.UserResponse
import rubber.dutch.hat.domain.exception.AuthorizationHeaderNotFoundException
import rubber.dutch.hat.domain.exception.UserNotFoundException
import rubber.dutch.hat.domain.service.UserProvider

@Component
class FindUserUsecase(
    private val userProvider: UserProvider
) {
    fun execute(headers: HttpHeaders): UserResponse {
        val accessToken = headers.get(HttpHeaders.AUTHORIZATION)?.get(0)?.replace("Bearer ", "")
            ?: throw AuthorizationHeaderNotFoundException()
        val user = userProvider.findByAccessToken(accessToken)
            ?: throw UserNotFoundException()

        return UserResponse(
            id = user.userId,
            accessToken = user.accessToken,
            name = user.name
        )
    }
}