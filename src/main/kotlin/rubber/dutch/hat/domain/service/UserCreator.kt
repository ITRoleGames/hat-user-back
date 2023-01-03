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
            accessToken = generateToken(), //TODO: выяснить что с этим делать
            name = nameGenerator.generateName()
        )
        return userSaver.save(user)
    }

    private fun generateUuid(): UUID {
        return UUID.randomUUID()
    }

    private fun generateToken(): String {
        return generateUuid().toString().replace("-", "")
    }
}