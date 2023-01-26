package rubber.dutch.hat.app

import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.UserResponseWithSecurityInfo
import rubber.dutch.hat.app.dto.toResponseWithSecurityInfo
import rubber.dutch.hat.domain.exception.UserNotFoundException
import rubber.dutch.hat.domain.service.UserProvider
import java.util.*

@Component
class GetCurrentUserUsecase(
        private val userProvider: UserProvider
) {
    fun execute(id: UUID): UserResponseWithSecurityInfo {
        val user = userProvider.findById(id)
                ?: throw UserNotFoundException()

        return user.toResponseWithSecurityInfo()
    }
}