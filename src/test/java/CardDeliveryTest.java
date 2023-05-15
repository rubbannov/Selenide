import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void testCardDeliveryBlank() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x("//*[@data-test-id='city']//input[@placeholder='Город']").setValue("Волгоград");
        $x("//*[@data-test-id='date']//input[@placeholder='Дата встречи']").clear();
        $x("//*[@data-test-id='date']//input[@placeholder='Дата встречи']").setValue("19052023");
        $x("//*[@data-test-id='name']//input[@name='name']").setValue("Вася Пупкин");
        $x("//*[@data-test-id='phone']//input[@type='tel']").setValue("+79990999900");
        $x("//*[@data-test-id='agreement']").click();
        $$("[type='button']").filter(visible).last().click();
        $x("//*[contains(text(), 'Успешно')]").shouldBe(visible, Duration.ofSeconds(11));


    }
}
