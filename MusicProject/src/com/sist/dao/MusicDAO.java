package com.sist.dao;
import java.util.*;

import sun.security.util.ArrayUtil;

import java.sql.*;
// 나중에 jar파일로 만들어서 
public class MusicDAO {

	
	
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
				String sql="SELECT rank, state, idCliment, poster, title, singer, album "
						 +"FROM music_genie "
						 +"ORDER BY rank ASC"; // 랭크순으로!!
				
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				int rowSize=50;
				
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
				
				String sql="SELECT CEIL(COUNT(*)/50.0) FROM music_genie";
				
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
				String sql="SELECT rank, state, idCliment, title, singer, poster, key "
						+ "FROM music_genie "
						+ "WHERE rank=?";    // rank에 ? 준 이유는 rank가 no의 역할이 되는 것이다. rank 1~200까지 있으니까 랭크를 50개마다 잘라서 .// 여기서는 mno와 rank의 값이 동일하다. 
						
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
				
				rs.close();
				
				
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
				
			}finally
			{
				
				disConnection();
			}
			
			
			return vo;
					
			
		}
		
	
	
}
