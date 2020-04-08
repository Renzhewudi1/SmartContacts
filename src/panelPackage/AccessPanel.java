package panelPackage;
/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: AccessPanel.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description£ºThis file is used to define access panel.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *****************************************************************************************************************************/

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;

import interfacePackage.*;

public class AccessPanel extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton accessButton;
    
	/***************************************************
	 * Function Name:  AccessPanel
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Access panel define.
	 **************************************************/
	public AccessPanel(int x, int y) {
		super();
		PlaceConfiguration(x, y);
    }
	
	/***************************************************
	 * Function Name:  actionPerformed
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Access button action performed.
	 **************************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		new LoginPanel(AccessPanel.this.getX(), AccessPanel.this.getY());
		this.dispose();
	}
	
	/***************************************************
	 * Function Name:  PlaceConfiguration
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Access panel place configuration.
	 **************************************************/	
	public void PlaceConfiguration(int x, int y) {
    	setTitle("Ö÷Ò³Ãæ");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	GetImage icon = new GetImage("Logo.png");
    	this.setBounds(x, y, icon.icon.getIconWidth(), icon.icon.getIconHeight());
    	getContentPane().add(icon.iconPanel, BorderLayout.CENTER);
    	
    	accessButton = new JButton("µÇÂ¼°´Å¥");
    	accessButton.addActionListener(this);
    	accessButton.addKeyListener(new KeyBorderListener());
    	
    	JPanel jpb = new JPanel();
    	jpb.add(accessButton);
    	getContentPane().add(jpb, BorderLayout.SOUTH);
    	this.setResizable(false);
    	setVisible(true);
	}
	
	private class KeyBorderListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				new LoginPanel(AccessPanel.this.getX(), AccessPanel.this.getY());
				AccessPanel.this.dispose();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
