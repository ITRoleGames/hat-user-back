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
    fun execute(id: UUID): UserResponse {
        val user = userProvider.findById(id)
            ?: throw UserNotFoundException()

        return UserResponse(
            id = user.id!!,
            accessToken = user.accessToken!!,
            name = user.name
        )
    }
}