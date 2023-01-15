package rubber.dutch.hat.infra.db

import org.springframework.data.jpa.repository.JpaRepository
import rubber.dutch.hat.domain.model.User
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
    fun findByAccessToken(accessToken: UUID): User?
    fun existsByName(name: String): Boolean
}