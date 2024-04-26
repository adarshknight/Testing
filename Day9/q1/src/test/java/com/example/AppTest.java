package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    WebDriver driver;

    @BeforeMethod
    public void bm() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
    }

    @Test(dataProvider = "dat1")
    public void test1(String from, String to, String departure, String return1, String tc) {
        driver.get("https://www.ixigo.com/");
        WebElement element1 = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div/div/div/p"));
        WebElement element2 = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div/div/p"));
        WebElement element3 = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div/div/p[2]"));
        WebElement element4 = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div/div/div/p"));
        WebElement element5 = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div/div/div/div/p[2]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerText = arguments[1];", element1, from);
        js.executeScript("arguments[0].innerText = arguments[1];", element2, to);
        js.executeScript("arguments[0].innerText = arguments[1];", element3, departure);
        js.executeScript("arguments[0].innerText = arguments[1];", element4, return1);
        js.executeScript("arguments[0].innerText = arguments[1];", element5, tc);
        
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/button")).click();
    }

    @Test
    public void test2() {
        driver.get("https://www.ixigo.com/");
        driver.findElement(By.xpath("/html/body/main/div[3]/div[2]/div/div[2]/div[1]/p[1]")).click();
        Set<String> id = driver.getWindowHandles();
        for (String it : id) {
            if (!it.equals(driver.getWindowHandle())) {
                driver.switchTo().window(it);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("about"));
    }

    @DataProvider(name = "dat1")
    public Object[][] dp1() throws IOException {
        FileInputStream fs = new FileInputStream("/Users/adarsh/Desktop/software testing/Day9/q1/Book2.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowcount = sheet.getLastRowNum();
        int colcount = sheet.getRow(0).getLastCellNum();
        Object[][] obj = new Object[rowcount+1][colcount];
        //for (int i = 0; i < rowcount+1; i++) {
            Row row = sheet.getRow(0);
            for (int j = 0; j < colcount; j++) {
                obj[0][j] = row.getCell(j).getStringCellValue();
                System.out.println(obj[0][j]);
            }
       // }
        workbook.close(); 
        return obj;
    }
}
