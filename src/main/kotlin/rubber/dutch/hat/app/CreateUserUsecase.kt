package rubber.dutch.hat.app

import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.UserResponseWithSecurityInfo
import rubber.dutch.hat.app.dto.toResponseWithSecurityInfo
import rubber.dutch.hat.domain.service.UserCreator

@Component
class CreateUserUsecase(private val userCreator: UserCreator) {
    fun execute(): UserResponseWithSecurityInfo {
        return userCreator.createUser().toResponseWithSecurityInfo()
    }
}