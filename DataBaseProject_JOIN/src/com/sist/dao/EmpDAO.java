package com.sist.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
      오라클 연결
   DBMS PROGRAM => RECORD 단위 (RECORD => COLUMN 여러개 묶어준것)
  => bit=> byte => word = record(vo한개) => table(vo여러개)
  			      column값

   vo 여러개를 가져올때 => 일반데이터형 클래스 => ArrayList
 
*/
public class EmpDAO {


	// 연결도구 Connection (Socket) 
	private Connection conn;
	// 입출력 => inputStream, outputStream
	private PreparedStatement ps;
	//URL 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록 => 한번수행 => 생성자
	public EmpDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
		}catch(Exception ex) 
		{
			// 드라이버 연결 문제시 해결
			ex.printStackTrace();
			
		}
		
	}
	
	
	//연결 - 클래스화시키기! -> jar파일로! 라이브러리화!-> Database는  보지 못하게 노출이 안되도록 만들어야한다.-> but,developesql는 누구나 볼수있다.ip만 바꾸면 가능!
	
	public void getConnection()
	{
		try
		{
			//id,pwd넘기기
		conn=DriverManager.getConnection(URL,"hr", "happy");
		//conn hr/happy
		  
			
		}catch(Exception ex){}
	}
	
	
	//해제 
	public void disConnection()
	{
		
		try
		{	
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		}catch(Exception ex) {}
		
	}
	
	
	
	// join을 사용하는 기능
	public ArrayList<EmpVO> empJoinData()
	{
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		
		try
		{
			
			getConnection();
			
			// SQL 문장
			/*
			 * LIKE외의 모든 문장 서식은 동일하다.
			 * 
			 */
			String sql="SELECT empno, ename, job, hiredate, sal, dname, loc, grade "
					  +"FROM emp, dept, salgrade "
					  +"WHERE emp.deptno=dept.deptno "
					  +"AND sal BETWEEN losal AND hisal";
			
			
			// 전송
			
			 ps=conn.prepareStatement(sql);
			 
			 
			 
			// 실행요청
			 
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 
				EmpVO vo=new EmpVO(); // 14개생성
				
				
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.getDvo().setDname(rs.getString(6));;
				vo.getDvo().setLoc(rs.getString(7));
				vo.getSvo().setGrade(rs.getInt(8));
				
				list.add(vo);
				
				 
				 
				 
			 }
			 
			 rs.close();
		}catch(Exception ex) 
		{
			
			ex.printStackTrace();
			
		
		}finally
		{
			disConnection();
			
		}
		
		return list;
		
		
	}
	
	
	
	
	
}
