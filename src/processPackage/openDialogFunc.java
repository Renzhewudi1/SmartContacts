package processPackage;
/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: openDialogFunc.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define open file function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *****************************************************************************************************************************/

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import interfacePackage.FileOperation;
import interfacePackage.Profile;

public class openDialogFunc extends JDialog{
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public HSSFSheet sheet;
	public boolean readFlg = false;
	private int x, y;
	private Profile profile;
	
	/***************************************************
	 * Function Name:  openDialogFunc
	 * Author: feng.yu
	 * Input variable:  Object[] inputTableRow, String inputTitle
	 * Output variable: N/A
	 * Description:  open file dialog.
	 **************************************************/
    public openDialogFunc(Object[] inputTableRow, String inputTitle) {
    	
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
    	int option = chooser.showOpenDialog(this);
    	if(option == JFileChooser.APPROVE_OPTION) {
    		File file = chooser.getSelectedFile();
    		FileOperation FO = new FileOperation(inputTableRow, inputTitle);
    		FO.readValue(file);
    		sheet = FO.readBook.getSheet(inputTitle);
    		try{
    		    if(sheet.getPhysicalNumberOfRows() > 0) {
				    readFlg = true;
				    profile.write(chooser.getSelectedFile().toString(), inputTitle);
			    }
			    else {
				    JDialog faultDia = new JDialog();
				    faultDia.add(new JTextArea(inputTitle + "为空"));
				    faultDia.setBounds(x, y, 100, 100);
				    faultDia.setVisible(true);
			    }
    		}
    		catch(NullPointerException e) {
			    JDialog nullDia = new JDialog();
			    nullDia.add(new JTextArea("    " + inputTitle + "为空"));
			    nullDia.setBounds(x, y, 100, 100);
			    nullDia.setVisible(true);
    		}
    		catch(Exception e) {
			    JDialog errDia = new JDialog();
			    errDia.add(new JTextArea("读取错误"));
			    errDia.setBounds(x, y, 100, 100);
			    errDia.setVisible(true);
    		}

			
    	}
    }
    
	/***************************************************
	 * Function Name:  setXY
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  get open dialog panel position.
	 **************************************************/
    public void setXY(int inputX, int inputY) {
    	x = inputX;
    	y = inputY;
    }
}
