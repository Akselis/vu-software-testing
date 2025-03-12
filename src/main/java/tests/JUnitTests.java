package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    public void InputFileOne(String inputFile) throws IOException {

        driver.get("https://demowebshop.tricentis.com/");

        driver.findElement(By.xpath("//a[@href='/login']")).click();

        driver.findElement(By.id("Email")).sendKeys("ponas.tadas@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Tadas123");
        driver.findElement(By.id("Log in")).click();

        driver.findElement(By.xpath("//ul[@class='top-menu']/descendant::a[contains(text(), 'Digital downloads')]"));

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line;

        while( (line = br.readLine()) != null) {
            driver.findElements(By.xpath("//div[@class='item-box'][descendant::a[contains(text(), " + line + ")]]/descendant::div[@class='buttons']/input"))
                    .getFirst()
                    .click();
        }

        br.close();

        driver.findElement(By.xpath("//a[.//span[@class='cart-qty']]")).click();
        driver.findElement(By.xpath("//*[@id='termsofservice']")).click();
        driver.findElement(By.xpath("//*[@id='checkout']")).click();

        Select select = new Select(driver.findElement(By.xpath("//*[@id='BillingNewAddress_CountryId']")));
        select.selectByIndex(0);

        var city = driver.findElement(By.xpath("//*[@id='BillingNewAddress_City']"));
        city.clear();
        city.sendKeys("Tadamiestis");

        var add = driver.findElement(By.xpath("//*[@id='BillingNewAddress_City']"));
        add.clear();
        add.sendKeys("Tadgatve");

        var zip = driver.findElement(By.xpath("//*[@id='BillingNewAddress_City']"));
        zip.clear();
        zip.sendKeys("11111");

        var phone = driver.findElement(By.xpath("//*[@id='BillingNewAddress_City']"));
        zip.clear();
        zip.sendKeys("+37066666666");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='PaymentMethod.save()']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='PaymentInfo.save()']")))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Confirm']")))
                .click();

        var orderResult = driver.findElement(By.xpath("//div[@class='page checkout-page']/descendant::strong")).toString();

        assertEquals("Your order has been successfully processed!", orderResult);
    }

    @AfterEach
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }
}
