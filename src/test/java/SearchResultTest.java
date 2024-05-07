
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SearchResultTest {

//    Открыть Chrome в headless режиме
//    Перейти на https://duckduckgo.com/
//    В поисковую строку ввести ОТУС
//    Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение современным ...

    private WebDriver driver;


    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.get("https://duckduckgo.com");
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkingSearchResult() {
        WebElement inputRequest = driver.findElement
                (By.cssSelector("[placeholder = 'Поиск без отслеживания']"));
        inputRequest.click();
        inputRequest.sendKeys("ОТУС");
        driver.findElement(By.cssSelector("[aria-label='Search']")).click();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...",
                "Онлайн‑курсы для профессионалов, дистанционное обучение современным ..."
        );
    }
}

