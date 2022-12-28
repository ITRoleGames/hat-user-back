package rubber.dutch.hat.infra.db

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserFinder
import rubber.dutch.hat.domain.port.UserSaver

@Component
class UserPersistenceService(
    private val userRepository: UserRepository
): UserSaver, UserFinder {

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun findByAccessToken(accessToken: String): User? {
        return userRepository.findByAccessToken(accessToken)
    }
}