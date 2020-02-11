package com.sist.dao;
import java.util.*; // Arraylist
import java.sql.*; 	// connection, preparedstatement, resultset
/*
 * Connection - 오라클 연결 - 전역
 * PreparedStatement - sql문장 전송 - 전역
 * Resultset - 결과값 저장 - 지역변수 ==> SELECT 가 있을때만!(INSERT, UPDATE, DELETE는 사용X)
 * 
 * ExecuteQuery("SQL")=> SELECT -리턴형 sql문장 => 가져오는 기능만 있으므로 COMMIT이 필요없다!
 * ExecuteUpdate("SQL") ==> INSERT, UPDATE, DELETE - (데이터 자체가 변경되는 것)오토커밋!

 * 			데이터베이스커넥션 프로그램? page 416
 * 	JDBC =>  DBCP  => ORM  => JPA
 * 			=====		=======
 * 					 관례형 데이터베이스 연결(라이브러리)
 * 						Mybatis, Hibernate
 * 
 * 
 * doget == 화면만 처리
 * 
 * dopost ==insert update, delete 데이터 주고 받을때
 */
public class BoardDAO {

	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록
	public BoardDAO()
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
		
		
		// 게시판 목록읽기= SELECT ~ ORDER BY
		public ArrayList<BoardVO> boardListData(int page)
		{
			ArrayList<BoardVO> list = new ArrayList<BoardVO>();
			
			// 데이터 가져오기
			try
			{
				getConnection();
				String sql="SELECT no,subject,name, regdate,hit "
						   + "FROM board "
						   + "ORDER BY no DESC";
					int rowSize = 10; // 가져올 갯수 10개 한페이지당 개수
				/*
				 * 0~9 - 1page
				 * 10~19 - 2 page
				 * 
				 */ 
				int i=0;   //10개씩 나눠주는 변수
				int j=0;  // while문이 돌아가는 횟수
				int pageStart=(page*rowSize)-rowSize;  // 출력위치 // 시작위치 잡아놓기!
				
				// 3*15 30-15 15
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())  //j는 while문이 돌아가는 횟수
				{
					if(i<10 && j>=pageStart) // 1가 0~9 1페이지면 j가 0으로 1페이지
					{// (i<10 ==> 10개씩만 잘라오는 아이! i는 10바퀴 돌면 종료하고 빠져나옴  
						
						BoardVO vo= new BoardVO();
						vo.setNo(rs.getInt(1));
						vo.setSubject(rs.getString(2));
						vo.setName(rs.getString(3));
						vo.setRegdate(rs.getDate(4));
						vo.setHit(rs.getInt(5));
						
						list.add(vo);
							
					}
					j++;
						
				}
				rs.close();
				
				
			}catch(Exception ex) 
			{	
				System.out.println(ex.getMessage());	
			}
			finally
			{
				disConnection();	
			}
		  return list;	
		}
		
		public int boardTotalPage()
		{
			
			int total=0;
			try
			{
				getConnection();
				String sql="SELECT CEIL(COUNT(*/10.0) FROM board";
							//CEIL-- 올림함수!! 1.2 =>  총 2 페이지로 바꿔줌!!
				
				ps=conn.prepareStatement(sql);
				
				ResultSet rs=ps.executeQuery();
				rs.next(); //커서를 데이터가 있는 곳에 올림
				total=rs.getInt(1);
				rs.close();
				
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
					
			}
			finally
			{
				disConnection();	
			}
			
			return total;
		}
		
		
		
		// 게시판에서 내용보기 = SELECT~ WHERE // 하나의
		public BoardVO boardDetailData(int no)  //사용자가 게시물 번호를 주면 게시물 확인 가능
		{
			BoardVO vo=new BoardVO();
			try
			{
				getConnection(); // UPDATE ==>조회수 증가해야하기 때문에!! => 증가된 데이터를 가져오기!= 클릭하면 조회수 증가! 
				// 글삭제도 SELECT로 비번 먼저 확인하고 삭제 가능하게 하기!
				// 한가지 기능을 위해 여러가지 쿼리문장 작성 가능! 유의점! 오토커밋!!
				//411,412 페이지 - 트랜젝션 프로그램! - 모든 문장 성공수행시- 커밋 // 하나라도 실패시 ROLLBACK처리!
				// 
				String sql="UPDATE board SET "
						+ "hit=hit+1 "
						+ "WHERE no=?";
				// 사용자가 넣어주는 매개변수 no
				ps=conn.prepareStatement(sql);
				// ?에 값을 채운다
				ps.setInt(1, no);
				// 실행요청
				ps.executeUpdate(); // commit!
				
				
				//상세보기
				sql="SELECT no,name,subject,content,regdate,hit "
					+"FROM board "
					+"WHERE no=?";
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);

				ResultSet rs=ps.executeQuery();
				rs.next(); // 한줄전체를 읽어라 검색값이 1줄이다. while문이 아니기에.
				
				
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getDate(5));
				vo.setHit(rs.getInt(6));
				
				rs.close();
				
				
			}catch(Exception ex)
			{
				
				System.out.println(ex.getMessage());
				
			}finally {
				
				
				disConnection();
			
			}
			return vo;
			
		}
		
		
		
		// 추가하기  INSERT
		// 수정하기 UPDATE
		// 삭제하기 DELETE
		// 찾기 SELECT ~ LIKE
		
		
		
	
	
	
}
