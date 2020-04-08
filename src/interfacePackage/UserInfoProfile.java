/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: UserInfoProfile.java
 * Author:    feng.yu
 * Create Time: 2018-6-14
 * Description£ºThis file is used to define user information profile function.
 * Change History:    Time        Author           Failure           Description
 *                  2018-6-14     feng.yu           N/A              Create
 *****************************************************************************************************************************/

package interfacePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class UserInfoProfile {
	public String password = "";
	public File file;
	
	public boolean create(String userName){
		  boolean rtn_create = true;
		  file = new File("./UserInfo/" + userName);
		if(file != null) {
		    File directory = file.getParentFile(); /*get parent file path */
		    if(!directory.exists()) {              /* check file direction is exist or not*/ 
			    rtn_create = directory.mkdirs();   /*create direction*/
		    }
		    else {
		    	
		    }
		    if(!file.exists()) {
			    try {
				    rtn_create = file.createNewFile();
			    }
			    catch(IOException e) {
				    rtn_create = false;
			    }
		    }
		}
	    return rtn_create;	  
	}
	
	public boolean read(String userName) {
		Properties pro; /*Properties assemble*/
		FileInputStream is = null;
		boolean rtn_read = true;
		
		file = new File("./UserInfo/" + userName);
		
		if(!file.exists()) {    /*Configure file is not exists*/
			rtn_read = false;
		}
		else {
			try {
				is = new FileInputStream(file);
				pro = new Properties();
				pro.load(is);  /*read characters*/
				if(pro.getProperty("password:") == null) {
					password = "";
				}
				else {
					password = pro.getProperty("password:");  /*Read configuration value*/
				}
				
			    is.close();
			}
			catch(IOException ex) {
				ex.printStackTrace();
				rtn_read = false;
			}
		}
		
		return rtn_read;
	}
	
	public boolean write(String password, String userName) {
		this.password = password;
		Properties pro = null;
		FileOutputStream os = null;
		FileInputStream is = null;
		boolean rtn_write = true;
		
		file = new File("./UserInfo/" + userName);
		
		try {
			is = new FileInputStream(file);
			pro = new Properties();
			pro.load(is);  /*read characters*/
			is.close();
			
			os = new FileOutputStream(file);
			pro.setProperty("password:", password);
			pro.store(os, null); /*write porperty*/
			os.flush();
			os.close();
		    
		}
		catch(IOException ioe) {
			rtn_write = false;
			ioe.printStackTrace();
		}
		
		return rtn_write;
		
	}
}
