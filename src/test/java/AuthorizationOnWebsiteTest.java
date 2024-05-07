import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthorizationOnWebsiteTest {

//    Открыть Chrome в режиме полного экрана
//    Перейти на https://otus.ru
//    Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
//    Вывести в лог все cookie

    private final static Logger logger = LogManager.getLogger(AuthorizationOnWebsiteTest.class);
    private WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://otus.ru");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.close();
        }
        logger.trace("Закрытие браузера -  завершено");
    }

    @Test
    public void authorizationOnOtus() {
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
        driver.findElement(By.xpath("//div[./input[@name='email']]")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("*****");
        driver.findElement(By.xpath("//div[./input[@type='password']]")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("*****");
        driver.findElement(By.xpath("//button[./*[text()='Войти']]")).click();

        Assertions.assertEquals("//span[text()='Кристина']", "//span[text()='Кристина']");

        logger.trace(driver.manage().getCookies());
    }
}
