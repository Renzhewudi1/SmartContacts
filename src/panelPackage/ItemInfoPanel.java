/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: UserPanel.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define item panel.
 * Change History:    Time        Author           Failure           Description
 *                   2018-4-20    feng.yu           N/A              Create
 *                   2018-6-6     feng.yu           N/A              1. Add focus to target row after search confirm.
 *                                                                   2. Fix popup menu cannot call out at Table area.
 *****************************************************************************************************************************/

package panelPackage;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import org.apache.poi.sl.usermodel.Background;

import DialogPackage.*;
import PanelComponents.*;
import processPackage.*;

public class ItemInfoPanel extends JInternalFrame{
	
	public JTable itemTable;
	private DefaultTableModel itemTableModel;
	private JScrollPane itemScrollPane;
	private Object[] defaultItemTableRow = {"物品编号","物品名称","物品类型","物品个数","物品位置","物品描述","当前状态","价格","备注"};
	
	/*Popup menu variable*/
	public JPopupMenu itemPopupMenu;
	public JMenuItem addItem;
	public JMenuItem editItem;
	public JMenuItem delItem;
	
	/*Button item define*/
	private JPanel  buttonPanel;
	private JButton saveButton;
	private JButton readButton;
	
	/*Search panel*/
	DefineItemSearchPanel itemSearchPanel;
	
