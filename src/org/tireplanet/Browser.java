package org.tireplanet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Browser {
	public void openbrowser() throws InterruptedException
	{
	 // Initialize WebDriver
    WebDriver driver=new ChromeDriver();
	driver.get("https://main.dvtf1h28t9tk3.amplifyapp.com/sign-in"); // Replace with your login URL
     driver.manage().window().maximize();
     WebElement usernameField=driver.findElement(By.xpath("//input[@type='text']"));// Adjust selector as needed
     usernameField.sendKeys("admin@tireplanet.com");
     Thread.sleep(1000);
     WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']")); // Adjust selector as needed
     passwordField.sendKeys("admin123");
     Thread.sleep(1000);
     WebElement rememberme=driver.findElement(By.xpath("//input[@type='checkbox']"));
     rememberme.click();
     Thread.sleep(1000);
     WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']")); // Adjust selector as needed
     loginButton.click();
}

	
}