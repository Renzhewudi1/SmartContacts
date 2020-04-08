package DialogPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class editItemDialogFunc extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField itemIDField;
	public JTextField itemNameField;
	public JComboBox  itemClassField;
	public JTextField itemNumField;
	public JTextField itemPosField;
	public JTextField itemDesField;
	public JTextField itemStateField;
	public JTextField itemPriceField;
	public JTextField itemCommField;
	
	private JPanel fieldPanel;
	
	private String[] itemClassName = {"家具", "电子产品", "耗材", "家用电器", "家居用品", "服饰", "办公用品"};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public editItemDialogFunc(String inputItemID, 
            String inputItemName,
            String inputItemClass,
            String inputItemNum,
            String inputItemPos,
            String inputItemDes,
            String inputItemState,
            String inputItemPrice,
            String inputItemComm) {
		setTitle("物品管理修改界面");
		setBounds(200, 200, 500, 300);
		setResizable(false);
		
		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		
		/*Add item ID*/
		JLabel itemID = new JLabel();
		itemID.setText("(*)物品代码: ");
		itemID.setForeground(Color.RED);
		addFieldFunc(itemID, 0, 0, 0, 0, 1, 1);
		
		itemIDField = new JTextField(10);
		itemIDField.setText(inputItemID);
		addFieldFunc(itemIDField, 1, 0, 0, 0, 1, 1);
		
		/*Add item name*/
		JLabel itemName = new JLabel();
		itemName.setText("(*)物品名称: ");
		itemName.setForeground(Color.RED);
		addFieldFunc(itemName, 2, 0, 0, 0, 1, 1);
		
		itemNameField = new JTextField(10);
		itemNameField.setText(inputItemName);
		addFieldFunc(itemNameField, 3, 0, 0, 0, 1, 1);
		
		/*Add item class*/
		JLabel itemClass = new JLabel();
		itemClass.setText("(*)物品类型: ");
		itemClass.setForeground(Color.RED);
		addFieldFunc(itemClass, 0, 1, 0, 0, 1, 1);
		
		itemClassField = new JComboBox();
		for(int i = 0; i < itemClassName.length; i++) {
			itemClassField.addItem(itemClassName[i]);
		}
		itemClassField.setSelectedItem(inputItemClass);
		addFieldFunc(itemClassField, 1, 1, 0, 0, 1, 1);
		
		/*Add item number*/
		JLabel itemNum = new JLabel();
		itemNum.setText("(*)物品数量: ");
		itemNum.setForeground(Color.RED);
		addFieldFunc(itemNum, 2, 1, 0, 0, 1, 1);
				
		itemNumField = new JTextField(10);
		itemNumField.setText(inputItemNum);
		addFieldFunc(itemNumField, 3, 1, 0, 0, 1, 1);	
		
		/*Add item position*/
		JLabel itemPos = new JLabel();
		itemPos.setText("物品位置: ");
		addFieldFunc(itemPos, 0, 2, 0, 0, 1, 1);
				
		itemPosField = new JTextField(20);
		itemPosField.setText(inputItemPos);
		addFieldFunc(itemPosField, 1, 2, 0, 0, 1, 3);
				
		/*Add item description*/
		JLabel itemDes = new JLabel();
		itemDes.setText("物品描述: ");
		addFieldFunc(itemDes, 0, 3, 0, 0, 1, 1);
		
		itemDesField = new JTextField(20);
		itemPosField.setText(inputItemDes);
		addFieldFunc(itemDesField, 1, 3, 0, 0, 1, 3);
		
		/*Add item state*/
		JLabel itemState = new JLabel();
		itemState.setText("物品状态: ");
		addFieldFunc(itemState, 0, 4, 0, 0, 1, 1);
		
		itemStateField = new JTextField(20);
		itemStateField.setText(inputItemState);
		addFieldFunc(itemStateField, 1, 4, 0, 0, 1, 3);
		
		/*Add item price*/
		JLabel itemPrice = new JLabel();
		itemPrice.setText("价格: ");
		addFieldFunc(itemPrice, 0, 5, 0, 0, 1, 1);
		
		itemPriceField = new JTextField(20);
		itemPriceField.setText(inputItemPrice);
		addFieldFunc(itemPriceField, 1, 5, 0, 0, 1, 3);
		
		/*Add item comments*/
		JLabel itemComm = new JLabel();
		itemComm.setText("备注: ");
		addFieldFunc(itemComm, 0, 6, 0, 0, 1, 1);
		
		itemCommField = new JTextField(20);
		itemCommField.setText(inputItemComm);
		addFieldFunc(itemCommField, 1, 6, 0, 0, 1, 3);
		
		
		this.add(fieldPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
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
}
