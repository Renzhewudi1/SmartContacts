/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: calendar.java
 * Author:    feng.yu
 * Create Time: 2018-4-2
 * Description：This file is used to define a calendar.
 * Change History:    Time        Author           Failure           Description
 *                   2018-4-2     feng.yu           N/A              Create
 *****************************************************************************************************************************/

package PanelComponents;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class calendar extends JDialog {

	private JButton lastMonthButton;
	private JButton nextMonthButton;
	public JLabel currentMonthLabel;
	public JTable monthTable;
	private Calendar calendar;
	private JPanel buttonPanel;
	private JScrollPane tablePanel;
	
	/***************************************************
	 * Function Name:  DefineMenuBar
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define calendar.
	 **************************************************/
	public calendar(int x, int y) {
		
		calendar = Calendar.getInstance();
		
		lastMonthButton = new JButton("上个月");
		lastMonthButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				currentMonthLabel.setText(updateLabel(-1));
				updateTable(calendar);
			}
			
		});
		nextMonthButton = new JButton("下个月");
		nextMonthButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				currentMonthLabel.setText(updateLabel(1));
				updateTable(calendar);
			}
			
		});
		
		/*Define label*/
		currentMonthLabel = new JLabel();
		currentMonthLabel.setText(updateLabel(0));
		
		/*Define Table*/
		monthTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		updateTable(calendar);
		buttonPanel = new JPanel();
		tablePanel = new JScrollPane();
		
		/*Define button*/
		buttonPanel.add(lastMonthButton);
		buttonPanel.add(currentMonthLabel);
		buttonPanel.add(nextMonthButton);
			
		tablePanel.setViewportView(monthTable);
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(tablePanel, BorderLayout.CENTER);
		this.setSize(300, 200);
		this.setLocation(x, y);
		this.setVisible(true);
		
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
		calendar.add(Calendar.MONTH, increment);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(calendar.getTime());
	}
	
	/***************************************************
	 * Function Name:  updateTable
	 * Author: feng.yu
	 * Input variable:  calendar
	 * Output variable: N/A
	 * Description:  update table.
	 **************************************************/
	@SuppressWarnings("unused")
	private void updateTable(Calendar calendar) {
		String[] weeks = new DateFormatSymbols().getShortWeekdays();
		String[] realWeeks = new String[7];
		for(int i = 1; i < weeks.length; i++) {
			realWeeks[i - 1] = weeks[i];
		}
		int today = calendar.get(Calendar.DATE);
		int monthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		int firstDay = calendar.getFirstDayOfWeek();
		int whiteDay = weekday - firstDay;
		Object[][] days = new Object[6][7];
		for(int i = 1; i <= monthDays; i++) {
			days[(i - 1 + whiteDay)/7][(i - 1 + whiteDay)%7] = i;
		}
		DefaultTableModel model = (DefaultTableModel)monthTable.getModel();
		model.setDataVector(days, realWeeks);
		monthTable.setModel(model);
		monthTable.setRowSelectionInterval(0, (today - 1 + whiteDay)/7);
		monthTable.setColumnSelectionInterval(0, (today - 1 + whiteDay)%7);
	}
}
