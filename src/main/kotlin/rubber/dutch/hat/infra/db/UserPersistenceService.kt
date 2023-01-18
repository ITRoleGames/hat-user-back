package rubber.dutch.hat.infra.db

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserFinder
import rubber.dutch.hat.domain.port.UserSaver
import java.util.*

@Component
class UserPersistenceService(
    private val userRepository: UserRepository
): UserSaver, UserFinder {

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun findById(id: UUID): User {
        return userRepository.getReferenceById(id)
    }

    override fun findByIds(ids: List<UUID>): List<User> {
        return userRepository.findAllById(ids)
    }

    override fun existsByName(name: String): Boolean {
        return userRepository.existsByName(name)
    }

}