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
	//private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	
	
	/*
	
	pool사용하기 위해 ! 필요없어짐! 
	public FoodDAO(){
		
		try
		{
			
		Class.forName("oracle.jdbc.driver.OracleDriver");
			
		}catch(Exception ex)
		{
			
			System.out.println(ex.getMessage());
			
		}
		
	}
	
	*/
	
	
	
	public void getConnection(){
		
		
		try
		{
			
          //conn=DriverManager.getConnection(URL, "hr", "happy");
			
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp//env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();	
		}
			
	}
	
	
	public void disConnection()
	{
		
		try
		{
			//닫기 ps, conn
			if(ps!=null) ps.close(); // close는 컴파일 익셉션을 가지고 있기때문에 예외처리를 해줘야한다!!
			if(conn!=null) conn.close();
	
		}catch(Exception ex){}
		
	}
	
	
	public static MenupanDAO newInstance(){
		
		if(dao==null)
			dao=new MenupanDAO();
		 return dao;
		
	}
	
	
	
	
	
	
	
	
	
}
