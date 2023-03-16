package rubber.dutch.hat.domain.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "\"user\"",
    indexes = [Index(name = "access_token_idx", columnList = "access_token")]
)
class User(
    @Id
    @Column(name = "id", nullable = false)
    override val id: UUID,

    @Column(name = "access_token", nullable = false)
    var accessToken: String,

    @Column(name = "name", nullable = false)
    val name: String
) : BaseEntity<UUID>()
