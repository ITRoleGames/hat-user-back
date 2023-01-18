package rubber.dutch.hat.app

import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.AbstractUserResponse
import rubber.dutch.hat.app.dto.toResponseWithSecurityInfo
import rubber.dutch.hat.domain.service.UserCreator

@Component
class CreateUserUsecase(
        private val userCreator: UserCreator
) {
    fun execute(): AbstractUserResponse {
        return userCreator.createUser().toResponseWithSecurityInfo()
    }
}