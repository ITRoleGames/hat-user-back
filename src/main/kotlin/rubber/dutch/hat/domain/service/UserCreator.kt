package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserSaver
import java.util.*

@Component
class UserCreator(
    private val nameGenerator: NameGenerator,
    private val userSaver: UserSaver
) {

    fun createUser(): User {
        val user = User(
            userId = generateUuid(),
            accessToken = generateUuid(),
            name = nameGenerator.generateName()
        )
        return userSaver.save(user)
    }

    private fun generateUuid(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
}