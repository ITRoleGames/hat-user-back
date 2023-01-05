package rubber.dutch.hat.infra.api.dto

data class ErrorResponse(
    val errorCode: ErrorCode
)

enum class ErrorCode {
    USER_NOT_FOUND,
    AUTHORIZATION_HEADER_NOT_FOUND,
    INVALID_ACCESS_TOKEN
}
