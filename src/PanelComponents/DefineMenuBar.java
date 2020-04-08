/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: DefineMenuBar.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description£ºThis file is used to define menu bar.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *****************************************************************************************************************************/

package PanelComponents;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import panelPackage.*;

public class DefineMenuBar extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5252928075954375268L;

	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu searchMenu;
	private JMenu windowMenu;
	private JMenu helpMenu;
	
	/*Menu*/
	public JMenu newMenu;
	
	/*Menu item*/
	public JMenuItem newUserInfo;
	public JMenuItem newItemInfo;
	public JMenuItem newWorkInfo;
	public JMenuItem newDocInfo;
	
	public JMenuItem aboutSmartContacts;
	
	
	public JMenuItem saveMenuItem;
	public JMenuItem saveAsMenuItem;
	public JMenuItem loadMenuItem;
	public JMenuItem exitMenuItem;
	
	
	/***************************************************
	 * Function Name:  DefineMenuBar
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define menu bar.
	 **************************************************/	
	public DefineMenuBar() {
		defineFileMenu();
		defineEditMenu();
		defineSearchMenu();
		defineWindowMenu();
		defineHelpMenu();
	}
	
	/***************************************************
	 * Function Name:  defineFileMenu
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define file menu.
	 **************************************************/
	private void defineFileMenu()
	{
		/*File Menu*/
		fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		/*Define new menu item*/
		newMenu = new JMenu("New");
		newMenu.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		fileMenu.add(newMenu);
		
		newUserInfo = new JMenuItem("New contacts information");
		newUserInfo.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		newMenu.add(newUserInfo);
		
		newItemInfo = new JMenuItem("New items information");
		newItemInfo.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		newMenu.add(newItemInfo);
		
		newWorkInfo = new JMenuItem("New working information");
		newWorkInfo.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		newMenu.add(newWorkInfo);
		
		newDocInfo = new JMenuItem("New documents information");
		newDocInfo.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		newMenu.add(newDocInfo);
		
		/*Define save menu item*/
		/*saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		fileMenu.add(saveMenuItem);	*/	
		/*Define save as menu item*/
		/*saveAsMenuItem = new JMenuItem("Save as");
		saveAsMenuItem.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		fileMenu.add(saveAsMenuItem);	*/
		/*Define load menu item*/
		/*loadMenuItem = new JMenuItem("Load");
		loadMenuItem.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		fileMenu.add(loadMenuItem);*/
		/*Add separator*/
		JSeparator seperator1 = new JSeparator();
		fileMenu.add(seperator1);
		/*Add exit*/
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setFont(new Font("new Time Rome", Font.PLAIN, 12));		
		fileMenu.add(exitMenuItem);
		
		this.add(fileMenu);
	}
	
	
	/***************************************************
	 * Function Name:  defineEditMenu
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define edit menu.
	 **************************************************/
	private void defineEditMenu()
	{
		/*Edit Menu*/
		editMenu = new JMenu("Edit");
		editMenu.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		
		this.add(editMenu);
	}
	
	
	/***************************************************
	 * Function Name:  defineSearchMenu
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define search menu.
	 **************************************************/
	private void defineSearchMenu()
	{
		/*Search Menu*/
		searchMenu = new JMenu("Search");
		searchMenu.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		
		this.add(searchMenu);
	}
	
	
	/***************************************************
	 * Function Name:  defineFileMenu
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define file menu.
	 **************************************************/
	private void defineWindowMenu()
	{
		/*Window Menu*/
		windowMenu = new JMenu("Window");
		windowMenu.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		
		this.add(windowMenu);
	}
	
	/***************************************************
	 * Function Name:  defineFileMenu
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define file menu.
	 **************************************************/
	private void defineHelpMenu()
	{
		/*Help Menu*/
		helpMenu = new JMenu("Help");
		helpMenu.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		
		aboutSmartContacts = new JMenuItem("New documents information");
		aboutSmartContacts.setFont(new Font("new Time Rome", Font.PLAIN, 12));
		helpMenu.add(aboutSmartContacts);
		
		this.add(helpMenu);
	}
}
