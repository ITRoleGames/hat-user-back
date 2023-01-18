package rubber.dutch.hat.app

import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.UserResponse
import rubber.dutch.hat.app.dto.toResponse
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.service.UserProvider
import java.util.*

@Component
class GetUsersUsecase(private val userProvider: UserProvider) {

    fun execute(ids: List<UUID>): List<UserResponse> {
        return userProvider.findByIds(ids).map(User::toResponse)
    }
}