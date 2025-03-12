package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JUnitTests {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setup(){
        setup(new ChromeDriver());
    }

    private void setup(WebDriver driver){
        this.driver = driver;
        this.driver.manage().window().maximize();
    }


    @ParameterizedTest
    @ValueSource(strings = { "data1.txt", "data2.txt" })
    public void InputFileTest(String inputFile) throws IOException {

        driver.get("https://demowebshop.tricentis.com/");

        driver.findElement(By.xpath("//a[@href='/login']")).click();

        driver.findElement(By.id("Email")).sendKeys("ponas.tadas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Tadas123");
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        var el = driver.findElement(By.xpath("//ul[@class='top-menu']/descendant::a[contains(text(), 'Digital downloads')]"));
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputFile);
        assertNotNull(inputStream, "File not found in resources");

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;

        while( (line = br.readLine()) != null) {
            String xpath = "//div[@class='item-box'][descendant::a[contains(text(), '" + line + "')]]/descendant::div[@class='buttons']/input";

            driver.findElement(By.xpath(xpath)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bar-notification']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='bar-notification']")));
        }

        br.close();

        driver.findElement(By.xpath("//a[.//span[@class='cart-qty']]")).click();
        driver.findElement(By.xpath("//*[@id='termsofservice']")).click();
        driver.findElement(By.xpath("//*[@id='checkout']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='Billing.save()']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='PaymentMethod.save()']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='PaymentInfo.save()']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Confirm']")))
                .click();

        wait.until(ExpectedConditions.urlToBe("https://demowebshop.tricentis.com/checkout/completed/"));

        var orderResult = driver.findElement(By.xpath("//div[@class='page checkout-page']/descendant::strong")).getText();

        assertEquals("Your order has been successfully processed!", orderResult);
    }

    @AfterEach
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }
}
