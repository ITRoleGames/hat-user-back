package rubber.dutch.hat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HatUserBackApplication

fun main(args: Array<String>) {
    runApplication<HatUserBackApplication>(*args)
}
