package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoQaTestOne extends TestBase{

    public boolean run() {

        driver.get("http://demoqa.com/");

        driver.findElement(
                By.xpath("//div[@class=\"category-cards\"]/div[./descendant::h5[contains(text(), \"Widgets\")]]"))
                .click();

        driver.findElement(
                By.xpath("//div[@class='element-group']/descendant::li[./descendant::span[contains(text(), \"Progress Bar\")]]"))
                .click();

        driver.findElement(By.xpath("//*[@id='progressBarContainer']/button"))
                .click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[@id='progressBar']/div"), "aria-valuenow", "100"));

        driver.findElement(By.xpath("//*[@id='progressBarContainer']/button"))
                .click();

        wait.until(ExpectedConditions.attributeToBe(By.xpath("//*[@id='progressBar']/div"), "aria-valuenow", "0"));

        var value = driver.findElement(By.xpath("//*[@id='progressBar']/div")).getDomAttribute("aria-valuenow");

        assert value != null;
        return Integer.parseInt(value) == 0;
    }
}
