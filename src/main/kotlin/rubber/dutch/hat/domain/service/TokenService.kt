package rubber.dutch.hat.domain.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import rubber.dutch.hat.app.dto.TokenDtoResponse
import java.security.Key
import java.time.Instant
import java.util.*

@Component
class TokenService(private val config: ConfigProperties) {
    private val key: Key = Keys.hmacShaKeyFor(config.secretKey.toByteArray())

    fun generate(id: UUID): TokenDtoResponse {
        val token =
                Jwts.builder()
                        .setId(id.toString())
                        .setIssuedAt(Date.from(Instant.now()))
                        .setExpiration(Date.from(Instant.now().plusSeconds(config.expiration)))
                        .signWith(key)
                        .compact()
        return TokenDtoResponse(
                token = token,
                expired = false,
                userId = id
        )
    }

    fun decode(token: String): TokenDtoResponse {
        val claims: Jws<Claims> = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        return TokenDtoResponse(
                token = token,
                isExpired(claims.body.expiration.time),
                userId = UUID.fromString(claims.body.id)
        )
    }

    private fun isExpired(expirationTime: Long): Boolean {
        return expirationTime < Instant.now().toEpochMilli()
    }
}