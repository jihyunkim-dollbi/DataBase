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
     
     
     //망코플레이트 => 속도를 높이기위해 => JDBC => DBCP-Connectionpool?(getConnection()대용! 미리 연결해놓고사용하여 편리함!(미리 생성시켜놓음!)), View!
     //응용!!!
     //XML, JSON => 자바스크립트 객체 표현법
     /*
      * var member=[{name:"hong",sex:"man",age:30}, ...] =>배열
      * ==> member[0].name
      *	
      * 리액트,뷰 => JSON사용!! 
      * 
      * <MOBILE>
      * 자바? => SDK SOFTWARE DEVELOP 
      * C?   => 0 NDK 
      */
     
     
     //서블릿 NewsServlet에서 목록 출력하기 위해 만든 메소드
     //한페이지에 12개씩 뿌릴 예정!!
     public ArrayList<NewsVO> newsListData(int page)
     {
    	 ArrayList<NewsVO> list=new ArrayList<NewsVO>();
    	 //try절 안에 들어가면 안됨! 리턴형을 포함하기 때문에 블록안에 넣으면 안된다!!
    	 
    	 try
    	 {
    		 getConnection();
    		 
    		 int rowSize=10; // 한페이지에 들어가는 목록수
    		 int start=(page*rowSize)-(rowSize-1); // 시작페이지
    		 
    		 // rownum의 시작은 1부터 시작한다!!
    		 // 1: 1~10=> 1페이지
    		 // 2페이지는 11~20 
    		 // 3 : 21~30!
    		 
    		 int end=page*rowSize; 
    		 // 끝페이지
    		 //between ~ and사용!!
    		 
    		 /*
    		    TITLE   NOT NULL VARCHAR2(4000) 
				POSTER  NOT NULL VARCHAR2(1000) 
				LINK    NOT NULL VARCHAR2(1000) 
				AUTHOR  NOT NULL VARCHAR2(300)  
				REGDATE NOT NULL VARCHAR2(100)  
				CONTENT NOT NULL CLOB   
    		  */
    		 /*
    		 String sql="SELECT title, poster, link ,author, regdate, content, num  "
    				   +"FROM (SELECT title,poster,link, author, regdate, content, rownum as num FROM news)  "
    				   +"WHERE num BETWEEN ? AND ? ";
    		*/		
    		 
    		 String sql="SELECT title, poster, author, regdate,  link, content, num "
  				       +"FROM (SELECT title, poster, author, regdate, link, content, rownum as num "
    				   +"FROM (SELECT title, poster, author, regdate, link, content FROM news)) "
  				       +"WHERE num BETWEEN ? AND ? ";
    		 
    		 
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, start);
    		 ps.setInt(2, end);
    		     		
    		 ResultSet rs = ps.executeQuery();
    		  //결과값을 받아온다
    		 
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
    			 vo.setAuthor(rs.getString(3));
    			 vo.setRegdate(rs.getString(4));
    			 vo.setLink(rs.getString(5));
    			 vo.setContent(rs.getString(6));
    			
    			 list.add(vo);
    			 // 데이터 첨부 - 뿌리기전!
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
    		 //xe에서는 커넥션을 최대 50개정도로 한다!
    		 //따라서 disconnection을 해줘야한다.!!
    		 
    	 }
    	 
    	 return list; // list! 리턴!
     }
     
    
     
     //뉴스에 대한 총페이지
     public int newsTotalPage()
     {
    	 int total=0;
    	 
    	 try
    	 {
    		 getConnection();
    		
    		 String sql="SELECT CEIL(COUNT(*)/10.0) FROM news";
    		 
    		 ps=conn.prepareStatement(sql);
    		 ResultSet rs=ps.executeQuery();
    		 rs.next();
    		 total=rs.getInt(1);
    		 rs.close();
    		 
    		 
    	 }catch(Exception ex)
    	 {
    		 
    		 ex.printStackTrace();
    	 }finally
    	 {
    		 disConnection();
    		 
    	 }
    	 return total;
    	 
     }
     
     
     
     
     
     public MovieVO movieDetailData(int mno) //사용자가 mno 넘겨주면 영화전체값을 주겠다! 결과값으로 vo한개 리턴!
     {
    	 MovieVO vo=new MovieVO();
    	 
    	 try
    	 {
    		 getConnection();
    		 
    		 /*
MNO      NOT NULL NUMBER(4)      
TITLE    NOT NULL VARCHAR2(1000) 
POSTER   NOT NULL VARCHAR2(2000) 
SCORE             NUMBER(4,2)    
GENRE    NOT NULL VARCHAR2(200)  
REGDATE           VARCHAR2(200)  
TIME              VARCHAR2(50)   
GRADE             VARCHAR2(200)  
DIRECTOR          VARCHAR2(200)  
ACTOR             VARCHAR2(500)  
STORY             CLOB        
    		  * 
    		  */
    		 String sql="SELECT mno,title,poster,score,genre,regdate,time,grade,director,actor,story "
    		 		+ "FROM movie "
    		 		+ "WHERE mno=?";
    		 
    		 
    		 ps=conn.prepareStatement(sql);
    		 ps.setInt(1, mno);
    		 ResultSet rs = ps.executeQuery();
    		 
    		 rs.next();
    		
    		 vo.setMno(rs.getInt(1));
    		 vo.setTitle(rs.getString(2));
    		 vo.setPoster(rs.getString(3));
    		 vo.setScore(rs.getDouble(4));
    		 vo.setGenre(rs.getString(5));
    		 vo.setRegdate(rs.getString(6));
    		 vo.setTime(rs.getString(7));
    		 vo.setGrade(rs.getString(8));
    		 vo.setDirector(rs.getString(9));
    		 vo.setActor(rs.getString(10));
    		 vo.setStory(rs.getString(11));

    		 rs.close();
    		 
    		 
    		 
    		 
    		 /*
    		  * while문을 사용하지 않는 이유는 mno한개에 대한 값=> 한줄을 가져올 것이기때문에! 
    		  * 
    		  * 
    		  * 
    		  */
    		 
    	 }
    	 catch(Exception ex)
    	 {
    		 ex.printStackTrace();
    		 
    	 }
    	 finally
    	 {
    		 
    		 disConnection();
    	 }
    	 
    	 return vo;
    	 
     }
     
     
     
}







