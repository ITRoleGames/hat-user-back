package rubber.dutch.hat.infra.api

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import rubber.dutch.hat.BaseApplicationTest

internal class VersionControllerTest : BaseApplicationTest() {

    @Test
    fun `WHEN get version THAN success`() {
        mockMvc.get("/api/v1/version")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("version") { value("1.0.0") }
            }
    }
}
