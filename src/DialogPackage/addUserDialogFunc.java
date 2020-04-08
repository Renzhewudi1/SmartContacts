package DialogPackage;
/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: addDialogFunc.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define add contacts message panel.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *****************************************************************************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import PanelComponents.*;

public class addUserDialogFunc extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField userIDField;
	public JTextField userNameField;
	public JTextField userPhoneField;
	public JTextField userMailField;
	public JTextField userAddrField;
	public JTextField userDateField;
	public JButton userDateButton;
	public JTextField userDepartmentField;
	public JTextField userTitleField;

	private JPanel fieldPanel;
	
	/***************************************************
	 * Function Name:  addUserDialogFunc
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define add user info function.
	 **************************************************/
	public addUserDialogFunc() {
		setTitle("通讯录添加界面");
		setBounds(200, 200, 500, 300);
		setResizable(false);
		
		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		
		/*Add user ID*/
		JLabel userID = new JLabel();
		userID.setText("(*)用户ID: ");
		userID.setForeground(Color.RED);
		addFieldFunc(userID, 0, 0, 0, 0, 1, 1);
		
		userIDField = new JTextField(5);
		addFieldFunc(userIDField, 1, 0, 0, 0, 1, 1);
		
		/*Add user name*/
		JLabel userName = new JLabel();
		userName.setText("(*)用户姓名: ");
		userName.setForeground(Color.RED);
		addFieldFunc(userName, 2, 0, 0, 0, 1, 1);
		
		userNameField = new JTextField(10);
		addFieldFunc(userNameField, 3, 0, 0, 0, 1, 1);
		
		/*Add user phone*/
		JLabel userPhone = new JLabel();
		userPhone.setText("(*)用户手机: ");
		userPhone.setForeground(Color.RED);
		addFieldFunc(userPhone, 0, 2, 0, 0, 1, 1);
		
		userPhoneField = new JTextField(20);
		addFieldFunc(userPhoneField, 1, 2, 0, 0, 1, 3);
		
		/*Add user mail*/
		JLabel userMail = new JLabel();
		userMail.setText("用户邮箱: ");
		addFieldFunc(userMail, 0, 3, 0, 0, 1, 1);
				
		userMailField = new JTextField(20);
		addFieldFunc(userMailField, 1, 3, 0, 0, 1, 3);	
		
		/*Add user address*/
		JLabel userAddr = new JLabel();
		userAddr.setText("用户地址: ");
		addFieldFunc(userAddr, 0, 4, 0, 0, 1, 1);
				
		userAddrField = new JTextField(20);
		addFieldFunc(userAddrField, 1, 4, 0, 0, 1, 3);
				
		/*Add user on boarding date*/
		JLabel userDate = new JLabel();
		userDate.setText("入职时间: ");
		addFieldFunc(userDate, 0, 5, 0, 0, 1, 1);
		
		userDateField = new JTextField(20);
		userDateField.setEditable(false);
		addFieldFunc(userDateField, 1, 5, 0, 0, 1, 3);
		
		userDateButton = new JButton("...");
		userDateButton.addActionListener(new DateSelection());
		addFieldFunc(userDateButton, 5, 5, 0, 0, 1, 1);
		
		/*Add user on boarding date*/
		JLabel userDepartment = new JLabel();
		userDepartment.setText("职工部门: ");
		addFieldFunc(userDepartment, 0, 6, 0, 0, 1, 1);
		
		userDepartmentField = new JTextField(20);
		addFieldFunc(userDepartmentField, 1, 6, 0, 0, 1, 3);
		
		/*Add user on boarding date*/
		JLabel userTitle = new JLabel();
		userTitle.setText("职工职务: ");
		addFieldFunc(userTitle, 0, 7, 0, 0, 1, 1);
		
		userTitleField = new JTextField(20);
		addFieldFunc(userTitleField, 1, 7, 0, 0, 1, 3);
		
		this.add(fieldPanel, BorderLayout.CENTER);
		setVisible(true);
		
	}
	
	/***************************************************
	 * Function Name:  addFieldFunc
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define add components function.
	 **************************************************/
	public void addFieldFunc(Component paraComp,
			                 int gridx, 
			                 int gridy,
			                 int weightx,
			                 int weighty,
			                 int gridheight,
			                 int gridwidth
			                 ) {
		GridBagConstraints userCons = new GridBagConstraints();
		userCons.gridheight = gridheight;
		userCons.gridwidth = gridwidth;
		userCons.weightx = weightx;
		userCons.weighty = weighty;
		userCons.gridy = gridy;
		userCons.gridx = gridx;
		fieldPanel.add(paraComp, userCons);
	}
	
	/***************************************************
	 * Function Name:  DateSelection
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define date selection function.
	 **************************************************/
	private class DateSelection implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			calendar UCA = new calendar(addUserDialogFunc.this.getX(), 
					addUserDialogFunc.this.getY());
			UCA.monthTable.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if((e.getButton() == MouseEvent.BUTTON1)
							&&(e.getClickCount() >= 2))
					{
						int rowSelectedNum = UCA.monthTable.getSelectedRow();
						int columnSelectedNum = UCA.monthTable.getSelectedColumn();
						Object selectedDate = UCA.monthTable.getValueAt(rowSelectedNum, columnSelectedNum);
						userDateField.setText(UCA.currentMonthLabel.getText() + "-" + selectedDate);
						UCA.dispose();
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
				
			});
		}
		
	}
}
