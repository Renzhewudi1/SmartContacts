/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: UserMainFrame.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define main frame function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *                   2018-5-2     feng.yu           N/A              Update working record function for easier.
 *                   2018-6-20    feng.yu           N/A              Fix bug for disable menu bar function.
 *****************************************************************************************************************************/


package panelPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import javax.swing.*;
import javax.swing.event.*;

import PanelComponents.*;

public class UserMainFrame extends JFrame {
	
	private JPanel contentPanel;
	private DefineMenuBar menubar;
	private JDesktopPane desktop;
	
	public ContactsInfoPanel CIP;
	public ItemInfoPanel IIP;
	public WorkingInfoPanel WIP;
	public DocInfoPanel DIP;
	
	/***************************************************
	 * Function Name:  UserMainFrame
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  User main frame.
	 **************************************************/
	public UserMainFrame(int x, int y) {
		FrameConfigure(x, y);
	}
	
	/***************************************************
	 * Function Name:  FrameConfigure
	 * Author: feng.yu
	 * Input variable:  x, y
	 * Output variable: N/A
	 * Description:  User frame configure.
	 **************************************************/
	private void FrameConfigure(int x, int y) {
		setTitle("用户界面");
		setBounds(x, y, 500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktop  = new JDesktopPane();
		desktop.putClientProperty("JDesktopPane.dragMode", "outline");
		setContentPane(desktop);
		
		MenuBarDefFunc();
		
		setBackground(Color.WHITE);
		setVisible(true);
	}
	
	/***************************************************
	 * Function Name:  MenuBarDefFunc
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define menu bar function
	 **************************************************/
	public void MenuBarDefFunc()
	{
		
		menubar = new DefineMenuBar();
		
		/*Define new function*/
		menubar.newUserInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CIP = new ContactsInfoPanel();
				
				CIP.addInternalFrameListener(new InternalFrameListener() {

					@Override
					public void internalFrameActivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameClosed(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						menubar.newUserInfo.setEnabled(true);
					}

					@Override
					public void internalFrameClosing(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameDeactivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
					}

					@Override
					public void internalFrameDeiconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameIconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameOpened(InternalFrameEvent e) {
						// TODO Auto-generated method stub
					}
					
				});
				
				UserMainFrame.this.add(CIP);
				try {
					CIP.setSelected(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				menubar.newUserInfo.setEnabled(false);
				
			}
			
		});
		
		menubar.newItemInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
				IIP = new ItemInfoPanel();
				IIP.addInternalFrameListener(new InternalFrameListener() {

					@Override
					public void internalFrameActivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameClosed(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						menubar.newItemInfo.setEnabled(true);
					}

					@Override
					public void internalFrameClosing(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameDeactivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
					}

					@Override
					public void internalFrameDeiconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameIconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameOpened(InternalFrameEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				
				UserMainFrame.this.add(IIP);
				try {
					IIP.setSelected(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				menubar.newItemInfo.setEnabled(false);
			}
			
		});
		
		menubar.newWorkInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				WIP = new WorkingInfoPanel();
				WIP.addInternalFrameListener(new InternalFrameListener() {

					@Override
					public void internalFrameActivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameClosed(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						menubar.newWorkInfo.setEnabled(true);
					}

					@Override
					public void internalFrameClosing(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameDeactivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
					}

					@Override
					public void internalFrameDeiconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameIconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameOpened(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
				
				UserMainFrame.this.add(WIP);
				try {
					WIP.setSelected(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				menubar.newWorkInfo.setEnabled(false);
			}
			
		});
		
		menubar.newDocInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DIP = new DocInfoPanel();
				DIP.addInternalFrameListener(new InternalFrameListener() {

					@Override
					public void internalFrameActivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameClosed(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						menubar.newDocInfo.setEnabled(true);
					}

					@Override
					public void internalFrameClosing(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameDeactivated(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
					}

					@Override
					public void internalFrameDeiconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameIconified(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void internalFrameOpened(InternalFrameEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
				
				UserMainFrame.this.add(DIP);
				try {
					DIP.setSelected(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				menubar.newDocInfo.setEnabled(false);
			}
			
		});
		
		/*Define exit function*/
		menubar.exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserMainFrame.this.dispose();
			}
			
		});
		
		/*Define about smart contacts*/
		menubar.aboutSmartContacts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showInternalMessageDialog(menubar, "本程序为：智能通讯录V2.7\n开发人：余枫\n联系电话：13683270101");
			}
			
		});
		
		setJMenuBar(menubar);
	}
	
}
