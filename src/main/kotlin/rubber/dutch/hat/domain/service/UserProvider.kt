package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.model.User
import rubber.dutch.hat.domain.port.UserFinder

@Component
class UserProvider(
    private val userFinder: UserFinder
) {
    fun findByAccessToken(accessToken: String): User? {
        return userFinder.findByAccessToken(accessToken)
    }
}