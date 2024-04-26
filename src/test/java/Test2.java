import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Test2 {

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
            driver.close();
        }
    }

    @Test
    public void checkingImageInModalWindow() {
        WebElement jpg = driver.findElement(By.cssSelector("[data-id='id-3']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(jpg).click().build().perform();
        Assertions.assertFalse(driver.findElements(By.id("pp_full_res")).isEmpty());

    }
}
