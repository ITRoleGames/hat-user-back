package rubber.dutch.hat.app

import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.UserResponse
import rubber.dutch.hat.domain.exception.UserNotFoundException
import rubber.dutch.hat.domain.service.UserProvider
import java.util.*

@Component
class FindUserUsecase(
    private val userProvider: UserProvider
) {
    fun execute(accessToken: UUID): UserResponse {
        val user = userProvider.findByAccessToken(accessToken)
            ?: throw UserNotFoundException()

        return UserResponse(
            id = user.userId,
            accessToken = user.accessToken,
            name = user.name
        )
    }
}