package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserSaver

@Component
class UserCreator(
    private val nameGenerator: NameGenerator,
    private val userSaver: UserSaver,
    private val tokenService: TokenService
) {

    fun createUser(): User {
        val user = User(
            name = nameGenerator.generateName()
        )
        val createdUser = userSaver.save(user)
        createdUser.accessToken = tokenService.generate(createdUser.id!!).token

        return userSaver.save(createdUser)
    }
}