package rubber.dutch.hat.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "\"user\"",
    indexes = [Index(name = "access_token_idx", columnList = "access_token")])
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UuidGenerator
    val id: UUID? = null,

    @Column(name = "access_token")
    var accessToken: String? = null,

    @Column(nullable = false)
    val name: String
)
