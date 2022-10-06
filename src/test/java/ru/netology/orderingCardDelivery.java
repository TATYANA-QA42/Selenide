package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class orderingCardDelivery {

    @Test
    void fullFilling() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Оренбург");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        LocalDate date = LocalDate.now().plusDays(5);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String datalocal = format.format(date);
        $("[placeholder='Дата встречи']").setValue(datalocal);
        $("[name='name']").setValue("Мишинев Дмитрий");
        $("[name='phone']").setValue("+79235698562");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + datalocal),
                        Duration.ofSeconds(15));
    }
}
