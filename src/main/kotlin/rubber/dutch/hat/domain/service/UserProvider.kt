package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserFinder
import java.util.*

@Component
class UserProvider(private val userFinder: UserFinder) {
    fun findById(id: UUID): User? {
        return userFinder.findById(id)
    }

    fun findByIds(ids: List<UUID>): List<User> {
        return userFinder.findByIds(ids)
    }

    fun existsByName(name: String): Boolean {
        return userFinder.existsByName(name)
    }
}