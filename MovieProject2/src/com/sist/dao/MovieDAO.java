package com.sist.dao;
import com.sist.vo.*;
import java.util.*;
import java.sql.*;


/*
    
     private int mno;
     private String title;
     private double score;
     private String genre;
     private String regdate;
     private String time;
     private String grade;
     private String director;
     private String actor;
     private String story;
     private int type;
 *   String sql="INSERT INTO movie VALUES("+mno+",'"+title+"',"+score+",'"+genre+"','"+regdate
 *              +"','"+time+"','"+grade+"','"+direactor+"','"+actor+"','"+story+"',"+type+")";
 *              
 *   String sql="INSERT INTO movie VALUES(?,?,?,?,?,?,?,?,?,?)"
 */
public class MovieDAO {
	
     private Connection conn; //Socket
     private PreparedStatement ps;//OutputStream , BufferedReader
     private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
     private static MovieDAO dao;
     
     // ����̹� ��� => �ѹ��� ����  
     public MovieDAO()
     {
    	 try
    	 {
    		 Class.forName("oracle.jdbc.driver.OracleDriver");
    	 }catch(Exception ex) 
    	 {
    		 ex.printStackTrace();
    	 }
     }
   
     
  
     public void getConnection()
     {
    	 try
    	 {
    		 conn=DriverManager.getConnection(URL,"hr","happy");
    	
    	 }catch(Exception ex) {}
     
     }
  
     
     
     public void disConnection()
     {
    	 try
    	 {
    		 if(ps!=null) 
    			 ps.close();
    		 if(conn!=null) 
    			 conn.close();
    		 
    
    	 }catch(Exception ex) {}
     
     }
    
          
     /*
      * 
      * 
      * 
      * 
      * 
      * 
      * 어댑터(pojo)
      * 
      * react
      * 	react-hooks
      * redux!
      * 
      */
     
     // 싱글톤!!
     public static MovieDAO newInstance()
     {
    	 if(dao==null)
    		 dao=new MovieDAO();
    	 
    	 return dao;
     }
     
     
     // 오라클에 저장하는 메소드
     public void movieInsert(MovieVO vo)
     {	
    	 /*
    	  * -------- -------- -------------- 
MNO      NOT NULL NUMBER(4)      
1TITLE    NOT NULL VARCHAR2(1000) 
2POSTER   NOT NULL VARCHAR2(2000) 
3SCORE             NUMBER(4,2)    
4GENRE    NOT NULL VARCHAR2(200)  
5REGDATE           VARCHAR2(200)  
6TIME              VARCHAR2(50)   
7GRADE             VARCHAR2(200)  
8DIRECTOR          VARCHAR2(200)  
9ACTOR             VARCHAR2(500)  
10STORY             CLOB           
11TYPE              NUMBER  
    	  * 
    	  * 
    	  */
    	 try
    	 {
    		 getConnection();
    		 String sql="INSERT INTO movie VALUES("
    		 		+ "(SELECT NVL(MAX(mno)+1, 1) FROM movie), "
    		 		+ "?,?,?,?,?,?,?,?,?,?,?)";
    		 ps=conn.prepareStatement(sql);
    		 ps.setString(1, vo.getTitle());
    		 ps.setString(2, vo.getPoster());
    		 ps.setDouble(3, vo.getScore());
    		 ps.setString(4, vo.getGenre());
    		 ps.setString(5, vo.getRegdate());
    		 ps.setString(6, vo.getTime());
    		 ps.setString(7, vo.getGrade());
    		 ps.setString(8, vo.getDirector());
    		 ps.setString(9, vo.getActor());
    		 ps.setString(10, vo.getStory());
    		 ps.setInt(11, vo.getType());
    		 
    		 
    		 ps.executeUpdate();
    		 
    	 }catch(Exception ex)
    	 {
    		 ex.printStackTrace();
    		 
    	 }finally
    	 {
    		 disConnection();
    		 
    	 }
    	 
     }
     
     
     
