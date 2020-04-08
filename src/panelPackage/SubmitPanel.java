/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: SubmitPanel.java
 * Author:    feng.yu
 * Create Time: 2018-6-14
 * Description：This file is used to define submit panel.
 * Change History:    Time        Author           Failure           Description
 *                  2018-6-14     feng.yu           N/A              Create
 *****************************************************************************************************************************/

package panelPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interfacePackage.GetImage;
import interfacePackage.UserInfoProfile;

public class SubmitPanel extends JFrame {
	
	private JTextField userNameField;
	private JTextField userPasswordField;
	private JTextField confirmPasswordField;
	
	private JButton confirmButton;
	private JButton cancelButton;
	
	private JPanel fieldPanel;
	private JPanel buttonPanel;
	
	private UserInfoProfile UIP;
	
	private JPanel imagePanel;
	
	/***************************************************
	 * Function Name:  SubmitPanel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  submit panel configuration.
	 **************************************************/
	public SubmitPanel(int x, int y) {
		super();
		PlaceField();
		PlaceButton();
		PanelConfiguration(x, y);
	}
	
	/***************************************************
	 * Function Name:  addFieldFunc
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  Add field components.
	 **************************************************/
	private void addFieldFunc(Component paraComp,
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
	 * Function Name:  PlaceField
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  Place field configuration.
	 **************************************************/
	private void PlaceField(){
		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		fieldPanel.setBackground(null);
		fieldPanel.setOpaque(false);
		
		/*Define user name input*/
		JLabel userName = new JLabel();
		userName.setText("用户名:");
		userName.setForeground(Color.BLACK);
		userName.setBackground(Color.WHITE);
		addFieldFunc(userName, 0, 0, 0, 0, 1, 1);
		
		userNameField = new JTextField(20);
		addFieldFunc(userNameField, 1, 0, 0, 0, 1, 1);
		
		/*Define password input*/
		JLabel password = new JLabel();
		password.setText("密码:");
		password.setForeground(Color.BLACK);
		password.setBackground(Color.WHITE);
		addFieldFunc(password, 0, 1, 0, 0, 1, 1);
		
		userPasswordField = new JPasswordField(20);
		addFieldFunc(userPasswordField, 1, 1, 0, 0, 1, 1);
		
		/*Define confirm password input*/
		JLabel passwordConfirm = new JLabel();
		passwordConfirm.setText("确认密码:");
		passwordConfirm.setForeground(Color.BLACK);
		passwordConfirm.setBackground(Color.WHITE);
		addFieldFunc(passwordConfirm, 0, 2, 0, 0, 1, 1);
		
		confirmPasswordField = new JPasswordField(20);
		addFieldFunc(confirmPasswordField, 1, 2, 0, 0, 1, 1);

	}

	/***************************************************
	 * Function Name:  PlaceButton
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  Place button configuration.
	 **************************************************/
	private void PlaceButton() {
		
		/*Define button*/
		buttonPanel = new JPanel();
		confirmButton = new JButton("确认");
		cancelButton = new JButton("取消");
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UIP = new UserInfoProfile();
				if(!userNameField.getText().isEmpty()) {
					if(userPasswordField.getText().equals(confirmPasswordField.getText())) {
						if(!UIP.read(userNameField.getText())) {
							UIP.create(userNameField.getText());
						}
						else {
							/*Do nothing*/
						}
						UIP.write(userPasswordField.getText(), userNameField.getText());
						new LoginPanel(SubmitPanel.this.getX(), SubmitPanel.this.getY());
						SubmitPanel.this.dispose();
					}
					else {
						JOptionPane.showInternalMessageDialog(confirmButton, "密码不一致，请重新输入。");
					}
				}
				else {
				    JOptionPane.showInternalMessageDialog(confirmButton, "用户名不能为空");				
				}
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new LoginPanel(SubmitPanel.this.getX(), SubmitPanel.this.getY());
				SubmitPanel.this.dispose();
			}
			
		});
		
	}
	
	/***************************************************
	 * Function Name:  PlaceButton
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  Place panel configuration.
	 **************************************************/
	private void PanelConfiguration(int x, int y) {
		GetImage icon = new GetImage("Logo.png");
		setSize(icon.icon.getIconWidth(), icon.icon.getIconHeight());	
		imagePanel = ((JPanel)getContentPane());
		imagePanel.add(fieldPanel, BorderLayout.CENTER);
		imagePanel.add(buttonPanel, BorderLayout.SOUTH);
		imagePanel.setBackground(null);
		imagePanel.setOpaque(false);

		this.setTitle("登录界面");
		this.setLocation(x, y);
		this.getLayeredPane().add(icon.iconPanel, new Integer(Integer.MIN_VALUE));
		this.setResizable(false);
		this.setVisible(true);
	}
}
