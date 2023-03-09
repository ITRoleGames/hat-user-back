package rubber.dutch.hat.domain.port

import rubber.dutch.hat.domain.model.User
import java.util.UUID

interface UserFinder {
    fun findById(id: UUID): User?

    fun findByIds(ids: List<UUID>): List<User>

    fun existsByName(name: String): Boolean
}
