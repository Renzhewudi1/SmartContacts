/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: DocInfoPanel.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define documents panel.
 * Change History:    Time        Author           Failure           Description
 *                  2018-6-6      feng.yu           N/A              Create
 *****************************************************************************************************************************/
package panelPackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hwpf.usermodel.Table;

import DialogPackage.addDocDialogFunc;
import DialogPackage.addUserDialogFunc;
import DialogPackage.editDocDialogFunc;
import DialogPackage.editUserDialogFunc;
import interfacePackage.LinkCellRenderer;
import processPackage.DocDataCheck;
import processPackage.UserDataCheck;
import processPackage.openDialogFunc;
import processPackage.saveDialogFunc;

public class DocInfoPanel extends JInternalFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JTable docTable;
	private DefaultTableModel docTableModel;
	private JScrollPane docScrollPane;
	private Object[] defaultDocTableRow = {"文档编号","文档名称","版本号","作者名称","文档位置","备注"};
	
	/*Button Variable*/
	private JPanel  buttonPanel;
	private JButton addButton;
	private JButton addAllButton;
	private JButton editButton;
	private JButton delButton;
	private JButton saveButton;
	private JButton readButton;
	
	/*Search panel*/
	DefineDocSearchPanel docSearchPanel;
	
	/***************************************************
	 * Function Name:  DocInfoPanel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define documents info.
	 **************************************************/
	public DocInfoPanel() {
		
		super("文档管理", true, true, true, true);
		panelConfiguration();
		placeTable();
		placeSearchPanel();
		placeButton();
	}

	/***************************************************
	 * Function Name:  panelConfiguration
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  Panel configuration.
	 **************************************************/
    private void panelConfiguration() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
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
    private void placeTable() {
    	/*Define user table*/
		docTableModel = new DefaultTableModel(null, defaultDocTableRow);
		docTable = new JTable(docTableModel)
		{
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};       
		
		LinkCellRenderer LCR = new LinkCellRenderer();
		docTable.setDefaultRenderer(Object.class, LCR);
		docTable.addMouseListener(LCR);
		docTable.addMouseMotionListener(LCR);
		
		docScrollPane = new JScrollPane();
		docScrollPane.setViewportView(docTable);
        getContentPane().add(docScrollPane, BorderLayout.CENTER);
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
		addButton = new JButton("添加文件");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addDocDialogFunc DDF = new addDocDialogFunc();
				JButton confirmButton = new JButton("确认");
				confirmButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if(new DocDataCheck().CheckEmpty(DDF.docIDField.getText(),
								DDF.docNameField.getText(),
								DDF.docVersionField.getText()) == true) {
							Vector<String> docVector = new Vector<>();
							docVector.add(DDF.docIDField.getText());
							docVector.add(DDF.docNameField.getText());
							docVector.add(DDF.docVersionField.getText());
							docVector.add(DDF.docAuthorField.getText());
							docVector.add(DDF.docAddrField.getText());
							docVector.add(DDF.docCommField.getText());
							docTableModel.addRow(docVector);
							DDF.dispose();
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
						DDF.dispose();
					}
					
				});
				JPanel butPan = new JPanel();
				butPan.add(confirmButton);
				butPan.add(cancelButton);
				DDF.add(butPan, BorderLayout.SOUTH);
				DDF.validate();
			}});
		
		/*Add fold doc*/
		addAllButton = new JButton("批量添加");
		addAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = chooser.showOpenDialog(addAllButton);
				if(option == JFileChooser.APPROVE_OPTION) {
					File folder = chooser.getSelectedFile();
					File[] folderFiles = folder.listFiles();
					for(File searchFile : folderFiles) {
						Vector<String> docVector = new Vector<>();
						docVector.add(" ");
						docVector.add(searchFile.getName());
						docVector.add(" ");
						docVector.add(" ");
						docVector.add(searchFile.getPath());
						docVector.add(" ");
						docTableModel.addRow(docVector);
					}
				}
				else {
					/*Do nothing*/
				}
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
				selectRow = docTable.getSelectedRow();
				if(selectRow != -1) {
					Vector<Vector<Object>> tmp_tableModel = new Vector<>();
					tmp_tableModel = (Vector)docTableModel.getDataVector();
					editDocDialogFunc EDDF = new editDocDialogFunc(tmp_tableModel.elementAt(selectRow).elementAt(0).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(1).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(2).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(3).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(4).toString(),
							tmp_tableModel.elementAt(selectRow).elementAt(5).toString());
					JButton confirmButton = new JButton("确认");
					JButton cancelButton = new JButton("取消");
					JPanel butPan = new JPanel();
					butPan.add(confirmButton);
					confirmButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if(new DocDataCheck().CheckEmpty(EDDF.docIDField.getText(),
									EDDF.docNameField.getText(),
									EDDF.docVersionField.getText()) == true){
								docTableModel.setValueAt(EDDF.docIDField.getText(), selectRow, 0);
								docTableModel.setValueAt(EDDF.docNameField.getText(), selectRow, 1);
								docTableModel.setValueAt(EDDF.docVersionField.getText(), selectRow, 2);
								docTableModel.setValueAt(EDDF.docAuthorField.getText(), selectRow, 3);
								docTableModel.setValueAt(EDDF.docAddrField.getText(), selectRow, 4);
								docTableModel.setValueAt(EDDF.docCommField.getText(), selectRow, 5);
								EDDF.dispose();
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
							EDDF.dispose();
						}
						
					});
					
					EDDF.add(butPan, BorderLayout.SOUTH);
					EDDF.validate();
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
				selectRow = docTable.getSelectedRow();
				if(selectRow != -1) {
					/*Remove selected row from table*/
					docTableModel.removeRow(selectRow);
				}
			}});
		
		/*Configure saving button*/
		saveButton = new JButton("存储按钮");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				saveDialogFunc SDF = new saveDialogFunc(docTableModel.getDataVector(), defaultDocTableRow, "文档管理");
				SDF.setXY(DocInfoPanel.this.getX(), DocInfoPanel.this.getY());
			}
			
		});
		
		/*Configure reading button*/
		readButton = new JButton("读取按钮");
		readButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openDialogFunc ODF = new openDialogFunc(defaultDocTableRow, "文档管理");
				ODF.setXY(DocInfoPanel.this.getX(), DocInfoPanel.this.getY());
				if(ODF.readFlg == true) {
					/*Clear table model*/
					while(docTableModel.getRowCount() > 0) {
					    docTableModel.removeRow(docTableModel.getRowCount()-1);	
					}
					
					int rows = ODF.sheet.getPhysicalNumberOfRows();
					for(int i = 1; i < rows; i++) {
						Vector<String> docVector = new Vector<>();
						for(int j = 0; j < ODF.sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
							docVector.add(ODF.sheet.getRow(i).getCell(j).toString());
						}
						docTableModel.addRow(docVector);
					}
				}
			}
			
		});
		
		buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(addAllButton);
		buttonPanel.add(editButton);
		buttonPanel.add(delButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(readButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
	/***************************************************
	 * Function Name:  placeSearchPanel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Configure search panel.
	 **************************************************/
    private void placeSearchPanel() {
    	docSearchPanel = new DefineDocSearchPanel();    	
    	docSearchPanel.searchResultTable.addMouseListener(new getDocSelectedRowListener());
    	getContentPane().add(docSearchPanel, BorderLayout.NORTH);
    	
    }
    
	/***************************************************
	 * Function Name:  getUserSelectedRowListener
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Get user selected row.
	 **************************************************/
    private class getDocSelectedRowListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if((e.getButton() == MouseEvent.BUTTON1)
					&&(e.getClickCount() >= 2))
			{
				int rowSelectedNum = docSearchPanel.searchResultTable.getSelectedRow();
				Vector<String> SearchVector = docSearchPanel.searchResultTableModel.getDataVector().elementAt(rowSelectedNum);
				
				for(int l = 0; l < docTableModel.getRowCount(); l++) {
					if(SearchVector.equals(docTableModel.getDataVector().elementAt(l))) {
						docTable.setRowSelectionInterval(l, l);
						/*Auto focus to target row.*/
						Rectangle rect = docTable.getCellRect(l, 0, true);
						docTable.scrollRectToVisible(rect);
						docSearchPanel.removeAllData(docSearchPanel.searchResultTableModel);
						docSearchPanel.searchResultDialog.dispose();
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
    
    public class DefineDocSearchPanel extends JPanel{
    	
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
    	public DefineDocSearchPanel() {
    		
    		initialPanel(defaultDocTableRow);
    		
    		/*Define Search text field*/
    		searchText = new JTextField(20);
    		
    		/*Define combo box.*/
    		classSelect = new JComboBox();
    		for(int i = 0; i < docTableModel.getColumnCount(); i++) {
    			classSelect.addItem(docTableModel.getColumnName(i));
    		}
    		
    		/*Define confirm button*/
    		confirmButton = new JButton("确认按钮");
    		confirmButton.addActionListener(new confirmButtonListener(docTable, docTableModel, defaultDocTableRow));
    		
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
    			searchResultDialog.setLocation(DocInfoPanel.this.getX(), DocInfoPanel.this.getY());
    			
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
