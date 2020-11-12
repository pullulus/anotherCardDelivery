package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @Test
    void shouldChangeTheDate() {
        open("http://localhost:9999");
        $("[placeholder= 'Город']").setValue(DataHelper.generateCityName());
        $("[placeholder= 'Дата встречи']").doubleClick();
        $("[placeholder= 'Дата встречи']").sendKeys(Keys.chord(Keys.BACK_SPACE));
        $("[placeholder= 'Дата встречи']").setValue(DataHelper.getDateOfMeeting(4));
        $("[name= 'name']").setValue(DataHelper.getUsersData("ru").getName());
        $("[name= 'phone']").setValue(DataHelper.getUsersData("ru").getPhone());
        $(".checkbox__box").click();
        $(byText("Запланировать")).click();
        $(withText("Встреча успешно запланирована на")).waitUntil(visible, 7000).shouldHave(text(DataHelper.getDateOfMeeting(4)));
        $("[placeholder= 'Дата встречи']").doubleClick();
        $("[placeholder= 'Дата встречи']").sendKeys(Keys.chord(Keys.BACK_SPACE));
        $("[placeholder= 'Дата встречи']").setValue(DataHelper.getDateOfMeeting(6));
        $(byText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату")).shouldHave(text("Перепланировать?"));
        $(byText("Перепланировать")).click();
        $(withText("Встреча успешно запланирована на")).shouldHave(text(DataHelper.getDateOfMeeting(6)));
    }
}
