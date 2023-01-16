package rubber.dutch.hat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan
class HatUserBackApplication{

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(HatUserBackApplication::class.java, *args)
        }
    }
}
