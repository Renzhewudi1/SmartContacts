package panelPackage;
/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: ContactsInfoPanel.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define user panel.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *                   2018-6-6     feng.yu           N/A              Add focus to target row after search confirm.
 *****************************************************************************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import org.apache.poi.sl.usermodel.Background;

import DialogPackage.*;
import processPackage.*;

public class ContactsInfoPanel extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Table Variable*/
	public JTable userTable;
	private DefaultTableModel userTableModel;
	private JScrollPane userScrollPane;
	private Object[] defaultUserTableRow = {"工号","姓名","手机","邮箱","地址","入职日期","部门","职务"};
	private Object[][] userData = {{"","","","","","","",""}};
	
	/*Button Variable*/
	private JPanel  buttonPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton delButton;
	private JButton saveButton;
	private JButton readButton;
	
	/*Popup menu variable*/
	public JPopupMenu userPopupMenu;
	public JMenuItem addItem;
	public JMenuItem delItem;
	
	/*Search panel*/
	DefineUserSearchPanel userSearchPanel;

	/***************************************************
	 * Function Name:  UserPanel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define user panel.
	 **************************************************/
	public ContactsInfoPanel() 
	{
		super("通讯录", true, true, true, true);
		panelConfiguration();
		placeTable();
		placeSearchPanel();
		placeButton();
		//placePopupMenu();
	}
	
	/***************************************************
	 * Function Name:  placeButton
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place Button.
	 **************************************************/
    private void placeButton() {
		/*Configure add button*/
		addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addUserDialogFunc ADF = new addUserDialogFunc();
				JButton confirmButton = new JButton("确认");
				confirmButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if((new UserDataCheck().CheckEmpty(ADF.userIDField.getText(),
								ADF.userNameField.getText(),
								ADF.userPhoneField.getText()) == true)
							&&(new UserDataCheck().CheckPhoneNum(ADF.userPhoneField.getText())==true)
							&&(new UserDataCheck().CheckMailNum(ADF.userMailField.getText()) == true)) {
							Vector<String> userVector = new Vector<>();
							userVector.add(ADF.userIDField.getText());
							userVector.add(ADF.userNameField.getText());
							userVector.add(ADF.userPhoneField.getText());
							userVector.add(ADF.userMailField.getText());
							userVector.add(ADF.userAddrField.getText());
							userVector.add(ADF.userDateField.getText());
							userVector.add(ADF.userDepartmentField.getText());
							userVector.add(ADF.userTitleField.getText());
							userTableModel.addRow(userVector);
							ADF.dispose();
						}
						else {
							/*Do nothing*/
						}

					}
					
				});
				
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						ADF.dispose();
					}
					
				});
				JPanel butPan = new JPanel();
				butPan.add(confirmButton);
				butPan.add(cancelButton);
				ADF.add(butPan, BorderLayout.SOUTH);
				ADF.validate();
			}
			
		});
		
		/*Configure edit button*/
		editButton = new JButton("编辑");
		editButton.addActionListener(new ActionListener() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectRow;
				selectRow = userTable.getSelectedRow();
				if(selectRow != -1) {
					Vector<Vector<Object>> tmp_tableModel = new Vector<>();
					tmp_tableModel = (Vector)userTableModel.getDataVector();
					editUserDialogFunc EDF = new editUserDialogFunc(tmp_tableModel.elementAt(selectRow).elementAt(0).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(1).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(2).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(3).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(4).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(5).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(6).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(7).toString());
					
					JButton confirmButton = new JButton("确认");
					JButton cancelButton = new JButton("取消");
					JPanel butPan = new JPanel();
					butPan.add(confirmButton);
					confirmButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if((new UserDataCheck().CheckEmpty(EDF.userIDField.getText(),
									EDF.userNameField.getText(),
									EDF.userPhoneField.getText()) == true)
								&&(new UserDataCheck().CheckPhoneNum(EDF.userPhoneField.getText())==true)
								&&(new UserDataCheck().CheckMailNum(EDF.userMailField.getText()) == true)){
								userTableModel.setValueAt(EDF.userIDField.getText(), selectRow, 0);
								userTableModel.setValueAt(EDF.userNameField.getText(), selectRow, 1);
								userTableModel.setValueAt(EDF.userPhoneField.getText(), selectRow, 2);
								userTableModel.setValueAt(EDF.userMailField.getText(), selectRow, 3);
								userTableModel.setValueAt(EDF.userAddrField.getText(), selectRow, 4);
								userTableModel.setValueAt(EDF.userDateField.getText(), selectRow, 5);
								userTableModel.setValueAt(EDF.userDepartmentField.getText(), selectRow, 6);
								userTableModel.setValueAt(EDF.userTitleField.getText(), selectRow, 7);
								EDF.dispose();
							}
							else {
								
							}
						}						
					});
					butPan.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							EDF.dispose();
						}
						
					});
					
					EDF.add(butPan, BorderLayout.SOUTH);
					EDF.validate();
				}

			}
			
		});
		
		/*Configure delete button*/
		delButton = new JButton("删除");
		delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectRow;
				selectRow = userTable.getSelectedRow();
				if(selectRow != -1) {
					/*Remove selected row from table*/
					userTableModel.removeRow(selectRow);
				}
			}			
		});
		
		/*Configure saving button*/
		saveButton = new JButton("存储按钮");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				saveDialogFunc SDF = new saveDialogFunc(userTableModel.getDataVector(), defaultUserTableRow, "通讯录");
				SDF.setXY(ContactsInfoPanel.this.getX(), ContactsInfoPanel.this.getY());
			}
			
		});
		
		/*Configure reading button*/
		readButton = new JButton("读取按钮");
		readButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openDialogFunc ODF = new openDialogFunc(defaultUserTableRow, "通讯录");
				ODF.setXY(ContactsInfoPanel.this.getX(), ContactsInfoPanel.this.getY());
				if(ODF.readFlg == true) {
					/*Clear table model*/
					while(userTableModel.getRowCount() > 0) {
					    userTableModel.removeRow(userTableModel.getRowCount()-1);	
					}
					
					int rows = ODF.sheet.getPhysicalNumberOfRows();
					for(int i = 1; i < rows; i++) {
						Vector<String> userVector = new Vector<>();
						for(int j = 0; j < ODF.sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
							userVector.add(ODF.sheet.getRow(i).getCell(j).toString());
						}
						userTableModel.addRow(userVector);
					}
				}
			}
			
		});
		
		buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(delButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(readButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
   
	/***************************************************
	 * Function Name:  placeTable
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place table.
	 **************************************************/    
    private void placeTable() {
    	/*Define user table*/
		userTableModel = new DefaultTableModel(null, defaultUserTableRow);
		userTable = new JTable(userTableModel)
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};       
		
		userScrollPane = new JScrollPane();
		userScrollPane.setViewportView(userTable);
        getContentPane().add(userScrollPane, BorderLayout.CENTER);
    }
 
	/***************************************************
	 * Function Name:  placePopupMenu
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place popup menu.
	 **************************************************/
    private void placePopupMenu() {
    	/* Define popup menu */
		userPopupMenu = new JPopupMenu();
		userScrollPane.setComponentPopupMenu(userPopupMenu);
		addItem = new JMenuItem("新建");
		addItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<String> userVector = new Vector<>();
				for(int i = 0; i < userTable.getColumnCount(); i++) {
					
					userVector.add(" ");
				}
				userTableModel.addRow(userVector);
			}
			
		});
		delItem = new JMenuItem("删除");
		
		userPopupMenu.add(addItem);
		userPopupMenu.add(delItem);
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
	 * Function Name:  placeSearchPanel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Configure search panel.
	 **************************************************/
    private void placeSearchPanel() {
    	userSearchPanel = new DefineUserSearchPanel();    	
    	userSearchPanel.searchResultTable.addMouseListener(new getUserSelectedRowListener());
    	getContentPane().add(userSearchPanel, BorderLayout.NORTH);
    	
    }
    
	/***************************************************
	 * Function Name:  getUserSelectedRowListener
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Get user selected row.
	 **************************************************/
    private class getUserSelectedRowListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if((e.getButton() == MouseEvent.BUTTON1)
					&&(e.getClickCount() >= 2))
			{
				int rowSelectedNum = userSearchPanel.searchResultTable.getSelectedRow();
				Vector<String> SearchVector = userSearchPanel.searchResultTableModel.getDataVector().elementAt(rowSelectedNum);
				
				for(int l = 0; l < userTableModel.getRowCount(); l++) {
					if(SearchVector.equals(userTableModel.getDataVector().elementAt(l))) {
						userTable.setRowSelectionInterval(l, l);
						/*Auto focus to target row.*/
						Rectangle rect = userTable.getCellRect(l, 0, true);
						userTable.scrollRectToVisible(rect);
						userSearchPanel.removeAllData(userSearchPanel.searchResultTableModel);
						userSearchPanel.searchResultDialog.dispose();
					}
				}
				
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    public class DefineUserSearchPanel extends JPanel{
    	
    	private JTextField searchText;
    	@SuppressWarnings("rawtypes")
    	private JComboBox classSelect;
    	private JButton confirmButton;
    	private JButton cancelButton;
    	
    	public JScrollPane searchResultPane;
    	public JTable searchResultTable;
    	public DefaultTableModel searchResultTableModel;
    	public JDialog searchResultDialog;
    	
    	/***************************************************
    	 * Function Name:  DefineSearchPanel
    	 * Author: feng.yu
    	 * Input variable:  N/A
    	 * Output variable: N/A
    	 * Description:  Define search panel.
    	 **************************************************/
    	@SuppressWarnings("unchecked")
    	public DefineUserSearchPanel() {
    		
    		initialPanel(defaultUserTableRow);
    		
    		/*Define Search text field*/
    		searchText = new JTextField(20);
    		
    		/*Define combo box.*/
    		classSelect = new JComboBox();
    		for(int i = 0; i < userTableModel.getColumnCount(); i++) {
    			classSelect.addItem(userTableModel.getColumnName(i));
    		}
    		
    		/*Define confirm button*/
    		confirmButton = new JButton("确认按钮");
    		confirmButton.addActionListener(new confirmButtonListener(userTable, userTableModel, defaultUserTableRow));
    		
    		/*Define cancel button*/
    		cancelButton = new JButton("取消按钮");
    		cancelButton.addActionListener(new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				// TODO Auto-generated method stub
    				searchText.setText(null);
    			}
    			
    		});
    		
    		setLayout(new GridLayout(1, 4));
    		this.add(searchText);
    		this.add(classSelect);
    		this.add(confirmButton);
    		this.add(cancelButton);
    		
    	}
    	
    	private class confirmButtonListener implements ActionListener{

    		DefaultTableModel inputTableModel; 
    		Object[] columnRow;
    		JTable inputTable;
    		
    		private confirmButtonListener(JTable inputTable, DefaultTableModel inputTableModel, Object[] columnRow){
    			this.inputTableModel = inputTableModel;
    			this.columnRow = columnRow;
    			this.inputTable = inputTable;
    		}
    		
    		@SuppressWarnings("unused")
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			int rowCount = inputTableModel.getRowCount();
    			int columnNum = classSelect.getSelectedIndex();
    			
    			searchResultDialog = new JDialog();
    			searchResultPane = new JScrollPane();
    			
    			/*searchResultTableModel = new DefaultTableModel(null, columnRow);
    			searchResultTable = new JTable(searchResultTableModel) {
    				public boolean isCellEditable(int row, int column) {
    					return false;
    				}
    			};*/
    			searchResultPane.setViewportView(searchResultTable);
    			
    			for(int j = 0; j < rowCount; j++) {
    				String searchTextValue = searchText.getText();
    				String tableValue = inputTableModel.getValueAt(j, columnNum).toString();
    				int rst = tableValue.indexOf(searchTextValue);
    				if(rst >= 0) {
    					@SuppressWarnings("rawtypes")
    					Vector<Vector> tableVector = inputTableModel.getDataVector();
    					searchResultTableModel.addRow(tableVector.elementAt(j));
    				}
    			}
    			searchResultTable.setModel(searchResultTableModel);
    			
    			searchResultDialog = new JDialog();
    			searchResultDialog.setName("查找结果");
    			searchResultDialog.setSize(700, 500);
    			searchResultDialog.setLocation(ContactsInfoPanel.this.getX(), ContactsInfoPanel.this.getY());
    			
    			searchResultDialog.addWindowListener(new WindowListener() {

    				@Override
    				public void windowActivated(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowClosed(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					confirmButton.setEnabled(true);
    				}

    				@Override
    				public void windowClosing(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					confirmButton.setEnabled(true);
    					removeAllData(searchResultTableModel);
    					
    				}

    				@Override
    				public void windowDeactivated(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowDeiconified(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowIconified(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					
    				}

    				@Override
    				public void windowOpened(WindowEvent arg0) {
    					// TODO Auto-generated method stub
    					confirmButton.setEnabled(false);
    				}
    				
    			});
    			
    			if(searchResultTableModel.getDataVector().isEmpty()) {
    				JLabel emptyLabel = new JLabel();
    				emptyLabel.setText("     没有找到搜索结果");
    				searchResultDialog.add(emptyLabel, BorderLayout.CENTER);
    			}
    			else {
    				searchResultPane.setVisible(true);
    				searchResultDialog.add(searchResultPane);
    			}
    			
    			searchResultDialog.setVisible(true);
    		}
    		
    	}
    	
    	private class SearchMouseListener implements MouseListener{
    		
    		JTable inputTable;
    		DefaultTableModel inputTableModel;
    		
    		@SuppressWarnings("unused")
			public SearchMouseListener(JTable inputTable, DefaultTableModel inputTableModel) {
    			this.inputTable = inputTable;
    			this.inputTableModel = inputTableModel;
    			
    		}
    		
    		@SuppressWarnings({ "unused", "unchecked" })
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			// TODO Auto-generated method stub
    			if((e.getButton() == MouseEvent.BUTTON1)
    					&&(e.getClickCount() >= 2))
    			{
    				int rowSelectedNum = searchResultTable.getSelectedRow();
    				Vector<String> SearchVector = searchResultTableModel.getDataVector().elementAt(rowSelectedNum);
    				
    				for(int l = 0; l < inputTableModel.getRowCount(); l++) {
    					if(SearchVector.equals(inputTableModel.getDataVector().elementAt(l))) {
    						inputTable.isRowSelected(l);
    						searchResultDialog.dispose();
    					}
    				}
    				
    			}
    		}

    		@Override
    		public void mouseEntered(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void mouseExited(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void mousePressed(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void mouseReleased(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    			
    		}
    		
    	}
    	
    	private void initialPanel(Object[] columnRow) {
    		
    		searchResultTableModel = new DefaultTableModel(null, columnRow);
    		searchResultTable = new JTable(searchResultTableModel) {
    			public boolean isCellEditable(int row, int column) {
    				return false;
    			}
    		};
    	}
    	
    	public void removeAllData(DefaultTableModel inputTableModel) {
    		
    		while(inputTableModel.getRowCount() > 0) {
    			inputTableModel.removeRow(0);
    		}
    	}
    }
}
