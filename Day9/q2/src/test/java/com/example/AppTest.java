package com.example;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest logger;

    @BeforeTest
    public void setup()
    {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("/Users/adarsh/Desktop/software testing/Day9/q2/Stesting.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "one")
    public void Testt2(String amount1,String interest,String year) throws IOException, InterruptedException
    {
         logger = extent.createTest("Test2","This is Second Test");
         SoftAssert assert1 = new SoftAssert();
         driver.get("https://groww.in/");
         Thread.sleep(3000);
         driver.findElement(By.xpath("//*[@id='footer']/div/div[1]/div/div[1]/div[2]/div[3]/a[2]")).click();
         Thread.sleep(3000);
         String Title = driver.findElement(By.xpath("//*[@id='root']/div[2]/h1")).getText();
         Assert.assertTrue(Title.equals("Calculators"));
         Thread.sleep(2000);
         driver.findElement(By.xpath("//*[@id='root']/div[2]/div[2]/a[15]/div")).click();
         Thread.sleep(2000);
         WebElement amount = driver.findElement(By.xpath("//*[@id='LOAN_AMOUNT']"));
         amount.clear();
         amount.sendKeys(amount1);
         WebElement in = driver.findElement(By.xpath("//*[@id='RATE_OF_INTEREST']"));
         in.clear();
         in.sendKeys(interest);
         WebElement y = driver.findElement(By.xpath("//*[@id='LOAN_TENURE']"));
         y.clear();
         y.sendKeys(year);

         String check = driver.findElement(By.xpath("//*[@id='root']/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]")).getText();
         Assert.assertTrue(check.equals("Your Amortization Details (Yearly/Monthly)"));
        }
    @Test
    public void Testtt1() throws IOException, InterruptedException
    {
         logger = extent.createTest("Test1","This is First Test");
         SoftAssert assert1 = new SoftAssert();
         driver.get("https://groww.in/");
         Thread.sleep(3000);
         driver.findElement(By.xpath("//*[@id='footer']/div/div[1]/div/div[1]/div[2]/div[3]/a[2]")).click();
         Thread.sleep(3000);
         String Title = driver.findElement(By.xpath("//*[@id='root']/div[2]/h1")).getText();
         Assert.assertTrue(Title.equals("Calculators"));
         
        }

        @DataProvider(name ="one")
    public Object[][] fetchData() throws IOException
    {  
        FileInputStream fs=new FileInputStream("/Users/adarsh/Desktop/software testing/Day9/q2/Stesting.html"); 
        XSSFWorkbook workbook=new XSSFWorkbook(fs); 
         XSSFSheet sheet=workbook.getSheetAt(0); 
        int rowCount=sheet.getLastRowNum();
        int colCount=sheet.getRow(0).getLastCellNum(); 

        Object[][] data=new Object[rowCount][colCount];
         
        for(int i=0;i<rowCount;i++)
        {  XSSFRow row=sheet.getRow(i+1); 
            for(int j=0;j<colCount;j++)
            {  data[i][j]=row.getCell(j).getStringCellValue();
                System.out.print(data[i][j]+ " ");
            }
            System.out.println();
        }  fs.close();
        return data;
    }


    @AfterMethod
    public void tearDown(ITestResult result)throws Exception
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            logger.log(Status.FAIL,"Testcase Failed : "+result.getName());
            logger.log(Status.FAIL,"Testcase Failed Reason: "+result.getThrowable());

            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String screenShotPath = "/Users/adarsh/Desktop/software testing/Day9/q2/Stesting.html"+result.getName()+".png";
            FileUtils.copyFile(screenshot,new File(screenShotPath));
            logger.addScreenCaptureFromPath(screenShotPath);
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.log(Status.PASS,"Test Case Passed: "+result.getName());
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String screenShotPath = "/Users/adarsh/Desktop/software testing/Day9/q2/Stesting.html"+result.getName()+".png";
            FileUtils.copyFile(screenshot,new File(screenShotPath));
            logger.addScreenCaptureFromPath(screenShotPath);
           
        }
        else if(result.getStatus()==ITestResult.SKIP)
        {
            logger.log(Status.SKIP,"Test Case Skipped: "+result.getName());
        }
        
    }

    @AfterTest
    public void afterTest()
    {
        extent.flush();
    }
    
}