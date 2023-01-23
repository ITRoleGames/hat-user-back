package rubber.dutch.hat.infra.api

import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import rubber.dutch.hat.domain.exception.AuthorizationHeaderNotFoundException
import rubber.dutch.hat.domain.exception.UserNotFoundException
import rubber.dutch.hat.infra.api.dto.ErrorCode
import rubber.dutch.hat.infra.api.dto.ErrorResponse

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class)
    fun currentUserNotFoundError(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.unprocessableEntity().body(ErrorResponse(ErrorCode.USER_NOT_FOUND))
    }

    @ExceptionHandler(AuthorizationHeaderNotFoundException::class)
    fun authHeaderNotFoundError(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse(ErrorCode.AUTHORIZATION_HEADER_NOT_FOUND))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun invalidAccessTokenError(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse(ErrorCode.INVALID_ACCESS_TOKEN))
    }

    @ExceptionHandler(value = [MethodArgumentTypeMismatchException::class, ValidationException::class])
    fun handleValidationException(ex: Any): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse(ErrorCode.BAD_REQUEST))
    }
}