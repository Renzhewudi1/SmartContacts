/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: DocDataCheck.java
 * Author:    feng.yu
 * Create Time: 2018-6-6
 * Description：This file is used to define documents data check function.
 * Change History:    Time        Author           Failure           Description
 *                   2018-6-6     feng.yu           N/A              Create
 *****************************************************************************************************************************/

package processPackage;

import javax.swing.JOptionPane;

public class DocDataCheck extends JOptionPane {
	
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
			                  String inputVersion) {
		boolean rtn_correct_flg = true;
		
		if(inputID.isEmpty()||
				inputName.isEmpty()||
				inputVersion.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "物品关键信息不能为空");
			rtn_correct_flg = false;
		}
		
		return rtn_correct_flg;
	}
}
