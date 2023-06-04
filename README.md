[![Build status](https://ci.appveyor.com/api/projects/status/bvq3my65os7up273?svg=true)](https://ci.appveyor.com/project/FirstBlackList/delivery-patterns)

# Домашнее задание к занятию «2.3. Patterns»



## Задача №1: заказ доставки карты (изменение даты)

Вам необходимо автоматизировать тестирование новой функции формы заказа доставки карты:

<img width="626" alt="zzzorder" src="https://github.com/FirstBlackList/Delivery_Patterns/assets/120597383/5b68dbc6-5144-4782-ae93-616fb1855f6a">

Требования к содержимому полей, сообщения и другие элементы, по словам заказчика и разработчиков, такие же, они ничего не меняли.

Примечание: личный совет — не забудьте это перепроверить, никому нельзя доверять 😈

Тестируемая функциональность: если заполнить форму повторно теми же данными, за исключением «Даты встречи», то система предложит перепланировать время встречи:

<img width="365" alt="zzzreplan" src="https://github.com/FirstBlackList/Delivery_Patterns/assets/120597383/4b5f085f-eff0-4918-a682-5eb7c795fc49">

После нажатия кнопки «Перепланировать» произойдёт перепланирование встречи:

<img width="365" alt="zzzsucces" src="https://github.com/FirstBlackList/Delivery_Patterns/assets/120597383/eb2f38a9-f0f9-4410-9a81-9b9cec38f290">

**Важно:** в этот раз вы не должны хардкодить данные прямо в тест. Используйте Faker, Lombok, data-классы для группировки нужных полей и утилитный класс-генератор данных — см. пример в презентации. 

Утилитными называют классы, у которых приватный конструктор и статичные методы.

Обратите внимание, что Faker может генерировать не совсем в нужном для вас формате.
