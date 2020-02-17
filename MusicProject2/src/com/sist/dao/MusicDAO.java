package com.sist.dao;
import java.util.*;

import sun.security.util.ArrayUtil;

import java.sql.*;
// 나중에 jar파일로 만들어서 
public class MusicDAO {

	//로그인창
	//목록
	//상세보기
	//댓글
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 등록
	public MusicDAO()
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
		
		
		// 기능
		// 목록 출력하기
		// 순위,state,idCliment,poster, title,singer,album
		public ArrayList<MusicVO> musicListData(int page)
		{
			//한페이징에 50ro
			
			ArrayList<MusicVO> list=new ArrayList<MusicVO>();
			
			try
			{
				getConnection();
				// 오라클에서 가져오기!!
				String sql="SELECT rank, state, idCliment, poster, title, singer, album, mno "
						 +"FROM music_genie "
						 +"ORDER BY rank ASC"; // 랭크순으로!!
				
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				int rowSize=15;
				
				int pageStart=(page*rowSize)-rowSize; // 0~49
				
				
				int i=0; // 50개씩 나눠줌
				int j=0; //while문 돌아감
				
				while(rs.next())
				{
					if(i<rowSize && j>=pageStart)
					{
						MusicVO vo=new MusicVO();
						vo.setRank(rs.getInt(1));
						vo.setState(rs.getString(2));
						vo.setIdCliment(rs.getInt(3));
						vo.setPoster(rs.getString(4));
						vo.setTitle(rs.getString(5));
						vo.setSinger(rs.getString(6));
						vo.setAlbum(rs.getString(7));
						vo.setMno(rs.getInt(8));
						
						list.add(vo);
						
						i++; //50개가 넘으면 빠져나감. 
					}
					j++; // 200개를 다 읽을 예정
				}
				
				
				
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
				
			}finally
			{
				
				disConnection();
			}
			return list;
			
		}
		
		
		
		// 총페이지 
		
		public int musicTotalPage()
		{
			
			int total=0;
			
			try
			{
				getConnection();
				
				String sql="SELECT CEIL(COUNT(*)/15.0) FROM music_genie";
				
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				total=rs.getInt(1);
				rs.close();
				
				
				
				
			}catch(Exception ex) {
				
				System.out.println(ex.getMessage());
			}
			finally
			{
				
				disConnection();
			}
			
		
			return total;
			
			
		}
		
		// 상세보기
		// 가져올것 랭크, state, idcliment, title, singer, poster,key
		
