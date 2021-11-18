package com.prestashit.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class PrestaTest {

    private static final String HOST_ADDRESS = "http://localhost:80";

    private final Random r;

    public PrestaTest() {
        this.r = new Random();
    }

    @Test
    public void shopTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(HOST_ADDRESS);

        testUserCreation(driver);

        Thread.sleep(2137);
        driver.quit();
    }

    /**
     * Test for testing the user creation process
     */
    public void testUserCreation(WebDriver driver) throws InterruptedException {
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
        driver.findElement(By.id("field-email")).sendKeys("jp2" + r.nextDouble() + "@gmdmail.com");
        Thread.sleep(500);
        driver.findElement(By.id("field-password")).sendKeys("jotpedwaXD");
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

}
