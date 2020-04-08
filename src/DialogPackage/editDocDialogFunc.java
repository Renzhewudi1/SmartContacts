/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: addDocDialogFunc.java
 * Author:    feng.yu
 * Create Time: 2018-6-6
 * Description：This file is used to define add documents dialog function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-6-6     feng.yu           N/A              Create
 *****************************************************************************************************************************/

package DialogPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfacePackage.Profile;

public class editDocDialogFunc extends JDialog {
	
	private static final long serialVersionUID = 1L;
	public JTextField docIDField;
	public JTextField docNameField;
	public JTextField docVersionField;
	public JTextField docAuthorField;
	public JTextField docAddrField;
	public JTextField docCommField;
	public JButton docAddrButton;

	private JPanel fieldPanel;
	
	private Profile profile;
	/***************************************************
	 * Function Name:  addUserDialogFunc
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Define add user info function.
	 **************************************************/
	public editDocDialogFunc(String inputDocID, 
            String inputDocName,
            String inputDocVersion,
            String inputDocAuthor,
            String inputDocAddr,
            String inputDocComm
            ) {
		setTitle("通讯录添加界面");
		setBounds(200, 200, 500, 300);
		setResizable(false);
		
		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		
		/*Add user ID*/
		JLabel docID = new JLabel();
		docID.setText("(*)文档ID: ");
		docID.setForeground(Color.RED);
		addFieldFunc(docID, 0, 0, 0, 0, 1, 1);
		
		docIDField = new JTextField(5);
		docIDField.setText(inputDocID);
		addFieldFunc(docIDField, 1, 0, 0, 0, 1, 1);
		
		/*Add user name*/
		JLabel docName = new JLabel();
		docName.setText("(*)文档名称: ");
		docName.setForeground(Color.RED);
		addFieldFunc(docName, 2, 0, 0, 0, 1, 1);
		
		docNameField = new JTextField(10);
		docNameField.setText(inputDocName);
		addFieldFunc(docNameField, 3, 0, 0, 0, 1, 1);
		
		/*Add user phone*/
		JLabel docVersion = new JLabel();
		docVersion.setText("(*)文档版本号: ");
		docVersion.setForeground(Color.RED);
		addFieldFunc(docVersion, 0, 2, 0, 0, 1, 1);
		
		docVersionField = new JTextField(20);
		docVersionField.setText(inputDocVersion);
		addFieldFunc(docVersionField, 1, 2, 0, 0, 1, 3);
		
		/*Add user mail*/
		JLabel docAuthor = new JLabel();
		docAuthor.setText("文档作者: ");
		addFieldFunc(docAuthor, 0, 3, 0, 0, 1, 1);
				
		docAuthorField = new JTextField(20);
		docAuthorField.setText(inputDocAuthor);
		addFieldFunc(docAuthorField, 1, 3, 0, 0, 1, 3);	
		
		/*Add user address*/
		JLabel docAddr = new JLabel();
		docAddr.setText("文档地址: ");
		addFieldFunc(docAddr, 0, 4, 0, 0, 1, 1);
				
		docAddrField = new JTextField(20);
		docAddrField.setText(inputDocAddr);
		docAddrField.setEditable(false);
		addFieldFunc(docAddrField, 1, 4, 0, 0, 1, 3);
		
		
		docAddrButton = new JButton("...");
		docAddrButton.addActionListener(new SelectFile());
		addFieldFunc(docAddrButton, 5, 4, 0, 0, 1, 1);
				
		/*Add user on boarding date*/
		JLabel docComm = new JLabel();
		docComm.setText("备注: ");
		addFieldFunc(docComm, 0, 5, 0, 0, 1, 1);
		
		docCommField = new JTextField(20);
		docCommField.setText(inputDocComm);
		addFieldFunc(docCommField, 1, 5, 0, 0, 1, 3);
		
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
		GridBagConstraints docCons = new GridBagConstraints();
		docCons.gridheight = gridheight;
		docCons.gridwidth = gridwidth;
		docCons.weightx = weightx;
		docCons.weighty = weighty;
		docCons.gridy = gridy;
		docCons.gridx = gridx;
		fieldPanel.add(paraComp, docCons);
	}
	
	/***************************************************
	 * Function Name:  Select file
	 * Author: feng.yu
	 * Input variable:  N/A
	 * Output variable: N/A
	 * Description:  Select file address function.
	 **************************************************/
	private class SelectFile implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			profile = new Profile();
	    	if(!profile.read("目标文档路径")) {
	    		profile.latestPath = "C:/Users/huoshanshan/Documents";
	    	}
	    	else {
	    		/*Do nothing*/
	    	}
			JFileChooser chooser = new JFileChooser(new File(profile.latestPath));
        	int option = chooser.showDialog(docAddrButton, "选择文件地址");
        	if(option == JFileChooser.APPROVE_OPTION) {
        		String path = chooser.getSelectedFile().getParent()
						+ "\\" + chooser.getSelectedFile().getName();
        		docAddrField.setText(path);
        		profile.write(chooser.getSelectedFile().toString(), "目标文档路径");
        	}
		}
		
	}
}
