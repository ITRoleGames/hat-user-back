package rubber.dutch.hat.domain.port

import rubber.dutch.hat.domain.model.User

interface UserSaver {
    fun save(user: User): User
}