     // 한페이지에 12개씩 뿌릴 예정!!
     public ArrayList<MovieVO> movieListData(int page, int type)
     {
    	 ArrayList<MovieVO> list=new ArrayList<MovieVO>();
    	 try
    	 {
    		 getConnection();
    		 int rowSize=12; // 한페이지에 들어가는 목록수
    		 int start=(page*rowSize)-(rowSize-1); // 시작페이지
    		 // rownum의 시작은 1!
    		 // 12-11=1~12 => 1페이지
    		 // 2페이지는 13 => 2*12 => 24-11=13~24 => 2페이지
    		 int end=page*rowSize; // 끝페이지
    		 //between ~ and사용!!
    		 
    		 String sql="";
    		 if(type<3)
    		 {
    			 
    			 //rownum을 줘서 페이지에 12개씩 프린팅 되도록!!
    		  sql="SELECT mno,title,poster,score,regdate, num "
    				 +"FROM (SELECT mno,title,poster,score,regdate, rownum as num "
    				 +"FROM (SELECT mno,title,poster,score,regdate "
    				 +"FROM movie WHERE type=? ORDER BY mno ASC)) "
    				 +"WHERE num BETWEEN ? AND ?";
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, type);
    		 ps.setInt(2, start);
    		 ps.setInt(3, end);
    		 }
    		 else
    		 {
    			 // rownum을 주지 않고 한페이지에 모두 출력되게함!!
    			 sql="SELECT mno,title,poster,score,regdate "
        				 +"FROM movie WHERE type=? ORDER BY mno ASC";
        				 
        		 ps=conn.prepareStatement(sql);
        		 ps.setInt(1, type);
        	
    			 
    		 }
    		 
    		 
    		 
    		 ResultSet rs = ps.executeQuery();
    		 
    		 while(rs.next()) // 
    		 {
    			 MovieVO vo=new MovieVO();
    			 vo.setMno(rs.getInt(1)); //상세보기시 필요
    			 vo.setTitle(rs.getString(2));
    			 vo.setPoster(rs.getString(3));
    			 vo.setScore(rs.getDouble(4));
    			 vo.setRegdate(rs.getString(5));
    			
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
     
     
     public void newsInsert(NewsVO vo)
     {	
    	 /*
    	  *  * 이름      널?       유형             
------- -------- -------------- 
TITLE   NOT NULL VARCHAR2(4000) 
POSTER  NOT NULL VARCHAR2(1000) 
LINK    NOT NULL VARCHAR2(1000) 
AUTHOR  NOT NULL VARCHAR2(300)  
REGDATE NOT NULL VARCHAR2(100)  
CONTENT NOT NULL CLOB   
 * 
    	  * 
    	  * 
    	  */
    	 try
    	 {
    		 getConnection();
    		 String sql="INSERT INTO news VALUES("
    				   +"?,?,?,?,?,?)";
    		 
    		 ps=conn.prepareStatement(sql);
    		 //순서대로넣기!
    		 ps.setString(1, vo.getTitle());
    		 ps.setString(2, vo.getPoster());
    		 ps.setString(3, vo.getLink());
    		 ps.setString(4, vo.getAuthor());
    		 ps.setString(5, vo.getRegdate());
    		 ps.setString(6, vo.getContent());
    		
    		 
    		 ps.executeUpdate();
    		 
    	 }catch(Exception ex)
    	 {
    		 ex.printStackTrace();
    		 
    	 }finally
    	 {
    		 disConnection();
    		 
    	 }
    	 
     }
     
     
     
     // 서블릿 NewsServlet에서 목록 출력하기 위해 만든 메소드
     //한페이지에 12개씩 뿌릴 예정!!
     public ArrayList<NewsVO> newsListData(int page)
     {
    	 ArrayList<NewsVO> list=new ArrayList<NewsVO>();
    	 try
    	 {
    		 getConnection();
    		 
    		 int rowSize=12; // 한페이지에 들어가는 목록수
    		 int start=(page*rowSize)-(rowSize-1); // 시작페이지
    		 // rownum의 시작은 1!
    		 // 12-11=1~12 => 1페이지
    		 // 2페이지는 13 => 2*12 => 24-11=13~24 => 2페이지
    		 int end=page*rowSize; // 끝페이지
    		 //between ~ and사용!!
    		 /*
    		  * 
    		  * 
    		  * TITLE   NOT NULL VARCHAR2(4000) 
				POSTER  NOT NULL VARCHAR2(1000) 
				LINK    NOT NULL VARCHAR2(1000) 
				AUTHOR  NOT NULL VARCHAR2(300)  
				REGDATE NOT NULL VARCHAR2(100)  
				CONTENT NOT NULL CLOB   
    		  */
   
    		 String sql="SELECT title,poster, link ,author, regdate, content, num  "
    				 +"FROM (SELECT title,poster,link, author, regdate, content, rownum as num FROM news)  "
    				 +"WHERE num BETWEEN ? AND ? ";
    				
    		 
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, start);
    		 ps.setInt(2, end);
    		 
    		
    		 ResultSet rs = ps.executeQuery();
    		 
    		 
    		 /*
    		  * while문을 읽으면서
    		  * rs에서 한줄씩 읽으면서 데이터를 vo에 넣을 것임!!
    		  * 
    		  * title,poster, link ,author, regdate, content
    		  */
    		 while(rs.next()) // 
    		 {
    			 
    			 /*
    			  *get -> 출력할때!
				   set -> 오라클에서 값을 가져올때!

    			  * 
    			  */
    			 
    			 NewsVO vo=new NewsVO();
    			 vo.setTitle(rs.getString(1));
    			 vo.setPoster(rs.getString(2));
    			 vo.setLink(rs.getString(3));
    			 vo.setAuthor(rs.getString(4));
    			 vo.setRegdate(rs.getString(5));
    			 vo.setContent(rs.getString(6));
    			
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







