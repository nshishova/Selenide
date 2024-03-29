package ru.netology.selenide;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendFormTest {

    private String genLocalData(int extDays, String formatData) {
        return LocalDate.now().plusDays(extDays).format(DateTimeFormatter.ofPattern(formatData));
    }

    @Test
    public void shouldSendFormPositiveData() {
        open("http://localhost:9999 ");
        $("[data-test-id='city'] input").setValue("Архангельск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.DELETE);
        String planDate = genLocalData(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").setValue(planDate);
        $("[data-test-id='name'] input").setValue("Мария-Антуанетта Австрийская");
        $("[data-test-id='phone'] input").setValue("+79112223334");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + planDate));


    }

}
