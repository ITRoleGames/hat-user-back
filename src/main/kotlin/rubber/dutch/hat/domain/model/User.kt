package rubber.dutch.hat.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "hat_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @Column(name = "access_token")
    val accessToken: String,

    @Column(nullable = false)
    val name: String
)
