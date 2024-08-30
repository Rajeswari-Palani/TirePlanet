package org.tireplanet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductList extends Browser {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
	
		Browser a=new Browser();
		a.openbrowser();
		DataDrivenExcel driven=new DataDrivenExcel();
		driven.startCapture();
		
		// capture.startCapture();
		
		Thread.sleep(2000);
        WebElement ProductMenu=driver.findElement(By.xpath("//div[@class='menu-collapse'][1]"));
        ProductMenu.click();
        Thread.sleep(1000);
        JavascriptExecutor executor= (JavascriptExecutor)driver;
        WebElement ProductList=driver.findElement(By.xpath("//span[contains(text(),'Product List')]"));
        executor.executeScript("arguments[0].click()",ProductList);
        // ProductList.click();
        Thread.sleep(7000);
     WebElement CheckBox=  driver.findElement(By.xpath("//label[@class='checkbox-label mb-0'][1]"));
        Thread.sleep(2000);
        executor.executeScript("arguments[0].click()",CheckBox);
        List<WebElement> selectedCheckboxes = driver.findElements(By.xpath("//table[@class='table-default table-hover table-auto-layout']//input[@type='checkbox'][@checked]"));
        System.out.println("Number of selected checkboxes: " + selectedCheckboxes.size());
        WebElement checkcount=driver.findElement(By.xpath("//p[@class='text-gray-500 text-lg']"));
        String c=  checkcount.getText();
        System.out.println(c);
      Thread.sleep(1000);
        WebElement checkcount1=driver.findElement(By.xpath("//p[contains(text(),'Selected Count:')]"));
        String c1=  checkcount1.getText();
         System.out.println(c1);
                
         driven.stopCapture();

         // Get captured output
         String consoleOutput = driven.getCapturedOutput();
         String[] outputLines = consoleOutput.split(System.lineSeparator());

         // Write the output to an Excel file
       
         driven.writeToExcel(outputLines, "console_output.xlsx");
       //  driven.writeoutput();

	}

}
