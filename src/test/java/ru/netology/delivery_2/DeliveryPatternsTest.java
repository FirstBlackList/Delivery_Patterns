package ru.netology.delivery_2;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryPatternsTest {
    private InformationAboutUserData user = DataGenerator.UserRegistration.generateUser("RU");
    private int addDays = 5;
    private int addNewMeetingDays = 10;
    private String dateMeeting = DataGenerator.generateDate(addDays, "dd.MM.yyyy");
    private String newDateMeeting = DataGenerator.generateDate(addNewMeetingDays, "dd.MM.yyyy");

    public static void clearDate() {
        $("[data-test-id=\"date\"] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    public void shouldSuccessfulApplication() {
        $("[data-test-id=\"city\"] input").setValue(user.getCity());
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"name\"] input").setValue(user.getName());
        $("[data-test-id=\"phone\"] input").setValue(user.getPhone());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"success-notification\"]")
                .shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + dateMeeting),
                        Duration.ofSeconds(12));
    }

    @Test
    public void shouldDateOfTheMeetingHasBeenChanged() {
        //Первая часть.
        $("[data-test-id=\"city\"] input").setValue(user.getCity());
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"name\"] input").setValue(user.getName());
        $("[data-test-id=\"phone\"] input").setValue(user.getPhone());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"success-notification\"]")
                .shouldHave(Condition.text
                        ("Успешно! Встреча успешно запланирована на " + dateMeeting), Duration.ofSeconds(12));
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(newDateMeeting);
        $(byText("Запланировать")).click();
        $("[data-test-id=\"replan-notification\"]")
                .shouldHave(Condition.text
                        ("Необходимо подтверждение У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(byText("Перепланировать")).click();
        $("[data-test-id=\"success-notification\"]")
                .shouldHave(Condition.text
                        ("Успешно! Встреча успешно запланирована на " + newDateMeeting), Duration.ofSeconds(12));
    }

    @Test
    public void shouldNameNotSelected() {
        $("[data-test-id=\"city\"] input").setValue(user.getCity());
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"phone\"] input").setValue(user.getPhone());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldPhoneNotSelected() {
        $("[data-test-id=\"city\"] input").setValue(user.getCity());
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"name\"] input").setValue(user.getName());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldDeliveryToTheSelectedCityIsNotAvailable() {
        $("[data-test-id=\"city\"] input").setValue("Moscow");
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"name\"] input").setValue(user.getName());
        $("[data-test-id=\"phone\"] input").setValue(user.getPhone());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void shouldDeliveryToTheSelectedCityIsNotAvailable_plusPoint() {
        $("[data-test-id=\"city\"] input").setValue(user.getCity() + ".");
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"name\"] input").setValue(user.getName());
        $("[data-test-id=\"phone\"] input").setValue(user.getPhone());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNameIncorrect() {

        $("[data-test-id=\"city\"] input").setValue(user.getCity());
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"name\"] input").setValue(user.getName() + " Yes");
        $("[data-test-id=\"phone\"] input").setValue(user.getPhone());
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=\"name\"] .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldCheckBoxIsNotPressed() {

        $("[data-test-id=\"city\"] input").setValue(user.getCity());
        clearDate();
        $("[data-test-id=\"date\"] input").setValue(dateMeeting);
        $("[data-test-id=\"name\"] input").setValue(user.getName());
        $("[data-test-id=\"phone\"] input").setValue(user.getPhone());
        $(byText("Запланировать")).click();
        $x("//label[contains(@class, 'checkbox')]")
                .shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(255, 92, 92, 1)")
                        , cssClass("input_invalid"), visible);
    }
}
