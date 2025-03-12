package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public void setup(){
        setup(new ChromeDriver());
    }

    public void setup(WebDriver driver){
        this.driver = driver;
        this.driver.manage().window().maximize();
    }

    public abstract boolean run();

    public void teardown() {
        if (driver != null) {
            driver.quit();  // Close the browser
        }
    }
}
