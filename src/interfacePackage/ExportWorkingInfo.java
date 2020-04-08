/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: ExportWorkingInfo.java
 * Author:    feng.yu
 * Create Time: 2018-5-4
 * Description：This file is used to define user panel.
 * Change History:    Time        Author           Failure           Description
 *                   2018-5-4     feng.yu           N/A              Create
 *****************************************************************************************************************************/

package interfacePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportWorkingInfo extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField fileName;
	public JButton saveButton;
	public JButton cancelButton;
	private int x, y;
    private Profile profile;
	
	/***************************************************
	 * Function Name:  saveDialogFunc
	 * Author: feng.yu
	 * Input variable:  Vector<Vector> dataVector, Object[] inputTableRow, String inputTitle
	 * Output variable: N/A
	 * Description:  save file dialog function.
	 **************************************************/
	
	@SuppressWarnings("rawtypes")
	public ExportWorkingInfo(Vector<Vector> dataVector, Object[] inputTableRow, String inputTitle) {

		// TODO Auto-generated constructor stub
        if(!dataVector.isEmpty()) {
        	/*Choose file path*/
        	profile = new Profile();
        	if(!profile.read(inputTitle)) {
        		profile.latestPath = "C:/Users/huoshanshan/Documents";
        	}
        	else {
        		/*Do nothing*/
        	}
        	JFileChooser chooser = new JFileChooser(new File(profile.latestPath));
        	FileNameExtensionFilter filter = new FileNameExtensionFilter("表格文件","xls");
        	chooser.setFileFilter(filter);
        	int option = chooser.showSaveDialog(this);
        	/*If approve operation*/
        	if(option == JFileChooser.APPROVE_OPTION) {
        		
        		/*Check file name*/
    			String name = chooser.getSelectedFile().getName();
        		
        		if(!name.endsWith("xls")) {
    				File tmp_File = new File(chooser.getSelectedFile().getParent()
    						+ "\\" + chooser.getSelectedFile().getName()
    						+ ".xls");
    				chooser.setSelectedFile(tmp_File);
    				
    			}
        		else {
        			
        		}
        		String watch1 = chooser.getSelectedFile().toString();
    			/*Store final chooser path into set.ini*/
    			profile.write(chooser.getSelectedFile().toString(), inputTitle);
    			
        		/*Define file operation*/
        		ExportWorkingFile EWF = new ExportWorkingFile(inputTableRow, inputTitle);
        		/*Get select file*/
        		try {
            		if(chooser.getSelectedFile().exists()) {
            			EWF.insertSheet(chooser.getSelectedFile());
            		}
            		else {
            			EWF.createFile(chooser.getSelectedFile());
            		}
            		
            	    
        		}
        		catch(Exception e) {
        			e.printStackTrace();
        		}
        		
        		/*Get size of table*/
        		for(int i = 0; i < dataVector.size();i++)
        		{
        			try {
            			/*Add every row into excel table*/
            			if(dataVector.elementAt(i).size() == inputTableRow.length) {
            				EWF.insertValue(dataVector.elementAt(i), 
                					chooser.getSelectedFile());
            			}
            			else {
            			    JDialog errDia = new JDialog();
            			    errDia.add("表格长度错误", new JTextArea());
            			    errDia.setVisible(true);
            			}       				
        			}
        			catch(IllegalArgumentException e) {
        			    JDialog nullDia = new JDialog();
        			    nullDia.add(new JTextArea("    " + inputTitle + "写入错误"));
        			    nullDia.setBounds(x, y, 100, 100);
        			    nullDia.setVisible(true);
        			}
        			catch(Exception e) {
        			    JDialog errDia = new JDialog();
        			    errDia.add(new JTextArea("写入错误"));
        			    errDia.setBounds(x, y, 100, 100);
        			    errDia.setVisible(true);
        			}


        		}

        	}
        }
        else {
        	JOptionPane.showMessageDialog(this, "表格为空");
        }
	
	}

	/***************************************************
	 * Function Name:  setXY
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  get save dialog panel position.
	 **************************************************/
    public void setXY(int inputX, int inputY) {
    	x = inputX;
    	y = inputY;
    }
    
    private class ExportWorkingFile {
    	
    	public HSSFWorkbook excelBook;
    	public HSSFWorkbook readBook;
    	public Object[] defaultTableRow;
    	public String sheetTitle;
    	
    	/***************************************************
    	 * Function Name:  ExportWorkingFile
    	 * Author: feng.yu
    	 * Input variable:  Object[] inputTableRow, String inputTitle
    	 * Output variable: N/A
    	 * Description:  Export working file.
    	 **************************************************/
    	private ExportWorkingFile(Object[] inputTableRow, String inputTitle) {
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
    			HSSFCellStyle tmpSty = excelBook.createCellStyle();
    			HSSFCellStyle tmpSty1 = excelBook.createCellStyle();
    			HSSFFont font = excelBook.createFont();
    			HSSFFont font1 = excelBook.createFont();

    			
    			/*Define first line value*/
    			HSSFSheet sheet = excelBook.createSheet(sheetTitle);
    			sheet.getPrintSetup().setLandscape(true);
    			sheet.setDefaultColumnWidth(3);
    			sheet.setColumnWidth(1, 1700);
    			sheet.setColumnWidth(defaultTableRow.length, 1700);
    			
    			HSSFRow WI = sheet.createRow((short)0);
    			for(int i = 0; i <= defaultTableRow.length; i++) {
   				    WI.createCell((short)i).setCellValue(" ");
   				    HSSFCell cell = WI.getCell(i);
   				    font1.setFontHeightInPoints((short)24);
   				    font1.setFontName("黑体");
       			    tmpSty.setAlignment(HorizontalAlignment.CENTER);
       			    tmpSty.setVerticalAlignment(VerticalAlignment.CENTER);
       			    tmpSty.setFont(font1);
        			tmpSty.setBorderBottom(BorderStyle.THIN);
        			tmpSty.setBorderLeft(BorderStyle.THIN);
        			tmpSty.setBorderRight(BorderStyle.THIN);
        			tmpSty.setBorderTop(BorderStyle.THIN);
       			    cell.setCellStyle(tmpSty);
   			    }
    			WI.getCell(0).setCellValue("考勤表");
    			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (defaultTableRow.length)));
    			
    			/*Define second line value*/
    			HSSFRow Corp_name = sheet.createRow((short)1);
    			for(int i = 0; i <= defaultTableRow.length; i++) {
    				Corp_name.createCell((short)i).setCellValue(" ");
   				    HSSFCell cell = Corp_name.getCell(i);
       			    font.setFontHeightInPoints((short)8);
       			    font.setFontName("宋体");
       			    tmpSty1.setAlignment(HorizontalAlignment.CENTER);
       			    tmpSty1.setVerticalAlignment(VerticalAlignment.CENTER);
       			    tmpSty1.setFont(font);
       			    tmpSty1.setBorderBottom(BorderStyle.THIN);
       			    tmpSty1.setBorderLeft(BorderStyle.THIN);
       			    tmpSty1.setBorderRight(BorderStyle.THIN);
       			    tmpSty1.setBorderTop(BorderStyle.THIN);
       			    cell.setCellStyle(tmpSty1);
   			    }
    			Corp_name.getCell((short)0).setCellValue("北京亿中景科技发展有限公司" + "               " + sheetTitle);
    			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, (defaultTableRow.length)));
    			
    			/*Define name row*/
    			HSSFRow row = sheet.createRow((short)2);                    /*Create row at 0*/
    			for(int i = 0; i < defaultTableRow.length; i++) {
    				 row.createCell((short)i).setCellValue(defaultTableRow[i].toString());
    				 row.getCell((short)i).setCellStyle(tmpSty1);
    			}
    			
    			row.createCell((short)defaultTableRow.length).setCellValue("出勤总计");
    			row.getCell((short)defaultTableRow.length).setCellStyle(tmpSty1);
    			
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
    			HSSFCellStyle tmpSty = excelBook.createCellStyle();
    			HSSFCellStyle tmpSty1 = excelBook.createCellStyle();
    			HSSFFont font = excelBook.createFont();
    			HSSFFont font1 = excelBook.createFont();

    			
    			/*Define first line value*/
    			HSSFSheet sheet = excelBook.createSheet(sheetTitle);
    			sheet.getPrintSetup().setLandscape(true);
    			sheet.setDefaultColumnWidth(3);
    			sheet.setColumnWidth(1, 1700);
    			sheet.setColumnWidth(defaultTableRow.length, 1700);
    			
    			HSSFRow WI = sheet.createRow((short)0);
    			for(int i = 0; i <= defaultTableRow.length; i++) {
   				    WI.createCell((short)i).setCellValue(" ");
   				    HSSFCell cell = WI.getCell(i);
   				    font1.setFontHeightInPoints((short)24);
   				    font1.setFontName("黑体");
       			    tmpSty.setAlignment(HorizontalAlignment.CENTER);
       			    tmpSty.setVerticalAlignment(VerticalAlignment.CENTER);
       			    tmpSty.setFont(font1);
        			tmpSty.setBorderBottom(BorderStyle.THIN);
        			tmpSty.setBorderLeft(BorderStyle.THIN);
        			tmpSty.setBorderRight(BorderStyle.THIN);
        			tmpSty.setBorderTop(BorderStyle.THIN);
       			    cell.setCellStyle(tmpSty);
   			    }
    			WI.getCell(0).setCellValue("考勤表");
    			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (defaultTableRow.length)));
    			
    			/*Define second line value*/
    			HSSFRow Corp_name = sheet.createRow((short)1);
    			for(int i = 0; i <= defaultTableRow.length; i++) {
    				Corp_name.createCell((short)i).setCellValue(" ");
   				    HSSFCell cell = Corp_name.getCell(i);
       			    font.setFontHeightInPoints((short)8);
       			    font.setFontName("宋体");
       			    tmpSty1.setAlignment(HorizontalAlignment.CENTER);
       			    tmpSty1.setVerticalAlignment(VerticalAlignment.CENTER);
       			    tmpSty1.setFont(font);
       			    tmpSty1.setBorderBottom(BorderStyle.THIN);
       			    tmpSty1.setBorderLeft(BorderStyle.THIN);
       			    tmpSty1.setBorderRight(BorderStyle.THIN);
       			    tmpSty1.setBorderTop(BorderStyle.THIN);
       			    cell.setCellStyle(tmpSty1);
   			    }
    			Corp_name.getCell((short)0).setCellValue("北京亿中景科技发展有限公司" + "               " + sheetTitle);
    			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, (defaultTableRow.length)));
    			
    			/*Define name row*/
    			HSSFRow row = sheet.createRow((short)2);                    /*Create row at 0*/
    			for(int i = 0; i < defaultTableRow.length; i++) {
    				 row.createCell((short)i).setCellValue(defaultTableRow[i].toString());
    				 row.getCell((short)i).setCellStyle(tmpSty1);
    			}
    			
    			row.createCell((short)defaultTableRow.length).setCellValue("出勤总计");
    			row.getCell((short)defaultTableRow.length).setCellStyle(tmpSty1);
    			
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
    		
			HSSFCellStyle tmpSty = excelBook.createCellStyle();
			HSSFFont font = excelBook.createFont();
			
    		try {
    			/*Add elements into excel book*/
    			HSSFSheet sheet = excelBook.getSheet(sheetTitle);
    			int count = sheet.getPhysicalNumberOfRows();
    			int workingday = 0;
    			HSSFRow row = sheet.createRow((short)count);
    			for(int i = 0; i < paraVector.size(); i++) {
       			    font.setFontHeightInPoints((short)8);
       			    font.setFontName("宋体");
       			    tmpSty.setAlignment(HorizontalAlignment.CENTER);
       			    tmpSty.setVerticalAlignment(VerticalAlignment.CENTER);
       			    tmpSty.setFont(font);
       				tmpSty.setBorderBottom(BorderStyle.THIN);
       				tmpSty.setBorderLeft(BorderStyle.THIN);
       				tmpSty.setBorderRight(BorderStyle.THIN);
       				tmpSty.setBorderTop(BorderStyle.THIN);
    				row.createCell((short)i).setCellValue(paraVector.elementAt(i).toString());
    				row.getCell((short)i).setCellStyle(tmpSty);
    				if(row.getCell((short)i).getStringCellValue().equals("出勤"))
    				{
    					workingday++;
    				}
    			}
    			row.createCell((short)paraVector.size()).setCellValue(workingday);
    			row.getCell((short)defaultTableRow.length).setCellStyle(tmpSty);

    			FileOutputStream out = new FileOutputStream(paraOutputFile);
    			excelBook.write(out);
    			out.flush();
    			out.close();
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
}
