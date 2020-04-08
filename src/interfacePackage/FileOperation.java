package interfacePackage;
/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: FileOperation.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description£ºThis file is used to define file operation function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *****************************************************************************************************************************/

import java.io.*;
import java.util.*;

import javax.swing.*;

import org.apache.poi.hssf.usermodel.*;

public class FileOperation {

	public HSSFWorkbook excelBook;
	public HSSFWorkbook readBook;
	public Object[] defaultTableRow;
	public String sheetTitle;
	
	/***************************************************
	 * Function Name:  FileOperation
	 * Author: feng.yu
	 * Input variable:  Object[] inputTableRow, String inputTitle
	 * Output variable: N/A
	 * Description:  File operate.
	 **************************************************/
	public FileOperation(Object[] inputTableRow, String inputTitle) {
		sheetTitle = inputTitle;
		defaultTableRow = inputTableRow;
	}
	
	/***************************************************
	 * Function Name:  createFile
	 * Author: feng.yu
	 * Input variable:  File paraOutputFile
	 * Output variable: N/A
	 * Description:  Create file.
	 **************************************************/
	public void createFile(File paraOutputFile) {
		
		try {
			excelBook = new HSSFWorkbook();
			HSSFSheet sheet = excelBook.createSheet(sheetTitle);
			HSSFRow row = sheet.createRow((short)0);                    /*Create row at 0*/
			for(int i = 0; i < defaultTableRow.length; i++) {
				 row.createCell((short)i).setCellValue(defaultTableRow[i].toString());
			}
			FileOutputStream out = new FileOutputStream(paraOutputFile);
			excelBook.write(out);
			out.flush();
			out.close();			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/***************************************************
	 * Function Name:  createFile
	 * Author: feng.yu
	 * Input variable:  File paraOutputFile
	 * Output variable: N/A
	 * Description:  Create file.
	 **************************************************/
	public void insertSheet(File paraOutputFile) {
		
		try {
			FileInputStream isr = new FileInputStream(paraOutputFile);
			excelBook = new HSSFWorkbook(isr);
			if(excelBook.getSheet(sheetTitle) != null) {
				excelBook.removeSheetAt(excelBook.getSheetIndex(sheetTitle));
			}
			HSSFSheet sheet = excelBook.createSheet(sheetTitle);
			HSSFRow row = sheet.createRow((short)0);                    /*Create row at 0*/
			for(int i = 0; i < defaultTableRow.length; i++) {
				 row.createCell((short)i).setCellValue(defaultTableRow[i].toString());
			}
			FileOutputStream out = new FileOutputStream(paraOutputFile);
			excelBook.write(out);
			out.flush();
			out.close();			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/***************************************************
	 * Function Name:  insertValue
	 * Author: feng.yu
	 * Input variable:  Vector<String> paraVector, File paraOutputFile
	 * Output variable: N/A
	 * Description:  Insert value.
	 **************************************************/
	public void insertValue(Vector<String> paraVector, File paraOutputFile)
	{
		try {
			/*Add elements into excel book*/
			HSSFSheet sheet = excelBook.getSheet(sheetTitle);
			int count = sheet.getPhysicalNumberOfRows();
			HSSFRow row = sheet.createRow((short)count);
			for(int i = 0; i < paraVector.size(); i++) {
				row.createCell((short)i).setCellValue(paraVector.elementAt(i).toString());
			}

			FileOutputStream out = new FileOutputStream(paraOutputFile);
			excelBook.write(out);
			out.flush();
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/***************************************************
	 * Function Name:  readValue
	 * Author: feng.yu
	 * Input variable:  File inputFile
	 * Output variable: N/A
	 * Description:  Read value.
	 **************************************************/
	public void readValue(File inputFile) {
		try {
			FileInputStream isr = new FileInputStream(inputFile);
			readBook = new HSSFWorkbook(isr);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
