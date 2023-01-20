package rubber.dutch.hat

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
class BaseApplicationTest {

  @Autowired
  lateinit var mockMvc: MockMvc

  @Autowired
  lateinit var objectMapper: ObjectMapper

  companion object {
    @JvmStatic
    private val postgreSQLContainer =
      PostgreSQLContainer("postgres:12-alpine").waitingFor(Wait.defaultWaitStrategy()).apply { start() }

    @DynamicPropertySource
    @JvmStatic
    fun initProperties(registry: DynamicPropertyRegistry) {
      registry.add("spring.datasource.url") { "jdbc:tc:postgresql:12:///${postgreSQLContainer.databaseName}" }
      registry.add("spring.datasource.username") { postgreSQLContainer.username }
      registry.add("spring.datasource.password") { postgreSQLContainer.password }
      registry.add("spring.datasource.driverClassName") { "org.testcontainers.jdbc.ContainerDatabaseDriver" }
    }
  }

}
