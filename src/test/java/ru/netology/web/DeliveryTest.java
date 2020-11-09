package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @Test
    void shouldChangeTheDate() {
        open("http://localhost:9999");
        $("[placeholder= 'Город']").setValue(DataHelper.getNameOfTheCity("ru");
        $("[placeholder= 'Дата встречи']").doubleClick();
        $("[placeholder= 'Дата встречи']").sendKeys(Keys.chord(Keys.BACK_SPACE));
        String dateOfMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder= 'Дата встречи']").setValue(dateOfMeeting);
        $("[name= 'name']").setValue("Надежда Крупская");
        $("[name= 'phone']").setValue("+75553332223");
        $(".checkbox__box").click();
        $(byText("Запланировать")).click();
        $$(".notification__content").first().shouldHave(text("Встреча успешно запланирована на " + dateOfMeeting));
        $("[placeholder= 'Дата встречи']").doubleClick();
        $("[placeholder= 'Дата встречи']").sendKeys(Keys.chord(Keys.BACK_SPACE));
        String newDateOfMeeting = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder= 'Дата встречи']").setValue(newDateOfMeeting);
        $(byText("Запланировать")).click();
        $$(".notification__content").get(1).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $(byText("Перепланировать")).click();
        $$(".notification__content").first().shouldHave(text("Встреча успешно запланирована на " + newDateOfMeeting));
    }
}
