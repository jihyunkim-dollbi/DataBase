package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.namager.*;


public class MenupanDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private static MenupanDAO dao;
	
	
	
	public void getConnection(){
		
		
		try
		{
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp//env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();	
		}
			
	}
	
	
	
	
	
	
	
	
	
	
	
}
