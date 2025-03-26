package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoQaTestOne extends TestBase{

    public boolean run() {

        driver.get("http://demoqa.com/");

        ((JavascriptExecutor) driver).executeScript("document.body.style.zoom='75%';"); // Set zoom to 75%

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        var wid = driver.findElement(By.xpath("//div[@class=\"category-cards\"]/div[./descendant::h5[contains(text(), \"Widgets\")]]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", wid);

        wid.click();
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/widgets"));

        var bar = driver.findElement(By.xpath("//li[span[contains(text(), \"Progress Bar\")]]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", bar);

        wait.until(ExpectedConditions.elementToBeClickable(bar)).click();

        driver.findElement(By.xpath("//*[@id='progressBarContainer']/button"))
                .click();

        wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[@id='progressBar']/div"), "aria-valuenow", "100"));

        driver.findElement(By.xpath("//*[@id='progressBarContainer']/button"))
                .click();

        wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[@id='progressBar']/div"), "aria-valuenow", "0"));

        var value = driver.findElement(By.xpath("//*[@id='progressBar']/div")).getDomAttribute("aria-valuenow");

        assert value != null;
        return Integer.parseInt(value) == 0;
    }
}
