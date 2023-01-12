package rubber.dutch.hat.infra.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import rubber.dutch.hat.app.CreateUserUsecase
import rubber.dutch.hat.app.FindUserUsecase
import rubber.dutch.hat.app.dto.UserResponse
import rubber.dutch.hat.domain.exception.AuthorizationHeaderNotFoundException
import rubber.dutch.hat.domain.exception.UserNotFoundException
import rubber.dutch.hat.infra.api.dto.ErrorCode
import rubber.dutch.hat.infra.api.dto.ErrorResponse
import java.util.UUID

@RestController
class UserController(
    private val createUserUsecase: CreateUserUsecase,
    private val findUserUsecase: FindUserUsecase
) {

    @Operation(
        summary = "Создание пользователя",
        responses = [
            ApiResponse(
                responseCode = "200", description = "Пользователь создан"),
            ApiResponse(
                responseCode = "422", description = "Бизнес-ошибка"
            )]
    )
    @PostMapping("/api/v1/users")
    fun createUser(): UserResponse {
        return createUserUsecase.execute()
    }

    @Operation(
        summary = "Получение текущего пользователя",
        description = "Этот вызов требуется при перезагрузке странице аутентифицированного пользователя для которого уже получен access-token",
        responses = [
            ApiResponse(
                responseCode = "200", description = "Получен текущий пользователь"),
            ApiResponse(
                responseCode = "422", description = "Бизнес-ошибка"
            )]
    )
    @GetMapping("/api/v1/user/current")
    fun currentUser(@RequestHeader("user-id") id: String): UserResponse {
        return findUserUsecase.execute(UUID.fromString(id))
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun currentUserNotFoundError(): ErrorResponse {
        return ErrorResponse(ErrorCode.USER_NOT_FOUND)
    }

    @ExceptionHandler(AuthorizationHeaderNotFoundException::class)
    fun authHeaderNotFoundError(): ErrorResponse {
        return ErrorResponse(ErrorCode.AUTHORIZATION_HEADER_NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun invalidAccessTokenError(): ErrorResponse {
        return ErrorResponse(ErrorCode.INVALID_ACCESS_TOKEN)
    }
}