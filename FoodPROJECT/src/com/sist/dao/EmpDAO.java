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
			//탐색기를 연다!
			Context init=new InitialContext(); // JNDI Registry(에서 커넥션 주소를 가지고 있음!가상 주소 저장 공간!)(JAVA NAMING DIRECTORY INTERFACE!)
			
			/*주소가 저장되어있는 형식이 폴더형식!
			 * "java://env/comp" => C DRIVE의 이름!
			 * C드라이브를  URL형태로 만듬! 아무도 모르는 주소를 만들기 위해!
			 * C드라이브안에 이 jdbc/oracle 이름으로 저장된 정보를 달라! 
			 * type="javax.sql.DataSource"를 준다!!
			 * 
			 * 5개의 커넥션주소값에 이름을 주어서 찾아옴!  	DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			 * 연결하는 객체를 달라! conn=ds.getConnection();
			 */
			
			/* 
			 * dbcp방식을 만들수도 있다!!
			 * Connection[] conn={100,200,300,400,500};
			 * boolean[] sw={false, false,false,false,false}
			 * 
			 * if(요청.equals("jdbc/oracle");
			 * 	{
			 * 		for(int i=0;i<5; i++)
			 * 		{
			 * 			return conn[i];
			 * 			break; 
			 * 		}
			 * 	}
			 * 
			 */
			
			//저장된 폴더위치로 접근 - 고정주소
			Context c= (Context)init.lookup("java://comp//env");// => JNDI
			
			//**lookup => setLookup("별칭",실제클래스주소)
			// 이름으로 클래스 주소를 찾아옴!
			
			//실제 커넥션 주소 요청 ==> 오라클 정보와 관련된 모든 정보를 전송해줌(DataSource가 모두 가짐!) - 우리가 만든 주소
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			
			//모든 정보를 가진  ds의 주소값을 받는다!!
			conn=ds.getConnection();
			
			//5개의 커넥션을 생성했고 5개의 커넥션 주소가 만들어졌을 것이다.
			//false & true : false면 반환된 상태, true면 사용중!
		
			
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
	public ArrayList<EmpVO> empAllData()
	{
		ArrayList<EmpVO> list=new ArrayList<EmpVO>();
		
		try
		{
			//사용할 커넥션 주소를 얻어옴!
			getConnection();
			/*
			String sql="SELECT empno, ename, job, hiredate, sal, dname, loc "
			    	  +"FROM emp, dept "
					  +"WHERE emp.deptno=dept.deptno";
			*/
			String sql="SELECT * FROM emp_dept";
					
					
 
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.getDvo().setDname(rs.getString(6));
				vo.getDvo().setLoc(rs.getString(7));
				
				list.add(vo);
			}
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			//반환
			disConnection();
		}
		return list;
	}
	
	
	
	
}
