package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserSaver
import java.util.*

@Component
class UserCreator(
        private val nameGenerator: NameGenerator,
        private val userSaver: UserSaver,
        private val tokenService: TokenService
) {

    fun createUser(): User {
        val userId = UUID.randomUUID()
        val user = User(
                id = userId,
                accessToken = tokenService.generate(userId).token,
                name = nameGenerator.generateName()
        )
        return userSaver.save(user)
    }
}