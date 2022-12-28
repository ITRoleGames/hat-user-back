package rubber.dutch.hat.domain.port

import rubber.dutch.hat.domain.model.User

interface UserFinder {
    fun findByAccessToken(accessToken: String): User?
}