package rubber.dutch.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    fun something(): TestResponse {
        val response = TestResponse("12345", "TestStr")
        return response
    }
}

data class TestResponse(val id: String, val text: String)