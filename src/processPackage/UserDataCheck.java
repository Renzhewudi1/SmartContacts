package processPackage;

import javax.swing.*;

public class UserDataCheck extends JOptionPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/***************************************************
	 * Function Name:  CheckEmpty
	 * Author: feng.yu
	 * Input variable:  inputID, inputName,inputPhone
	 * Output variable: N/A
	 * Description:  Check input is empty or not.
	 **************************************************/
	public boolean CheckEmpty(String inputID, String inputName, String inputPhone) {
		boolean rtn_correct_flg = true;
		
		if(inputID.isEmpty()||
				inputName.isEmpty()||
				inputPhone.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "ID,姓名和电话不能为空");
			rtn_correct_flg = false;
		}
		
		return rtn_correct_flg;
	}
	
	/***************************************************
	 * Function Name:  CheckPhoneNum
	 * Author: feng.yu
	 * Input variable:  inputMail
	 * Output variable: N/A
	 * Description:  Check input phone number formation is correct or not.
	 **************************************************/
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
	
	/***************************************************
	 * Function Name:  CheckMailNum
	 * Author: feng.yu
	 * Input variable:  inputMail
	 * Output variable: N/A
	 * Description:  Check input mail formation is correct or not.
	 **************************************************/
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
