package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserFinder
import java.util.UUID

@Component
class UserProvider(
    private val userFinder: UserFinder
) {
    fun findByAccessToken(accessToken: UUID): User? {
        return userFinder.findByAccessToken(accessToken)
    }
}