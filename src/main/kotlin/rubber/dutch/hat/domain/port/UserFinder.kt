package rubber.dutch.hat.domain.port

import rubber.dutch.hat.domain.model.User
import java.util.UUID

interface UserFinder {
    fun findByAccessToken(accessToken: UUID): User?
}