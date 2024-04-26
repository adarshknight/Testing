package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
// import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    WebDriver driver;
    @BeforeTest
    public void beforetest(){
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void test1(){
        driver=new ChromeDriver();
        driver.get("https://www.barnesandnoble.com/");
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]/div/a[1]")).click();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys("Chetan Bhagat"+Keys.ENTER);
        
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String title=driver.findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1")).getText();
        Assert.assertTrue(title.contains("Chetan Bhagat"));

    }
    @Test
    public void test2(){
        Actions mouse=new Actions(driver);
        WebElement audio=driver.findElement(By.xpath("//*[@id='rhfCategoryFlyout_Audiobooks']"));
        mouse.moveToElement(audio).perform();
        driver.findElement(By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();

        // Assuming driver is already initialized, e.g., WebDriver driver = new ChromeDriver();

        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0, 1000)");
        driver.findElement(By.xpath("//*[@id='addToBagForm_2940159543998']/input[11]")).submit();

}



}