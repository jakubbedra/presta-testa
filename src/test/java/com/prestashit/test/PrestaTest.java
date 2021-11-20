package com.prestashit.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class PrestaTest {

    private static final String HOST_ADDRESS = "http://localhost:80";

    private final Random r;

    private static final int MAX_PRODUCT_QUANTITY = 10;

    public PrestaTest() {
        this.r = new Random();
    }

    @Test
    public void shopTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(HOST_ADDRESS);

        addProductsToCart(driver);
        createUser(driver);
        checkout(driver);
        checkShipmentStatus(driver);
    }

    private void addProductsToCart(WebDriver driver) throws InterruptedException {
        //selecting category
        driver.findElement(By.id("category-3")).click();
        Thread.sleep(200);
        //selecting subcategory
        driver.findElement(By.xpath("//div[@id=\"subcategories\"]/ul/li[3]/div/a")).click();
        Thread.sleep(200);
        //selecting items to cart from category 1
        for (int i = 0; i < 8; i++) {
            addProductToCart(i, driver);
        }

        driver.findElement(By.id("category-8")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("//div[@id=\"subcategories\"]/ul/li[2]/div/a")).click();
        Thread.sleep(200);
        for (int i = 0; i < 2; i++) {
            addProductToCart(i, driver);
        }
    }

    private void addProductToCart(int ind, WebDriver driver) throws InterruptedException {
        String url = driver.getCurrentUrl();

        WebElement product = driver.findElement(By.xpath("//div[@id=\"js-product-list\"]/div[@class=\"products row\"]"))
                .findElements(By.xpath("//div[@class=\"product\"]")).get(ind);

        product.findElement(By.tagName("a")).click();
        Select select = new Select(driver.findElement(By.id("group_5")));
        select.selectByValue("27");
        driver.findElement(By.id("quantity_wanted")).sendKeys(Keys.chord(Keys.CONTROL, "a"), r.nextInt(MAX_PRODUCT_QUANTITY) + "");

        driver.findElement(By.xpath("//div[@class=\"add\"]/button")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5 * 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("myModalLabel")));

        driver.navigate().to(url);
    }

    private void createUser(WebDriver driver) throws InterruptedException {
        //accessing the login page
        driver.findElement(By.xpath("//div[@id=\"_desktop_user_info\"]/div/a")).click();
        Thread.sleep(500);

        //accessing the register page
        driver.findElement(By.xpath("//div[@class=\"no-account\"]/a")).click();
        Thread.sleep(500);

        //filling the form
        driver.findElement(By.id("field-id_gender-1")).click();
        Thread.sleep(500);
        driver.findElement(By.id("field-firstname")).sendKeys("Jan");
        Thread.sleep(500);
        driver.findElement(By.id("field-lastname")).sendKeys("Paweł");
        Thread.sleep(500);
        driver.findElement(By.id("field-email")).sendKeys("jp2" + r.nextInt() + "@example.com");
        Thread.sleep(500);
        driver.findElement(By.id("field-password")).sendKeys("qwerty1234");
        Thread.sleep(500);
        driver.findElement(By.id("field-birthday")).sendKeys("2005-04-02");
        Thread.sleep(500);
        driver.findElement(By.name("customer_privacy")).click();
        Thread.sleep(200);
        driver.findElement(By.name("psgdpr")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//section[@class=\"register-form\"]/form/footer/button")).click();
        Thread.sleep(500);
    }

    private void checkout(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//div[@id=\"_desktop_cart\"]/div/div/a")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("//div[@class=\"text-sm-center\"]/a")).click();
        //personal data
        driver.findElement(By.id("field-address1")).sendKeys("sample address 21/3/7");
        driver.findElement(By.id("field-postcode")).sendKeys("69-420");
        driver.findElement(By.id("field-city")).sendKeys("Fucking");
        driver.findElement(By.name("confirm-addresses")).click();
        //delivery
        driver.findElement(By.id("delivery_option_2")).click();
        driver.findElement(By.id("delivery_message")).sendKeys("prosze wyslac asap");
        driver.findElement(By.name("confirmDeliveryOption")).click();
        //payment
        driver.findElement(By.id("payment-option-2")).click();
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.xpath("//div[@id=\"payment-confirmation\"]/div[@class=\"ps-shown-by-js\"]/button")).click();

    }

    private void checkShipmentStatus(WebDriver driver) {
        driver.findElement(By.xpath("//div[@id=\"_desktop_user_info\"]/div/a[@class=\"account\"]")).click();
        driver.findElement(By.xpath("//a[@id=\"history-link\"]")).click();
    }

}
