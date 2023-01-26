package rubber.dutch.hat.infra.api

import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import rubber.dutch.hat.BaseApplicationTest
import rubber.dutch.hat.app.dto.UserResponseWithSecurityInfo
import rubber.dutch.hat.app.dto.UsersResponse
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
        val createUserResponse = callCreateUser().andReturn().response
        val createdUser: UserResponseWithSecurityInfo = objectMapper.readValue(createUserResponse.contentAsString)

        val createUserResponse2 = callCreateUser().andReturn().response
        val createdUser2: UserResponseWithSecurityInfo = objectMapper.readValue(createUserResponse2.contentAsString)

        val getUsersResponse = callGetUsers(listOf(createdUser.id, createdUser2.id), createdUser.id)
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }.andReturn().response

        val usersResponse: UsersResponse = objectMapper.readValue(getUsersResponse.contentAsString)
        Assertions.assertNotNull(usersResponse.users)
        Assertions.assertEquals(2, usersResponse.users.size)
        Assertions.assertTrue(usersResponse.users.any { it.id == createdUser.id && it.name == createdUser.name })
        Assertions.assertTrue(usersResponse.users.any { it.id == createdUser2.id && it.name == createdUser2.name })
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