package rubber.dutch.hat.domain.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.jupiter.api.Test
import java.util.*

class TokenServiceTest {
    private val configProperties =
        ConfigProperties("E5J5rFgqJ#3XFzadeth535t4efdbt67i86yrfv234", 600000)
    private  var tokenService = TokenService(configProperties)

    @Test
    fun `generate token success`() {
        val id = UUID.randomUUID()

        val actual = tokenService.generate(id)

        assertNotNull(actual.userId)
        assertNotNull(actual.expired)
        assertNotNull(actual.token)

        assertEquals(actual.userId, id)
        assertEquals(actual.expired, false)
    }

    @Test
    fun `decode token success`() {
        val id = UUID.randomUUID()
        val token = tokenService.generate(id).token

        val response = tokenService.decode(token)

        assertNotNull(response.token)
        assertNotNull(response.userId)
        assertNotNull(response.expired)

        assertEquals(response.token, token)
        assertEquals(response.userId, id)
        assertEquals(response.expired, false)
    }
}
