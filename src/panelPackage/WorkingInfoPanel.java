/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: WorkingInfoPanel.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description£ºThis file is used to define main function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *                   2018-5-2     feng.yu           N/A              Update working record function for easier.
 *****************************************************************************************************************************/

package panelPackage;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import interfacePackage.ExportWorkingInfo;
import interfacePackage.FileOperation;
import processPackage.openDialogFunc;
import processPackage.saveDialogFunc;

public class WorkingInfoPanel extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*Define button componenets*/
	private JButton addButton;
	private JButton editButton;
	private JButton delButton;
	private JButton saveButton;
	private JButton openButton;
	private JButton exportButton;
	private JPanel buttonPanel;
	
	/*Define calendar*/
	private JButton lastMonth;
	private JButton nextMonth;
	
	private Integer monthDays;
	private JLabel monthLabel;
	private JPanel labelPane;
	private JPanel calendarPane;
	
	private JScrollPane tablePanel;
	private JTable workTable;
	private DefaultTableModel workTableModel;
	
	/*Define comboBox for table*/
	private JComboBox BOX = new JComboBox(new String[] {" ","¼ÙÆÚ","³öÇÚ","¼Ó°à","µ÷ÐÝ","²¡¼Ù","ÊÂ¼Ù","°ë¼Ù","³ö²î","³Ùµ½","ÔçÍË","¿õ¹¤"});
	private TableColumn TC;
	private TableCellEditor TCE;
	
	Vector<String> calendarTableRow;
	
	private Calendar workCalendar;
	/***************************************************
	 * Function Name:  WorkingInfoPanel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define working information panel.
	 **************************************************/
	public WorkingInfoPanel() {

		
		super("¿¼ÇÚ¼ÇÂ¼", true, true, true, true);
		panelConfiguration();
		configCalendar();
		
		placeTable();
		placeButton();
	}
	

	/***************************************************
	 * Function Name:  panelConfiguration
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Panel configuration.
	 **************************************************/
    private void panelConfiguration() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setResizable(true);
        try {
			this.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setVisible(true);
    }
    
	/***************************************************
	 * Function Name:  placeButton
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place Button.
	 **************************************************/
    private void placeButton() {
    	
    	addButton = new JButton("Ìí¼Ó");
    	editButton = new JButton("±à¼­");
    	delButton = new JButton("É¾³ý");
    	saveButton = new JButton("´æ´¢°´Å¥");
    	openButton = new JButton("¶ÁÈ¡°´Å¥");
    	exportButton = new JButton("±í¸ñµ¼³ö");
    	buttonPanel = new JPanel();
    	
    	buttonPanel.add(addButton);
    	addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<String> tmp_Vector = new Vector<>();
				for(int i = 0; i < workTableModel.getColumnCount(); i++) {
					tmp_Vector.add(" ");
				}
				workTableModel.addRow(tmp_Vector);
			}
    		
    	});
    	buttonPanel.add(delButton);
    	delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedRow;
				selectedRow = workTable.getSelectedRow();
				if(selectedRow != -1) {
					workTableModel.removeRow(selectedRow);
				}
			}
    		
    	});
    	
    	buttonPanel.add(saveButton);
    	saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//saveDialogFunc SDF = new saveDialogFunc(workTableModel.getDataVector(), calendarTableRow.toArray(), (monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼"));
				//SDF.setXY(WorkingInfoPanel.this.getX(), WorkingInfoPanel.this.getY());
				if(!workTableModel.getDataVector().isEmpty()) {
					FileOperation FO = new FileOperation(calendarTableRow.toArray(), (monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼"));
					File workExcel = new File(".\\¿¼ÇÚ¼ÇÂ¼.xls");
					/*Get select file*/
	        		try {
	            		if(workExcel.exists()) {
	            			FO.insertSheet(workExcel);
	            		}
	            		else {
	            			FO.createFile(workExcel);
	            		}            	    
	        		}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        		}
	        		
	        		/*Get size of table*/
	        		for(int i = 0; i < workTableModel.getDataVector().size();i++)
	        		{
	        			try {
	            			/*Add every row into excel table*/
	            			if(workTableModel.getDataVector().elementAt(i).size() == calendarTableRow.toArray().length) {
	                			FO.insertValue(workTableModel.getDataVector().elementAt(i), 
	                					workExcel);
	            			}
	            			else {
	            			    JOptionPane.showInternalMessageDialog(saveButton, "±í¸ñ³¤¶È´íÎó");
	            			}       				
	        			}
	        			catch(IllegalArgumentException e) {
	        				JOptionPane.showInternalMessageDialog(saveButton, "Ð´Èë´íÎó");
	        			}
	        			catch(Exception e) {
	        			    JOptionPane.showInternalMessageDialog(saveButton, "Ð´Èë´íÎó");
	        			}


	        		}
				}
				else {
					JOptionPane.showInternalMessageDialog(saveButton, "ÓÃ»§ÁÐ±íÎª¿Õ");
				}
				
			}
    		
    	});
    	
    	buttonPanel.add(openButton);
    	openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//openDialogFunc ODF = new openDialogFunc(calendarTableRow.toArray(), (monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼"));
				//ODF.setXY(WorkingInfoPanel.this.getX(), WorkingInfoPanel.this.getY());
				FileOperation FO = new FileOperation(calendarTableRow.toArray(), (monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼"));
				File workExcel = new File(".\\¿¼ÇÚ¼ÇÂ¼.xls");
				FO.readValue(workExcel);
				HSSFSheet sheet = FO.readBook.getSheet(monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼");
    		    try {
    		    	if(sheet.getPhysicalNumberOfRows() > 0) {
    					/*Clear table model*/
    					while(workTableModel.getRowCount() > 0) {
    						workTableModel.removeRow(workTableModel.getRowCount()-1);	
    					}
    					
    					int rows = sheet.getPhysicalNumberOfRows();
    					for(int i = 1; i < rows; i++) {
    						Vector<String> userVector = new Vector<>();
    						for(int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
    							userVector.add(sheet.getRow(i).getCell(j).toString());
    						}
    						workTableModel.addRow(userVector);
    					}
    			    }
    			    else {
    			    	JOptionPane.showInternalMessageDialog(openButton, monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼" + "Îª¿Õ");
    			    }
    		    }
        		catch(NullPointerException e) {
    			    JOptionPane.showInternalMessageDialog(openButton, monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼" + "Îª¿Õ");
        		}
        		catch(Exception e) {
    			    JOptionPane.showInternalMessageDialog(openButton, "¶ÁÈ¡´íÎó");
        		}
				
			}
    		
    	});
    	
    	buttonPanel.add(exportButton);
    	exportButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ExportWorkingInfo EWI = new ExportWorkingInfo(workTableModel.getDataVector(), calendarTableRow.toArray(), (monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼"));
				EWI.setXY(WorkingInfoPanel.this.getX(), WorkingInfoPanel.this.getY());
			}
    		
    	});
    	
    	getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
	/***************************************************
	 * Function Name:  placeButton
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place Button.
	 **************************************************/
    private void configCalendar() {
    	workCalendar = Calendar.getInstance();
    	monthLabel = new JLabel();
    	monthDays = workCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    	monthLabel.setText(updateLabel(0));
    	nextMonth = new JButton("ÏÂ¸öÔÂ");
    	nextMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				monthLabel.setText(updateLabel(1));
				refreshTable();
		    	
		    	FileOperation FO = new FileOperation(calendarTableRow.toArray(), (monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼"));
				File workExcel = new File(".\\¿¼ÇÚ¼ÇÂ¼.xls");
				FO.readValue(workExcel);
				HSSFSheet sheet = FO.readBook.getSheet(monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼");
			    try {
			    	if(sheet.getPhysicalNumberOfRows() > 0) {
						/*Clear table model*/
						while(workTableModel.getRowCount() > 0) {
							workTableModel.removeRow(workTableModel.getRowCount()-1);	
						}
						
						int rows = sheet.getPhysicalNumberOfRows();
						for(int i = 1; i < rows; i++) {
							Vector<String> userVector = new Vector<>();
							for(int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
								userVector.add(sheet.getRow(i).getCell(j).toString());
							}
							workTableModel.addRow(userVector);
						}
				    }
				    else {
				    	JOptionPane.showInternalMessageDialog(nextMonth, monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼" + "Îª¿Õ");
				    }
			    }
				catch(NullPointerException e) {
				    JOptionPane.showInternalMessageDialog(nextMonth, monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼" + "Îª¿Õ");
				}
				catch(Exception e) {
				    JOptionPane.showInternalMessageDialog(nextMonth, "¶ÁÈ¡´íÎó");
				}
			}
    		
    	});
    	
    	lastMonth = new JButton("ÉÏ¸öÔÂ");
    	lastMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				monthLabel.setText(updateLabel(-1));
				refreshTable();
		    	
		    	FileOperation FO = new FileOperation(calendarTableRow.toArray(), (monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼"));
				File workExcel = new File(".\\¿¼ÇÚ¼ÇÂ¼.xls");
				FO.readValue(workExcel);
				HSSFSheet sheet = FO.readBook.getSheet(monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼");
			    try {
			    	if(sheet.getPhysicalNumberOfRows() > 0) {
						/*Clear table model*/
						while(workTableModel.getRowCount() > 0) {
							workTableModel.removeRow(workTableModel.getRowCount()-1);	
						}
						
						int rows = sheet.getPhysicalNumberOfRows();
						for(int i = 1; i < rows; i++) {
							Vector<String> userVector = new Vector<>();
							for(int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
								userVector.add(sheet.getRow(i).getCell(j).toString());
							}
							workTableModel.addRow(userVector);
						}
				    }
				    else {
				    	JOptionPane.showInternalMessageDialog(lastMonth, monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼" + "Îª¿Õ");
				    }
			    }
				catch(NullPointerException e) {
				    JOptionPane.showInternalMessageDialog(lastMonth, monthLabel.getText()+"¿¼ÇÚ¼ÇÂ¼" + "Îª¿Õ");
				}
				catch(Exception e) {
				    JOptionPane.showInternalMessageDialog(lastMonth, "¶ÁÈ¡´íÎó");
				}
			}
    		
    	});
    	
    	labelPane = new JPanel();
    	labelPane.setLayout(new BorderLayout());
    	calendarPane = new JPanel();
    	calendarPane.add(lastMonth);
    	calendarPane.add(monthLabel);
    	calendarPane.add(nextMonth);
    	labelPane.add(calendarPane, BorderLayout.NORTH);
    	
    }
    
	/***************************************************
	 * Function Name:  updateLabel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  update label.
	 **************************************************/
	@SuppressWarnings("unused")
	private String updateLabel(int increment) {
		workCalendar.add(Calendar.MONTH, increment);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(workCalendar.getTime());
	}
    
	/***************************************************
	 * Function Name:  placeTable
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place table.
	 **************************************************/
	@SuppressWarnings("serial")
	private void placeTable() {
    	calendarTableRow = new Vector<>();
    	tablePanel = new JScrollPane();
    	calendarTableRow.add("¹¤ºÅ");
    	calendarTableRow.add("ÐÕÃû");
    	for(Integer i = 1; i <= monthDays; i++)
    	{
    		calendarTableRow.add(i.toString());
    	}
    	
    	workTableModel = new DefaultTableModel(null, calendarTableRow.toArray());
    	workTable = new JTable(workTableModel);
    	for(int j = 1; j <= monthDays; j++) {
        	TC = workTable.getColumnModel().getColumn(j + 1);
        	TCE = new DefaultCellEditor(BOX);
        	TC.setCellEditor(TCE);
    	}
    	//workTable.setInputVerifier();
    	
		tablePanel.setViewportView(workTable);
		
		labelPane.add(tablePanel, BorderLayout.CENTER);
    	getContentPane().add(labelPane, BorderLayout.CENTER);
    	
    }
	
	/***************************************************
	 * Function Name:  reflashTable
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Refresh table.
	 **************************************************/
	private void refreshTable() {
		calendarTableRow = new Vector<>();
    	calendarTableRow.add("¹¤ºÅ");
    	calendarTableRow.add("ÐÕÃû");
    	monthDays = workCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    	for(Integer i = 1; i <= monthDays; i++)
    	{
    		calendarTableRow.add(i.toString());
    	}
    	workTableModel.setColumnIdentifiers(calendarTableRow);
    	
    	for(int m = 0; m < workTableModel.getRowCount(); m++) {
    		for(int n = 2; n <= monthDays + 1; n++) {
    			workTableModel.setValueAt(" ", m, n);
    		}
    	}
    	
    	for(int j = 1; j <= monthDays; j++) {
        	TC = workTable.getColumnModel().getColumn(j + 1);
        	TCE = new DefaultCellEditor(BOX);
        	TC.setCellEditor(TCE);
	    }

	}
}