	/***************************************************
	 * Function Name:  Item panel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define item panel.
	 **************************************************/
	public ItemInfoPanel()
	{
		super("物品管理", true, true, true, true);
		panelConfiguration();
		placeTable();
		placeSearchPanel();
		placePopupMenu();
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
		setSize(300, 200);
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
	 * Function Name:  placeTable
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place table.
	 **************************************************/    
    @SuppressWarnings("serial")
	private void placeTable() {
    	/*Define user table*/
    	itemTableModel = new DefaultTableModel(null, defaultItemTableRow);
		itemTable = new JTable(itemTableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};	
		
		itemScrollPane = new JScrollPane();
		itemScrollPane.setViewportView(itemTable);
        getContentPane().add(itemScrollPane, BorderLayout.CENTER);
    }
    
    /***************************************************
	 * Function Name:  placePopupMenu
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place popup menu.
	 **************************************************/
    private void placePopupMenu() {
    	/*Define popup menu*/
    	itemPopupMenu = new JPopupMenu();
		itemScrollPane.setComponentPopupMenu(itemPopupMenu);
		itemTable.setComponentPopupMenu(itemPopupMenu);
		addItem = new JMenuItem("新建");
		addItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addItemDialogFunc AIF = new addItemDialogFunc();
				JButton confirmButton = new JButton("确认");
				confirmButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(new ItemDataCheck().CheckEmpty(AIF.itemIDField.getText(),
								AIF.itemNameField.getText(),
								AIF.itemClassField.getSelectedItem().toString(),
								AIF.itemNumField.getText()) == true) {
							Vector<String> itemVector = new Vector<>();
							itemVector.add(AIF.itemIDField.getText());
							itemVector.add(AIF.itemNameField.getText());
							itemVector.add(AIF.itemClassField.getSelectedItem().toString());
							itemVector.add(AIF.itemNumField.getText());
							itemVector.add(AIF.itemPosField.getText());
							itemVector.add(AIF.itemDesField.getText());
							itemVector.add(AIF.itemStateField.getText());
							itemVector.add(AIF.itemPriceField.getText());
							itemVector.add(AIF.itemCommField.getText());
							
							itemTableModel.addRow(itemVector);
							AIF.dispose();
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
						AIF.dispose();
					}
					
				});
				JPanel butPan = new JPanel();
				butPan.add(confirmButton);
				butPan.add(cancelButton);
				AIF.add(butPan, BorderLayout.SOUTH);
				AIF.validate();
			}
			
		});
		
		editItem = new JMenuItem("编辑");
		editItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectRow;
				selectRow = itemTable.getSelectedRow();
				if(selectRow != -1) {
					Vector<Vector<Object>> tmp_tableModel = new Vector<>();
					tmp_tableModel = (Vector)itemTableModel.getDataVector();
					editItemDialogFunc EIF = new editItemDialogFunc(tmp_tableModel.elementAt(selectRow).elementAt(0).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(1).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(2).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(3).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(4).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(5).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(6).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(7).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(8).toString());
					
					JButton confirmButton = new JButton("确认");
					JButton cancelButton = new JButton("取消");
					JPanel butPan = new JPanel();
					butPan.add(confirmButton);
					confirmButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if(new ItemDataCheck().CheckEmpty(EIF.itemIDField.getText(),
									EIF.itemNameField.getText(),
									EIF.itemClassField.getSelectedItem().toString(),
									EIF.itemNumField.getText()) == true){
								itemTableModel.setValueAt(EIF.itemIDField.getText(), selectRow, 0);
								itemTableModel.setValueAt(EIF.itemNameField.getText(), selectRow, 1);
								itemTableModel.setValueAt(EIF.itemClassField.getSelectedItem().toString(), selectRow, 2);
								itemTableModel.setValueAt(EIF.itemNumField.getText(), selectRow, 3);
								itemTableModel.setValueAt(EIF.itemPosField.getText(), selectRow, 4);
								itemTableModel.setValueAt(EIF.itemDesField.getText(), selectRow, 5);
								itemTableModel.setValueAt(EIF.itemStateField.getText(), selectRow, 6);
								itemTableModel.setValueAt(EIF.itemPriceField.getText(), selectRow, 7);
								itemTableModel.setValueAt(EIF.itemCommField.getText(), selectRow, 8);
								EIF.dispose();
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
							EIF.dispose();
						}
						
					});
					
					EIF.add(butPan, BorderLayout.SOUTH);
					EIF.validate();
				}

			}
			
		});
		
		delItem = new JMenuItem("删除");
		delItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectRow;
				selectRow = itemTable.getSelectedRow();
				if(selectRow != -1) {
					/*Remove selected row from table*/
					itemTableModel.removeRow(selectRow);
				}
			}
			
		});
		
		itemPopupMenu.add(addItem);
		itemPopupMenu.add(editItem);
		itemPopupMenu.add(delItem);
    }
    
	/***************************************************
	 * Function Name:  placeButton
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Place Button.
	 **************************************************/
    private void placeButton() {
    	
		/*Configure saving button*/
		saveButton = new JButton("存储按钮");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				saveDialogFunc SDF = new saveDialogFunc(itemTableModel.getDataVector(), defaultItemTableRow, "物品管理");
				SDF.setXY(ItemInfoPanel.this.getX(), ItemInfoPanel.this.getY());
			}
			
		});
		
		/*Configure reading button*/
		readButton = new JButton("读取按钮");
		readButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openDialogFunc ODF = new openDialogFunc(defaultItemTableRow, "物品管理");
				ODF.setXY(ItemInfoPanel.this.getX(), ItemInfoPanel.this.getY());
				if(ODF.readFlg == true) {
					/*Clear table model*/
					while(itemTableModel.getRowCount() > 0) {
						itemTableModel.removeRow(itemTableModel.getRowCount()-1);	
					}
					
					int rows = ODF.sheet.getPhysicalNumberOfRows();
					for(int i = 1; i < rows; i++) {
						Vector<String> userVector = new Vector<>();
						for(int j = 0; j < ODF.sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
							userVector.add(ODF.sheet.getRow(i).getCell(j).toString());
						}
						itemTableModel.addRow(userVector);
					}
				}
			}
			
		});
		
		buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(readButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
	/***************************************************
	 * Function Name:  panelConfiguration
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  Panel configuration.
	 **************************************************/
	private void placeSearchPanel() {
    	itemSearchPanel = new DefineItemSearchPanel();
    	itemSearchPanel.searchResultTable.addMouseListener(new getItemSelectedRowListener());
    	getContentPane().add(itemSearchPanel, BorderLayout.NORTH);
    }
	
	  
		/***************************************************
		 * Function Name:  panelConfiguration
		 * Author: feng.yu
		 * Input variable:  N/A
		 * Output variable: N/A
		 * Description:  Panel configuration.
		 **************************************************/
	    private class getItemSelectedRowListener implements MouseListener{

			@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if((e.getButton() == MouseEvent.BUTTON1)
						&&(e.getClickCount() >= 2))
				{
					@SuppressWarnings("static-access")
					int rowSelectedNum = itemSearchPanel.searchResultTable.getSelectedRow();
					
					try {
						
						Vector<String> SearchVector = itemSearchPanel.searchResultTableModel.getDataVector().elementAt(rowSelectedNum);
					
						for(int l = 0; l < itemTableModel.getRowCount(); l++) {
							if(SearchVector.equals(itemTableModel.getDataVector().elementAt(l))) {
								itemTable.setRowSelectionInterval(l, l);
								/*Auto focus to target row.*/
								Rectangle rect = itemTable.getCellRect(l, 0, true);
								itemTable.scrollRectToVisible(rect);
								itemSearchPanel.removeAllData(itemSearchPanel.searchResultTableModel);
								itemSearchPanel.searchResultDialog.dispose();
							}
						}
					}
					catch(Exception e1) {
						e1.printStackTrace();
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
		
	    public class DefineItemSearchPanel extends JPanel{
	    	
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
	    	public DefineItemSearchPanel() {
	    		
	    		initialPanel(defaultItemTableRow);
	    		
	    		/*Define Search text field*/
	    		searchText = new JTextField(20);
	    		
	    		/*Define combo box.*/
	    		classSelect = new JComboBox();
	    		for(int i = 0; i < itemTableModel.getColumnCount(); i++) {
	    			classSelect.addItem(itemTableModel.getColumnName(i));
	    		}
	    		
	    		/*Define confirm button*/
	    		confirmButton = new JButton("确认按钮");
	    		confirmButton.addActionListener(new confirmButtonListener(itemTable, itemTableModel, defaultItemTableRow));
	    		
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
	    			searchResultDialog.setLocation(ItemInfoPanel.this.getX(), ItemInfoPanel.this.getY());
	    			
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
	    	
	    	@SuppressWarnings("unused")
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
	    	
	    	@SuppressWarnings("serial")
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
