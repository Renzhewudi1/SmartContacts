package processPackage;
/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: saveDialogFunc.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define save file function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *****************************************************************************************************************************/

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import interfacePackage.FileOperation;
import interfacePackage.Profile;

public class saveDialogFunc extends JDialog{

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public saveDialogFunc(Vector<Vector> dataVector, Object[] inputTableRow, String inputTitle) {
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
    			/*Store final chooser path into set.ini*/
    			profile.write(chooser.getSelectedFile().toString(), inputTitle);       			
        		
        		/*Define file operation*/
        		FileOperation FO = new FileOperation(inputTableRow, inputTitle);
        		/*Get select file*/
        		try {
            		if(chooser.getSelectedFile().exists()) {
            			FO.insertSheet(chooser.getSelectedFile());
            		}
            		else {
            			FO.createFile(chooser.getSelectedFile());
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
                			FO.insertValue(dataVector.elementAt(i), 
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
}
