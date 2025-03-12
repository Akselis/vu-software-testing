package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DemoWebShopTest {
    private WebDriver driver;

    public void setup() {
        driver = new ChromeDriver();             // Initialize Chrome browser
        driver.manage().window().maximize();     // Maximize the browser window
    }

    public boolean run(){
        driver.get("https://demowebshop.tricentis.com/");

        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[7]/a")).click();

        var box = driver.findElements(By.className("item-box"))
                .stream()
                .filter(element ->
                        Double.parseDouble(element.findElement(By.className("actual-price")).getText()) > 99)
                .findFirst().orElse(null);

        driver.findElements(By.className("actual-price"));

        if(box == null){
            return false;
        }
        box.findElement(By.className("picture")).click();

        for(int i = 0; i < 2; i++){
            var recipient = driver.findElement(By.xpath("//*[@id='giftcard_4_RecipientName']"));
            recipient.clear();
            recipient.sendKeys("testgetter");

            var sender = driver.findElement(By.xpath("//*[@id='giftcard_4_SenderName']"));
            sender.clear();
            sender.sendKeys("testsender");

            var q1 = driver.findElement(By.xpath("//*[@id='addtocart_4_EnteredQuantity']"));
            q1.clear();
            q1.sendKeys("5000");

            if(i == 0){
                driver.findElement(By.xpath("//*[@id='add-to-cart-button-4']")).click();
            }
            if(i == 1){
                driver.findElement(By.xpath("//*[@id='add-to-wishlist-button-4']")).click();
            }
        }

        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/ul/li[6]/a"))
                .click();

        driver.findElement(By.xpath("//div[@data-productid='71']"))
                .findElement(By.className("picture"))
                .click();

        for(int i = 0; i < 2; i++){
            Select select = new Select(
                    driver.findElement(By.xpath("//*[@id='product_attribute_71_9_15']")));

            select.selectByValue("47");

            var q1 = driver.findElement(By.xpath("//*[@id='product_attribute_71_10_16']"));
            q1.clear();
            q1.sendKeys("80");

            driver.findElement(By.xpath("//*[@id='product_attribute_71_11_17_50']"))
                    .click();

            var q2 = driver.findElement(By.xpath("//*[@id='addtocart_71_EnteredQuantity']"));
            q2.clear();
            q2.sendKeys("26");

            if(i == 0){
                driver.findElement(By.xpath("//*[@id='add-to-cart-button-71']")).click();
            }
            if(i == 1){
                driver.findElement(By.xpath("//*[@id='add-to-wishlist-button-71']")).click();
            }
        }

        driver.findElement(By.xpath("//a[.//span[@class='wishlist-qty']]")).click();

        var checkboxes = driver.findElements(By.xpath("//td[@class=\"add-to-cart\"]/input"));

        for(WebElement checkbox : checkboxes){
            checkbox.click();
        }

        driver.findElement(By.xpath("//input[@value='Add to cart']")).click();

        var subtotal = driver.findElement(By.xpath(
                        "//tr[./td[@class=\"cart-total-left\"]/span[contains(text(), \"Sub-Total\")]]/descendant::span[@class=\"product-price\"]"))
                .getText();

        return Double.parseDouble(subtotal) == 1002600.00;
    }

    public void teardown() {
        if (driver != null) {
            driver.quit();  // Close the browser
        }
    }
}
