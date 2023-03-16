package rubber.dutch.hat.domain.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("app.jwt")
class ConfigProperties @ConstructorBinding constructor(val secretKey: String, val expiration: Long)
