package rubber.dutch.hat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan
@Suppress("UtilityClassWithPublicConstructor")
class HatUserBackApplication {

    companion object {
        @JvmStatic
        @Suppress("SpreadOperator")
        fun main(args: Array<String>) {
            SpringApplication.run(HatUserBackApplication::class.java, *args)
        }
    }
}
