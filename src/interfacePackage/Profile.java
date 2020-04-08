/******************************************************************************************************************************
 * The copy right of this project is belonged to HuaYiDa technology ,CO.,LTD. 
 * Project Name: Smart contacts
 * File Name: Profile.java
 * Author:    feng.yu
 * Create Time: 2018-6-14
 * Description：This file is used to define profile function.
 * Change History:    Time        Author           Failure           Description
 *                  2018-6-14     feng.yu           N/A              Create
 *****************************************************************************************************************************/

package interfacePackage;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Profile {
	public String latestPath = "C:/Users/huoshanshan/Documents";
	public File file = new File("./set.ini");
	
	public boolean create(){
		  boolean rtn_create = true;  
		if(file != null) {
		    File directory = file.getParentFile(); /*get parent file path */
		    if(!directory.exists()) {              /* check file direction is exist or not*/ 
			    rtn_create = directory.mkdirs();   /*create direction*/
		    }
		    else { /*direction is exists.*/
			    if(!file.exists()) {
				    try {
					    rtn_create = file.createNewFile();
				    }
				    catch(IOException e) {
					    rtn_create = false;
				    }
			    }
		    }
		}
	    return rtn_create;	  
	}
	
	public boolean read(String inputTitle) {
		Properties pro; /*Properties assemble*/
		FileInputStream is = null;
		boolean rtn_read = true;
		
		if(!file.exists()) {    /*Configure file is not exists*/
			rtn_read = create();   /*Create one*/
		
		    if(rtn_read == true) {  /*create configuration file*/
		    	rtn_read = write(latestPath, inputTitle);
		    }
		    else {
		    	JOptionPane.showConfirmDialog(null, "对不起，不存在配置文件信息！", "错误",
		    			JOptionPane.YES_NO_OPTION,
		    			JOptionPane.ERROR_MESSAGE);
		    }
		}
		else {
			try {
				is = new FileInputStream(file);
				pro = new Properties();
				pro.load(is);  /*read characters*/
				if(pro.getProperty("latestPath" + inputTitle) == null) {
					latestPath = "C:/Users/huoshanshan/Documents";
				}
				else {
					latestPath = pro.getProperty("latestPath" + inputTitle);  /*Read configuration value*/
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
	
	public boolean write(String latestPath, String inputTitle) {
		this.latestPath = latestPath;
		Properties pro = null;
		FileOutputStream os = null;
		FileInputStream is = null;
		boolean rtn_write = true;
		
		try {
			is = new FileInputStream(file);
			pro = new Properties();
			pro.load(is);  /*read characters*/
			is.close();
			
			os = new FileOutputStream(file);
			pro.setProperty("latestPath" + inputTitle, latestPath);
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
