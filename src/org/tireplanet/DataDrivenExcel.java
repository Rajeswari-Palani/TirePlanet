package org.tireplanet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.PrintStream;

 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Workbook; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class DataDrivenExcel 
{ 
	 private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    private final PrintStream originalOut = System.out;
	    
	    public void startCapture() 
	    {
     System.setOut(new PrintStream(outputStream));
 }

 public void stopCapture() {
     System.setOut(originalOut);
 }

 public String getCapturedOutput() {
     return outputStream.toString();
 }

    public void writeToExcel(String[] data, String filePath)
    { 
    	 
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Console Output");

            // Row row = sheet.createRow(0);

            // Write each piece of data into successive columns
//            for (int i = 0; i < data.length; i++) {
//            	
//                Cell cell = row.createCell(i);
//                cell.setCellValue(data[i]);
//            }
            Sheet sheet1 = workbook.getSheetAt(0);
            for(int i=0; i<data.length; i++){
            	Row row = sheet.createRow(i);
            	Cell cell = row.createCell(0);

            	cell.setCellValue(data[i]);
            	}
            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


}
