package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component

@Component
class NameGenerator {
    fun generateName(): String {
        return "Generated Name"
    }
}