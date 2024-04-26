package com.example;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class day8_session1 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
public void before() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 30);
    driver.get("http://dbankdemo.com/bank/login");
    driver.manage().window().maximize();
}
    @Test(priority = '0',dataProvider="data")
    public void test1(String username,String password)
    {

        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]")));
        usernameElement.sendKeys(username);

        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")));
        passwordElement.sendKeys(password);

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submit\"]")));
        login.submit();

        assertTrue(driver.getCurrentUrl().contains("home"));
    }
    @Test(priority = '1',dataProvider="data")
    public void test2(String username,String password)
    {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]")));
        usernameElement.sendKeys(username);

        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")));
        passwordElement.sendKeys(password);

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submit\"]")));
        login.submit();

        WebElement deposit = driver.findElement(By.xpath("//*[@id=\"deposit-menu-item\"]"));
        deposit.click();

        WebElement acForDepo = driver.findElement(By.xpath("//*[@id=\"selectedAccount\"]"));
        Select acForDepoSelect = new Select(acForDepo);
        acForDepoSelect.selectByVisibleText("Individual Checking(standard checking) (Savings)");

        WebElement amount = driver.findElement(By.id("amount"));
        amount.sendKeys("5000");

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]"));
        submit.submit();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,500)");
    }

    @Test(priority = '2',dataProvider = "data")
    public void test3(String username,String password)
    {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"username\"]")));
        usernameElement.sendKeys(username);

        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")));
        passwordElement.sendKeys(password);

        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submit\"]")));
        login.submit();

        WebElement withdraw = driver.findElement(By.xpath("//*[@id=\"withdraw-menu-item\"]"));
        withdraw.click();

        WebElement account = driver.findElement(By.xpath("//*[@id=\"selectedAccount\"]"));
        Select accountSelect = new Select(account);
        accountSelect.selectByVisibleText("Individual Checking(standard checking) (Savings)");

        WebElement amount = driver.findElement(By.xpath("//*[@id=\"amount\"]"));
        amount.sendKeys("3000");

        WebElement submit = driver.findElement(By.xpath("//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]"));
        submit.submit();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,500)");
    }

    @DataProvider(name = "data")
    public Object[][] getValues() throws IOException
    {
        Object [][]data = new Object[1][2];

        data[0][0] = "S@gmail.com";
        data[0][1] = "P@ssword12";
        return data;
    }
}