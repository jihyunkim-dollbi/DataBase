package com.sist.dao;
/*
 * emp , dept
 * view를 이용해 join할 예정!
 * main에서(실행안됨) 코딩하지 않고 =>웹을 구동하여(서블렛만들거나,jsp만들거나) 톰캣을 구동해야 실행됨!
 * 
 * 
 * 
 * 
 * 
 */
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class EmpDAO {

	//JSP 14장!
	private Connection conn;
	private PreparedStatement ps; //SQL전송 '?'이용
	private static EmpDAO dao;
	//RESULTSET 결과전송!!
	//
	
	//CONNECTION POOL => 메모리관리 / 서버다운 GOOD!
	//미리 생성해둔 Connection객체를 얻어온다!
	public void getConnection(){
		
		try
		{
			Context init=new InitialContext();
			Context c= (Context)init.lookup("java://env/comp");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			
		}catch(Exception ex) {}
		
		
	}
	
	//Connection 반환하는 과정!
	public void disConnection(){
		
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
			
		}catch(Exception ex)
		{
			
			
		}
		
		
	}
	
	//기능
	
	
	
	
	
}
