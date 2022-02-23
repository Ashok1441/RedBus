package com.redbus;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {
	@Test
	public void seatBooking() throws AWTException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.redbus.in/");
	    String acrbHomeTitle = driver.getTitle();
	    
	    String exrbHomeTitle="Book Bus Travels, AC Volvo Bus, rPool & Bus Hire - redBus India";
	    Assert.assertEquals(acrbHomeTitle, exrbHomeTitle,"Title is Mismated");
	    
	    driver.findElement(By.id("src")).sendKeys("kukat");
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_DOWN);
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    
	    robot.keyRelease(KeyEvent.VK_DOWN);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    
	    driver.findElement(By.id("dest")).sendKeys("kur");
	    robot.keyPress(KeyEvent.VK_DOWN);
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    
	    robot.keyRelease(KeyEvent.VK_DOWN);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    
	    driver.findElement(By.id("onward_cal")).click();
	    
	    String mmAndYy = "Mar 2022";
	    int date=01;
	    Thread.sleep(2000);
	    
        while(true) {
			
			String text = driver.findElement(By.xpath("//td[@class='monthTitle']")).getText();
		   System.out.println(text);
			if(text.equals(mmAndYy)) {
				break;
			}
			else {
				driver.findElement(By.className("next")).click();
				
			}
		}
        driver.findElement(By.xpath("//table[@class='rb-monthTable first last']//td[text()="+date+"]")).click();
        driver.findElement(By.id("search_btn")).click();
        Thread.sleep(5000);
        driver.findElement(By.className("close")).click();
        driver.findElement(By.xpath("//span[text()='Ok, got it']")).click();
        Thread.sleep(2000);
        
       JavascriptExecutor jse = (JavascriptExecutor)driver;
       Point locAdd = driver.findElement(By.xpath("//div[@class='section-msg']")).getLocation();
       jse.executeScript("window.scrollBy"+locAdd);
       
        driver.findElement(By.className("button")).click();
        driver.findElement(By.xpath("(//div[@class='button view-seats fr'])[1]")).click();
        driver.findElement(By.xpath("(//div[@class='radio-unchecked'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("gotoseat_btn")).click();
	    
	}

}
