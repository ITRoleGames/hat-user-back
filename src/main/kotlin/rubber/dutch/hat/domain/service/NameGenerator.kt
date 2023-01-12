package rubber.dutch.hat.domain.service

import org.springframework.stereotype.Component
import rubber.dutch.hat.domain.port.UserFinder
import kotlin.random.Random

@Component
class NameGenerator(private val userFinder: UserFinder) {
    //Для того случая если игроков будет больше чем доступных слов
    //Когда будет честная генерация это можно будет удалить
    var maxCheck = 3
    val generatedNames = listOf("Активированный Угорь",
        "Алхимический Металлист",
        "Анатомический Нонсенс",
        "Андед-Мороз",
        "Антигерой",
        "Антропоморфный Дендромутант",
        "Асексуальный Маньяк",
        "Барон Суббота",
        "Бетонный Элементаль",
        "Биполярный Медведь",
        "Бледный Йорик",
        "Бобрыня Некитчев",
        "Бриллиантовый Диадемон",
        "Будён Неладен",
        "Вдомах",
        "Вежливый Лось",
        "Веселиск",
        "Вестник Абокралипсиса",
        "Весь Покрытый Зеленью",
        "Вождь Апачей",
        "Вуглускр",
        "Гамадрил-шалун",
        "Гамильтонов Гриф",
        "Героеукладчик",
        "Гипножаб",
        "Гипнокот",
        "Главгад",
        "Гневнух",
        "Господин ПэЖэ",
        "Гребубля",
        "Гриболикий Хромоног",
        "Гриф Секретности",
        "Двоичный Кот",
        "Двухсотлѣтнiй Монстръ",
        "Дипломант Натрия",
        "Дияблос Вульгарис",
        "Длиннокот",
        "Дождевой Шаи-Хулудик",
        "Дом На Драконьих Ножках",
        "Дракон-бюрократ",
        "Едкий Сатир",
        "Желатиновый Тетраэдр",
        "Жутконос",
        "Злон",
        "Зомби В Куртке «Избранный №13»",
        "Квестоносец",
        "Книжный Червь",
        "Конёк-рагнарёк",
        "Корейский Рандом",
        "Красный Кровяной Телец")

    fun generateName(): String {
        return checkForDublicateName(
            generatedNames.get(Random.nextInt(0, generatedNames.size)))
    }

    fun checkForDublicateName(name: String): String {
        if (userFinder.existsByName(name) && maxCheck > 0) {
            maxCheck--
            return generateName()
        }
        maxCheck = 3
        return name
    }
}