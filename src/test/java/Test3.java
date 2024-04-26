import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test3 {

//    Открыть Chrome в режиме полного экрана
//    Перейти на https://otus.ru
//    Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
//    Вывести в лог все cookie

    private final static Logger logger = LogManager.getLogger(Test3.class);
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
        WebElement butt = driver.findElement(By.xpath("//button[text()='Войти']"));
        butt.click();
        WebElement email = driver.findElement(By.xpath("//div[./input[@name='email']]"));
        email.click();
        email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("*********");
        WebElement pass = driver.findElement(By.xpath("//div[./input[@type='password']]"));
        pass.click();
        pass = driver.findElement(By.xpath("//input[@type='password']"));
        pass.sendKeys("**********");
        WebElement butt2 = driver.findElement(By.xpath("//button[./*[text()='Войти']]"));
        butt2.click();
        logger.trace(driver.manage().getCookies());
    }
}
