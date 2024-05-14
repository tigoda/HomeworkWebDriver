import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ImageInModalWindowTest {

//    Открыть Chrome в режиме киоска
//    Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
//    Нажать на любую картинку
//    Проверить что картинка открылась в модальном окне


    private WebDriver driver;


    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
//    Открыть Chrome в режиме киоска
        driver.manage().window().getSize();
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public boolean waitToCondition(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    @Test
    public void checkingImageInModalWindow() {

        Boolean beforeClick = waitToCondition(By.cssSelector("[class='pp_expand']"));
        System.out.println(beforeClick);

        WebElement jpg = driver.findElement(By.cssSelector("[data-id ='id-3']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(jpg).click().build().perform();
        Boolean afterClick = waitToCondition(By.cssSelector("[class='pp_expand']"));
        System.out.println(afterClick);
        Assertions.assertNotEquals(beforeClick, afterClick);

    }
}


