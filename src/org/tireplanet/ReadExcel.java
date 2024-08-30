package org.tireplanet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadExcel {
	  // Method to check if login was successful
	
	public static void checkLoginFail(WebDriver driver)
	{
		
            WebElement failElement = driver.findElement(By.xpath("//div[@class='flex items-center']")); // Adjust selector as needed
            failElement.getText();
          //  row.createCell(3).setCellValue(failElement.getText());
           // return failElement.isDisplayed();
           
        
	}
    private static boolean checkLoginSuccess(WebDriver driver) {
        // Replace with actual logic to verify successful login
        // For example, you might look for a specific element that only appears on successful login
        try {
            WebElement successElement = driver.findElement(By.xpath("//span[text()='Dashboard']")); // Adjust selector as needed
            return successElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) {
        // Path to the Excel file
       String excelFilePath = "C:\\Users\\Rajeswari Palani\\Login Details.xlsx";

    	 // Initialize WebDriver
        WebDriver driver=new ChromeDriver();

        try {
            // Load the Excel file
            FileInputStream fis = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over the rows in the sheet
            for (Row row : sheet) {
                // Skip header row (if necessary)
                if (row.getRowNum() == 0) {
                    continue;
                }

                String UserName = row.getCell(0).getStringCellValue(); // Adjust cell index if needed
                String Password = row.getCell(1).getStringCellValue(); // Adjust cell index if needed
               
                // Open the login page
                driver.get("https://main.dvtf1h28t9tk3.amplifyapp.com/sign-in"); // Replace with your login URL
                driver.manage().window().maximize();
                // Find the username, password fields, and login button
                Thread.sleep(1000);
                WebElement usernameField=driver.findElement(By.xpath("//input[@type='text']"));// Adjust selector as needed
                // Enter the username 
                usernameField.clear();
                usernameField.sendKeys(UserName);
                Thread.sleep(1000);
                WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']")); // Adjust selector as needed
             // Enter the password
                passwordField.clear();
                passwordField.sendKeys(Password);
                Thread.sleep(1000);
                /*WebElement rememberme=driver.findElement(By.xpath("//input[@type='checkbox']"));
                rememberme.click();
                Thread.sleep(1000);*/
                WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']")); // Adjust selector as needed
                loginButton.click();
                
                
                Thread.sleep(5000);
                // Wait for some time or handle post-login steps if necessary
                
             // Check if login was successful or handle errors
                boolean loginSuccess = checkLoginSuccess(driver);

                // Write result back to Excel
                if (!loginSuccess) {
                	
                    row.createCell(2).setCellValue("Login failed");
                    WebElement failElement = driver.findElement(By.xpath("//div[@class='flex items-center']")); // Adjust selector as needed
                    
                    row.createCell(3).setCellValue(failElement.getText());
                    
                } 
                else
                {
                    row.createCell(2).setCellValue("Login successful");
                   
                    row.createCell(3).setCellValue("Remember me Not Selected");
                    Thread.sleep(1000); // Adjust wait time as needed
                    WebElement LogOut = driver.findElement(By.xpath("//span[@class='avatar avatar-circle']")); // Adjust selector as needed
                    LogOut.click();
                    Thread.sleep(1000);
                    WebElement LogOut1 = driver.findElement(By.xpath("//span[text()='Sign Out']")); // Adjust selector as needed
                    LogOut1.click();
                    Thread.sleep(3000);
                    
                }
            

            // Write changes back to the Excel file
            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            }
            }

            // Close the workbook
            workbook.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Close the browser
          //  driver.quit();
        }
    }
}
