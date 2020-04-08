package processPackage;

/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: ItemDataCheck.java
 * Author:    feng.yu
 * Create Time: 2018-3-20
 * Description：This file is used to define item data check function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-3-20    feng.yu           N/A              Create
 *****************************************************************************************************************************/

import javax.swing.*;

public class ItemDataCheck extends JOptionPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/***************************************************
	 * Function Name:  CheckEmpty
	 * Author: feng.yu
	 * Input variable:  inputID, inputName,inputClass,inputNum
	 * Output variable: N/A
	 * Description:  Check input is empty or not.
	 **************************************************/
	public boolean CheckEmpty(String inputID, 
			                  String inputName, 
			                  String inputClass,
			                  String inputNum) {
		boolean rtn_correct_flg = true;
		
		if(inputID.isEmpty()||
				inputName.isEmpty()||
				inputClass.isEmpty()||
				inputNum.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "物品关键信息不能为空");
			rtn_correct_flg = false;
		}
		
		return rtn_correct_flg;
	}
	
	public boolean CheckPhoneNum(String inputPhone) {
		/*Compare phone number*/
		String regex = "^[+]?([0-9]{2,3})?-?[0-9]{8,11}";
		boolean rtn_correct_flg = true;
		if(!inputPhone.isEmpty()){
			if(!inputPhone.matches(regex)) {
				JOptionPane.showMessageDialog(this, "电话号码格式错误");
				rtn_correct_flg = false;
			}
		}
		else {
			/*Do nothing*/
		}
		return rtn_correct_flg;
	}
	
	public Boolean CheckMailNum(String inputMail) {
		/*Compare mail address*/
		String regex_1 = "^[a-zA-Z0-9.]{1,}@(([a-zA-Z0-9]-*){1,}\\.)([a-zA-Z\\.]{1,3})([a-zA-Z\\.]*)?";
		boolean rtn_correct_flg = true;
		if(!inputMail.isEmpty()){
			if(!inputMail.matches(regex_1)) {
				JOptionPane.showMessageDialog(this, "邮箱格式错误");
				rtn_correct_flg = false;
			}
		}
		else {
			/*Do nothing*/
		}
		return rtn_correct_flg;
	}
}
