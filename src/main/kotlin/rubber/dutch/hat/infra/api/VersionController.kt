package rubber.dutch.hat.infra.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VersionController(@Value("\${application.version}") private val version: String) {

    @GetMapping("/api/v1/version")
    fun getVersion(): VersionInfo {
        return VersionInfo(version)
    }

    data class VersionInfo(val version: String)
}
