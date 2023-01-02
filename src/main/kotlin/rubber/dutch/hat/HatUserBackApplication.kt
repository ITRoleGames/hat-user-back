package rubber.dutch.hat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class HatUserBackApplication{

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(HatUserBackApplication::class.java, *args)
        }
    }
}
