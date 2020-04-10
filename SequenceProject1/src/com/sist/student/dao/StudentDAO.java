package com.sist.student.dao;

import java.util.*;
import java.sql.*;

public class StudentDAO {

	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록
	public StudentDAO()
	{
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
		}catch(Exception ex) {
			
			System.out.println(ex.getMessage());
			
		}
		
	}
	
		// 연결
		public void getConnection()
		{
			try
			{	
				conn=DriverManager.getConnection(URL,"hr","happy");
				
			}
			catch(Exception ex)
			{
					
			}	
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
		
		
		//student 테이블에서 학번을 정렬로 해서 학번 , 이름, 국어, 영어, 수학점수 5개의 데이터 가져오기
		public ArrayList<StudentVO> stdAllData(int page){
			
			ArrayList<StudentVO> list= new ArrayList<StudentVO>();
					
			try
			{
				getConnection();
				String sql="SELECT hakbun,name,kor,eng,math "
						 +"FROM student "
						 +"ORDER BY 1";
				
				
				/*
				 * SUBQUERY => 모든 문장을 한번에 보낼수 있어서 속도가 빨라진다. 네트워크 통신이기때문에!!
				 * String sql="SELECT hakbun,name,kor,eng,math,num "
						 +"FROM (SELECT hakbun,name,kor,eng,math,rownum as num "
						 +"FROM (SELECT hakbun,name,kor,eng,math "
						 +"FROM student ORDER BY 1)) "
						 +"WHERE num BETWEEN 1 AND 10";
						 
						 ==> select 두번을 사용한 이유는 미리 출력을 해 놓고 자르기 위함. 중간에서는 자를 수가 없기 때문에!!
						 // 따라서 페이징 기법은 자바로 하는게 아니라 오라클에서 해야한다! (자르기!!)
				 * 학번 이름 국어 영어 수학 num을 가져오는데 먼저 학번을 순서대로 한 상태에서 num이 1~10까지만 가져와라.
				 * 
				 * 
				 */
				
				
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				
				int i=0; // vo한개 읽는 것 - 한페이지에 10개씩 잘라줌
				int j=0; // 페이지 개수
				int pageStart=(page*10)-10; // 페이지시작점
				
				
				
				
				while(rs.next())
				{
					if(i<10 && j>=pageStart) // i가 0부터9까지이고, j가 page가 1일 경우 pageStart가 0이 되는데 그 값보다 클때, 아래 리스트를 출력하라..
					{
						
						StudentVO vo=new StudentVO();
						vo.setHakbun(rs.getInt("hakbun"));
						vo.setName(rs.getString("name"));
						vo.setKor(rs.getInt("kor"));
						vo.setEng(rs.getInt("eng"));
						vo.setMath(rs.getInt("math")); // 함수가 들어가는 경우는 무조건 번호!
		
						list.add(vo);
					
						i++;
					
					}
					
					j++;
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
		
		
		//시퀀스 넣기 - 
		//hakbun에 시퀀스(자동증가)를 넣어주며 데이터를 가져옴!
		public void stdInsert(StudentVO vo)
		{
			try
			{  //날짜는 디폴트라서 안가져와도 됨?
				getConnection();
				String sql="INSERT INTO student(hakbun,name,kor,eng,math,sex) "
						 +"VALUES(std_hakbun_seq.nextval,?,?,?,?,?)";  
				//                          1            2 3 4 5 6
				//hakbun에 시퀀스 줌! 프라이머리키이기때문에 중복안됨!!
				//regdate는 디폴트값이기 때문에 안가져외도 됨???
				// ?에 값을 채워주자!!
				
				
				ps=conn.prepareStatement(sql);
				//실행전에  ?에 값을 넣어줘야한다
				ps.setString(1, vo.getName());
				ps.setInt(2, vo.getKor());
				ps.setInt(3, vo.getEng());
				ps.setInt(4, vo.getMath());
				ps.setString(5, vo.getSex());
				
				
				
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				
			}
			finally
			{
				
				disConnection();
			}
			
			
		}
		
		
		public int stdRowCount()
		{
			
			
			int count=0;
			
			try
			{
				getConnection();
				String sql="SELECT COUNT(*) FROM student";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				count=rs.getInt(1);
				rs.close();
				
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
				
			}finally
			{
				disConnection();
				
			}
			
			return count;
		}
		
		
		//게시글 삭제하기 ==함수를 만들어 놓고 서블릿 파일로 삭제파일만들고 doget만 연다 a태그로 넘어왔기 때문에 post는 필요없다.
		public void stdDelete(int hakbun)
		{
			try
			{  //날짜는 디폴트라서 안가져와도 됨?
				getConnection();
				String sql="DELETE FROM student "
						+"WHERE hakbun=?";  
				//       
				//hakbun에 시퀀스 줌! 프라이머리키이기때문에 중복안됨!!
				//regdate는 디폴트값이기 때문에 안가져외도 됨???
				// ?에 값을 채워주자!!
				
				
				ps=conn.prepareStatement(sql);
				//실행전에  ?에 값을 넣어줘야한다
				ps.setInt(1, hakbun);
				// 함수를 안 넣었기 때문에 변수그대로 넣어도 된다!
				
				
				ps.executeUpdate();
				
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				
			}
			finally
			{
				
				disConnection();
			}
			
			
		}
		
		
		
}