		public MusicVO musicDetailData(int no){
			
			MusicVO vo=new MusicVO();
			
			try
			{
				
				getConnection();
				/*
				 * DML(CURD)
				 * 	SELECT ~ 
				 * 	FROM 
				 * 	WHERE 
				 * 	GROUP BY 
				 * 	HAVING 
				 * 	ORDER BY
				 * 	
				 *  INSERT INTO table(컬럼명..) VALUES(값....) => DEFAULT
				 *  INSERT INTO table(값...) => DEFAULT와 관련없이 무조건 첨부
				 *  
				 * 	UPDATE table명 SET
				 * 		컬럼명=값, 컬럼명=값...
				 * 		WHERE 조건
				 * 			
				 * 	DELETE FROM table명
				 * 	WHERE 조건
				 * 	
				 * 
				 * 
				 */
				String sql="UPDATE music_genie SET "
						  +"hit=hit+1 "
						  +"WHERE mno=?";
				
				ps=conn.prepareStatement(sql); // ps생성
				// ?에 값을 채우기!
				ps.setInt(1, no);
				// 실행요청
				ps.executeUpdate();
				/*
				 * 1. ps.executeUpdate(); ==> 오라클의 데이터가 변경(INSERT, UPDATE, DELETE)
				 * 		COMMIT포함!
				 * 2. ps.executeQuery();  ==> 오라클의 데이터변경이 없는 경우(검색:SELECT)
				 * 		COMMIT미포함!
				 */
				
				
					  sql="SELECT rank, state, idCliment, title, singer, poster, key, mno, album "
						+ "FROM music_genie "
						+ "WHERE mno=?";    // rank에 ? 준 이유는 rank가 no의 역할이 되는 것이다. rank 1~200까지 있으니까 랭크를 50개마다 잘라서 .// 여기서는 mno와 rank의 값이 동일하다. 
						// mno로 ?값을 바꾼이유는 rank가 중복이 있을지도 몰라서... 중복없는 mno가 안전!
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				ResultSet rs=ps.executeQuery();
				rs.next();
				vo.setRank(rs.getInt(1));
				vo.setState(rs.getString(2));
				vo.setIdCliment(rs.getInt(3));
				vo.setTitle(rs.getString(4));
				vo.setSinger(rs.getString(5));
				vo.setPoster(rs.getString(6));
				vo.setKey(rs.getString(7));
				vo.setMno(rs.getInt(8));
				vo.setAlbum(rs.getString(9));
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
			
			
			return vo;
					
			
		}
		
		//다음주 뷰, 메뉴 만들기! 이번주 정리하기
		//댓글 만들기 => 댓글에 댓글은 다음에..
		//2020 02 14
		//로그인된 상태에서 댓글창쓰는 곳 보여줄것
		//로그인
		public String isLogin(String id, String pwd)
		{
			
			System.out.println(id+pwd);
			// 아이디, 비번 틀리냐? 로그인 됐나?
			String result="";
			
					
			try
			{
				getConnection(); 
				
				//카운트 사용(있냐없냐 경우의 수에서 많이 용): 우편번호(데이터 있는 경우, 없는 경우)
				//카운트 갯수 : 0이면 무존재, 1이면 존재 => 카운트 사용!
				String sql="SELECT COUNT(*) FROM music_member " // !!!!!!!!
						+"WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int count =rs.getInt(1); // 카운트가 있냐없냐..
				rs.close();
				
				
				if(count==0)
				{
					
					result="NOID";
					
				}else
				{
				
					//아이디 존재//비번가져와야함=> 오라클에서 가져오기
					sql="SELECT pwd,name, sex FROM music_member "
							+"WHERE id=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, id);
					
					rs=ps.executeQuery();
					rs.next();
					
					//값 2개 받기
					String db_pwd=rs.getString(1);
					String name=rs.getString(2);
					String sex=rs.getString(3);
					rs.close();
					
					
					if(db_pwd.equals(pwd))
					{
						result=name+"|"+sex;   //이름은 보낼 방법이 없어서 result에 넣어놓음 //성별추가 // 로그아웃전까지 유지해야하는 데이터가 무엇인지 알고 세션에 저장하기!
						
						
					}else
					{
						result="NOPWD";
						
					}
					// 로그인이 되면 목록띄우러 고고!
					
					
				}
				
				
				
				
						
			}catch(Exception ex)
			{
				ex.printStackTrace();
				
			}finally
			{
				disConnection();
				
			}
			
					
			return result;
			
		}
		//댓글쓰기 INSERT
		//댓글 수정 UPDATE
		//댓글 삭제 DELETE
		//댓글 보기 SELECT=> WHERE문장
		
		
		//댓글가져오기
		public ArrayList<MusicReplyVO> replyListDate(int mno){
			
			//사용자는 무엇을 보내줘야 우리가 가져올수 있을까? 뮤직번호가 있어야 우리가 1번에 대한 댓글 다 가져옴
			ArrayList<MusicReplyVO> list= new ArrayList<MusicReplyVO>();
			
			try
			{
				getConnection();
				String sql="SELECT no, id, name, msg, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'), " //쓴날짜 가져오기
							+"(SELECT sex FROM music_member mm WHERE mm.id=mr.id) " //컬럼대신에 SELECT를 사용하는 경우SCALAR? => 컬럼대신(스칼라), 테이블대신(인라인뷰)
							+"FROM music_reply mr "
							+"WHERE mno=?";
				
				ps=conn.prepareStatement(sql);
				ps.setInt(1, mno);
				// ?개수만큼만 값을 채우면 ok
				
				//실행!
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					MusicReplyVO vo=new MusicReplyVO();
					vo.setNo(rs.getInt(1));
					vo.setId(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setMsg(rs.getString(4));
					vo.setDbDay(rs.getString(5));
					vo.setSex(rs.getString(6));
					
					list.add(vo);
					
				}
				rs.close();
				
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
				
			}finally{
				
				disConnection();
			
				
			}
			
			return list;
			
			
			
		}
		
		public void replyInsert(MusicReplyVO vo)
		{
			try
			{
				getConnection();
				
				String sql="INSERT INTO music_reply VALUES(mr_no_seq.nextval,?,?,?,?,SYSDATE)";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getMno());
				ps.setString(2, vo.getId());
				ps.setString(3, vo.getName());
				ps.setString(4, vo.getMsg());
				
				
				ps.executeUpdate();
				
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
				
			}finally
			{
				disConnection();
				
			}
			
			
		}
		
		
		
		
		public void replyDelete(int no)
		{
			try
			{
				getConnection();
				
				String sql="DELETE FROM music_reply WHERE no=?";
				ps=conn.prepareStatement(sql);
				
				ps.setInt(1, no); //이 번호 삭제해라!!
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
		
		
		//hit수가 많은 제목 ==> 5개 (인라인뷰)
		public ArrayList<MusicVO> musicTop5()
		{
				
			ArrayList<MusicVO> list=new ArrayList<MusicVO>();
			
			try
			{	
				//SELECT 리스트 -메소드1
				//SELECT ONE -메소드2
				//DELETE
				//INSERT
				//UPDATE
				//총 5개의 라이브러리를 만들어 놓고 사용하면 편리.
				getConnection();
				String sql="SELECT poster, title, no, rownum "
						  +"FROM (SELECT poster, title, RANK() OVER(ORDER BY hit DESC) as no "
						  +"FROM music_genie ORDER BY hit DESC) "
						  +"WHERE rownum<=5";
				
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					MusicVO vo=new MusicVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setRank(rs.getInt(3));
					// 1,2,3 순서는 위에 select문의 순서대로 한다.
					//rownum을 직접 가져올 때는 => 내용보기=> 이전게시물 rownum-1, 다음게시물 rownum+1 을 만들때!! 
					
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
				
				disConnection();
			}
				
			return list;
	
			
			
		}
		
	
		
		
	
}
