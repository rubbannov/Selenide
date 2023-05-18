import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    public String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    @Test
    void testCardDeliveryBlank() {
        String planningDate = generateDate(4);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x("//*[@data-test-id='city']//input[@placeholder='Город']").setValue("Волгоград");
        $x("//*[@data-test-id='date']//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input[@placeholder='Дата встречи']").setValue(planningDate);
        $x("//*[@data-test-id='name']//input[@name='name']").setValue("Вася Пупкин");
        $x("//*[@data-test-id='phone']//input[@type='tel']").setValue("+79990999900");
        $x("//*[@data-test-id='agreement']").click();
        $$("[type='button']").filter(visible).last().click();
        $x("//*[contains(text(), 'Успешно')]").shouldBe(visible, Duration.ofSeconds(11));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);


    }
}
