package rubber.dutch.hat.infra.api

import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import rubber.dutch.hat.BaseApplicationTest
import rubber.dutch.hat.app.dto.UserResponseWithSecurityInfo
import java.util.*

internal class UserControllerTest : BaseApplicationTest() {

    @Test
    fun `create user success`() {
        callCreateUser()
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("id") { isNotEmpty() }
                    jsonPath("name") { isNotEmpty() }
                    jsonPath("accessToken") { isNotEmpty() }
                }
    }

    @Test
    fun `get users success`() {
        val mockResponse = callCreateUser().andReturn().response
        val createUserResponse: UserResponseWithSecurityInfo = objectMapper.readValue(mockResponse.contentAsString)

        callGetUsers(listOf(createUserResponse.id), createUserResponse.id)
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
    }

    @Test
    fun `get current user success`() {
        val mockResponse = callCreateUser().andReturn().response
        val createUserResponse: UserResponseWithSecurityInfo = objectMapper.readValue(mockResponse.contentAsString)

        callGetCurrentUser(createUserResponse.id)
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("id") { isNotEmpty() }
                    jsonPath("name") { isNotEmpty() }
                    jsonPath("accessToken") { isNotEmpty() }
                }
    }

    private fun callCreateUser(): ResultActionsDsl {
        return mockMvc.post("/api/v1/users") {
            contentType = MediaType.APPLICATION_JSON
        }
    }

    private fun callGetCurrentUser(userId: UUID): ResultActionsDsl {
        return mockMvc.get("/api/v1/user/current") {
            header("user-id", userId)
        }
    }

    private fun callGetUsers(ids: List<UUID>, userId: UUID): ResultActionsDsl {
        return mockMvc.get("/api/v1/users?ids=${ids.joinToString(separator = ",")}") {
            header("user-id", userId)
        }
    }
}