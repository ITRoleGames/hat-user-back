package rubber.dutch.hat.app

import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.UserResponse
import rubber.dutch.hat.domain.service.UserCreator

@Component
class CreateUserUsecase(
    private val userCreator: UserCreator
) {
    fun execute(): UserResponse {
        val user = userCreator.createUser()

        return UserResponse(
            id = user.userId,
            accessToken = user.accessToken,
            name = user.name
        )
    }
}