package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoQaTestTwo extends TestBase{

    public boolean run() {

        driver.get("http://demoqa.com/");

        driver.findElement(
                By.xpath("//div[@class=\"category-cards\"]/div[./descendant::h5[contains(text(), \"Elements\")]]"))
                .click();

        driver.findElement(
                By.xpath("//div[@class='element-group']/descendant::li[./descendant::span[contains(text(), \"Web Tables\")]]"))
                .click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        while (true) {
            // Click "Add New Record"
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='addNewRecordButton']"))).click();

            // Fill in the form
            var name = driver.findElement(By.xpath("//input[@id='firstName']"));
            name.clear();
            name.sendKeys("John");

            var lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
            lastName.clear();
            lastName.sendKeys("Smith");

            var email = driver.findElement(By.xpath("//input[@id='userEmail']"));
            email.clear();
            email.sendKeys("john.smith@gmail.com");

            var age = driver.findElement(By.xpath("//input[@id='age']"));
            age.clear();
            age.sendKeys("30");

            var salary = driver.findElement(By.xpath("//input[@id='salary']"));
            salary.clear();
            salary.sendKeys("100");

            var department = driver.findElement(By.xpath("//input[@id='department']"));
            department.clear();
            department.sendKeys("Computer Science");

            // Click Submit
            driver.findElement(By.xpath("//*[@id='submit']")).click();

            // Wait for the pop-up to close before checking total pages
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='document']")));

            // Check the total pages
            var pageElement = driver.findElement(By.xpath("//span[@class='-totalPages']"));
            int totalPages = Integer.parseInt(pageElement.getText());

            // Stop when we reach 2 pages
            if (totalPages == 2) {
                break;
            }
        }

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='document']")));

        var nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"-next\"]/button")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);

        // Click using JavaScript to bypass potential overlays
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);

        while(true) {

            driver.findElements(By.xpath("//div[@role='row']/descendant::span[@title='Delete']"))
                    .getFirst()
                    .click();

            var pageElement = driver.findElement(By.xpath("//span[@class='-totalPages']"));
            int totalPages = Integer.parseInt(pageElement.getText());

            var currentPage = driver.findElement(By.xpath("//div[@class='-pageJump']/input"))
                    .getDomAttribute("value");

            assert currentPage != null;
            int currentPageNumber = Integer.parseInt(currentPage);

            if(totalPages == 1 && totalPages == currentPageNumber) {
                break;
            }
            else{
                return false;
            }
        }

        return true;
    }
}
