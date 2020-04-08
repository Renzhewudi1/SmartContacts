package panelPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;

import interfacePackage.*;

public class LoginPanel extends JFrame {
	
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JButton confirmButton;
	private JButton submitButton;
	private JButton cancelButton;
	private JPanel fieldPanel;
	private JPanel imagePanel;
	private String defaultUserName = "YuFeng";
	private String defaultPassword = "13683270101";
	private JPanel buttonPanel;
	
	private UserInfoProfile UIF;
	
	/***************************************************
	 * Function Name:  LoginPanel
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  LoginPanel configuration.
	 **************************************************/
	@SuppressWarnings({ "deprecation", "deprecation" })
	public LoginPanel(int x, int y) {
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
		
		passwordField = new JPasswordField(20);
		addFieldFunc(passwordField, 1, 1, 0, 0, 1, 1);

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
		confirmButton = new JButton("登录");
		submitButton = new JButton("注册");
		cancelButton = new JButton("取消");
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				/*Read password from file*/
				UIF = new UserInfoProfile();
				if(UIF.read(userNameField.getText())) {
					if(passwordField.getText().equals(UIF.password)) {
						new UserMainFrame(LoginPanel.this.getX(), LoginPanel.this.getY());
						LoginPanel.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(confirmButton, "密码错误，请重新输入。");
					}
				}
				else {
					JOptionPane.showMessageDialog(confirmButton, "用户名不存在，请重新输入。");
				}
			}
			
		});
		
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SubmitPanel SP = new SubmitPanel(LoginPanel.this.getX(), LoginPanel.this.getY());
				LoginPanel.this.dispose();
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new AccessPanel(LoginPanel.this.getX(), LoginPanel.this.getY());
				LoginPanel.this.dispose();
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
