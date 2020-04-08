/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: LinkCellRenderer.java
 * Author:    feng.yu
 * Create Time: 2018-6-11
 * Description£ºThis file is used to link cell renderer.
 * Change History:    Time        Author           Failure           Description
 *                   2018-6-11    feng.yu           N/A              Create
 *****************************************************************************************************************************/

package interfacePackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.table.DefaultTableCellRenderer;

public class LinkCellRenderer extends DefaultTableCellRenderer implements MouseInputListener {

	/*Current mouse row position*/
	private int row = -1;
	/*Current mouse column position*/
	private int col = -1;
	/*Target table*/
	private JTable table = null;
	
	public Component getTableCellRendererComponent(JTable table, Object value, 
			boolean isSelected, boolean hasFocus, int row, int col) {
		
		/*return to default status*/
		this.table = table;
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		
		this.setForeground(Color.BLACK);
		
		/*Change mouse cursor from arrow to hand.*/
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		this.setText(value.toString());
		
		/*if move mouse to renderer cell*/
		if((row == this.row)&&(col == this.col)) {
			/*WHen column is documents address*/
			if(col == 4) {
				/*Change word color to blue.*/
				this.setForeground(Color.BLUE);
				/*Change mouse form from arrow to hand.*/
				this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				/*Show text.*/
				this.setText("<html><u>" + value.toString() + "</u></html>");
			}
			this.setBackground(table.getSelectionBackground());
		}
		else if(isSelected) {
			/*Change selected cell color*/
			this.setForeground(table.getForeground());
			this.setBackground(table.getSelectionBackground());
		}
		else {
			this.setBackground(Color.WHITE);
		}
		
		return this;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = e.getPoint();
		int c = table.columnAtPoint(p);
		if(c != 4) {
			return;
		}
		
		int r = table.rowAtPoint(p);
		final Runtime runtime = Runtime.getRuntime();
		
		try {
			/*get target cell value.*/
			//URL url = new URL(table.getValueAt(r, c).toString());
			Desktop.getDesktop().open(new File(table.getValueAt(r, c).toString()));
		}
		catch(Exception ex) {
		    JOptionPane.showInternalMessageDialog(table, "Â·¾¶´íÎó");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	    if (table != null) {
            int oldRow = row;
            int oldCol = col;
            /*After mouse move from table, reset row and column value.*/
            row = -1;
            col = -1;
            /*Rectangel area when last cell is valid.*/
            if (oldRow != -1 && oldCol != -1) {
                Rectangle rect = table.getCellRect(oldRow, oldCol, false);
                table.repaint(rect);
            }
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(table != null) {
			/*Get mouse point.*/
			Point p = e.getPoint();
			int oldRow = row;
			int oldCol = col;
			row = table.rowAtPoint(p);
			col = table.columnAtPoint(p);
			/*Repaint old area*/
			if((oldRow != -1) && (oldCol != -1)) {
				Rectangle rect = table.getCellRect(oldRow, oldCol, false);
				table.repaint(rect);
			}
			
			/*Repaint new area*/
			if((row != -1) && (col != -1)) {
				Rectangle rect = table.getCellRect(row, col, false);
				table.repaint(rect);
			}
		}
	}

}
