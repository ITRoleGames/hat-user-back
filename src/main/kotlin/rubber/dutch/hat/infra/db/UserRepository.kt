package rubber.dutch.hat.infra.db

import org.springframework.data.jpa.repository.JpaRepository
import rubber.dutch.hat.domain.model.User

interface UserRepository: JpaRepository<User, Long> {
    fun findByAccessToken(accessToken: String): User?
}