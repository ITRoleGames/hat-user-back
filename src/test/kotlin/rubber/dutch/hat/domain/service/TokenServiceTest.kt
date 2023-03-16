package rubber.dutch.hat.domain.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class TokenServiceTest {
    private val configProperties =
        ConfigProperties("E5J5rFgqJ#3XFzadeth535t4efdbt67i86yrfv234", 600000)
    private val tokenService = TokenService(configProperties)

    @Test
    fun `generate token success`() {
        val id = UUID.randomUUID()

        val actual = tokenService.generate(id)

        Assertions.assertEquals(id, actual.userId)
        Assertions.assertEquals(false, actual.expired)
    }

    @Test
    fun `decode token success`() {
        val id = UUID.randomUUID()
        val token = tokenService.generate(id).token

        val response = tokenService.decode(token)

        Assertions.assertEquals(token, response.token)
        Assertions.assertEquals(id, response.userId)
        Assertions.assertEquals(false, response.expired)
    }

    @Test
    fun `decode token expired true`() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2ODI0Njg3ZC1kYWE1LTRmNDAtYWEwMy0yYjNjMDVlOWEwNzgiLCJpYXQiOjE2Nz" +
            "UxNzAzNzksImV4cCI6MTY3NTE3MDM4OX0.D_Hx38VUp92ggTQmnMx5YM4Ko4NJPnprfDUJN_Wh6CM"

        val tokenDto = tokenService.decode(token)

        Assertions.assertTrue(tokenDto.expired)
    }
}
