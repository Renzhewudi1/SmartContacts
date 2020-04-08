package interfacePackage;

import javax.swing.*;

public class GetImage {
	
	public ImageIcon icon;
	public JLabel iconLabel;
	public JPanel iconPanel;
	
	/***************************************************
	 * Function Name:  GetImage
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Get image from folder.
	 **************************************************/	
	public GetImage(String iconAddr) {
		
		icon = new ImageIcon(iconAddr);
		iconLabel = new JLabel();
		iconLabel.setIcon(icon);
		iconPanel = new JPanel();
		iconPanel.add(iconLabel);
		iconPanel.setSize(icon.getIconWidth(), icon.getIconHeight());
	}
}
