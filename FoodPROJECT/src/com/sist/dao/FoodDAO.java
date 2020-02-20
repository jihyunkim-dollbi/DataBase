package com.sist.dao;

import java.util.*;
import java.util.Locale.Category;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.manager.*;
// connection pool
public class FoodDAO {

	private Connection conn;
	private PreparedStatement ps;
	private static FoodDAO dao; //싱클톤으로 만들기!
	// private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	
	
	//<DataSource type="POOLED"> => 마이바티스에서는 이렇게 입력하면 POOL이됨!  POOLED / UNPOOLED! ==> JSOUP, JAXP, JAXB, SIMPLE-JSON, CSV, 트위터스트림?
	//모든 설정파일이 XML이 될 예정!
	
	
	
	/* pool사용하기 위해 ! 필요없어짐! 
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
	
	
	public void getConnection()
	{
		//데이터 수집시 필요 => dbcp사용x => 수집은 자바 애플리케이션사용만 해도 데이터 크롤링 가능!!
		//데이터 수집은 일반 메인으로ok
		//db에 넣은 후에는 아래가 pool로 바꿔야함!!!
		
		//run on server =>웹에 출력  =>톰캣 사용
		//run on application =>도수창에 출력 =>메인사용 =>톰캣 사용x => dbcp(pool)작동x
		
		try
		{
			//실행을 하자마자 
			// conn=DriverManager.getConnection(URL, "hr", "happy");
			
			//POOL사용하기!
			//이미 만들어져있는 커넥션 객체만 가져오면 됨!!
			//톰캣이 5개 만들어놓음!
			//5개 각각의 주소가 JNDI에 주소가 저장되어있음 ->주소접근은 lookup() 사용!
			// java:com//env가 JNDI에 접근하기 위한 주소! INITIALCONTEXT()사용!
			//jdbc:oracle ~ ==>JNDI에 있는 커넥풀한개의 주소를 가져오기 위함!=> 주소접근은 lookup() 사용!
			//SERVER.XML을 읽는 시기는? => run on server을 눌렀을때! 
			//run on server하는 순간 =>톰켓이 5개를 만듬! => 
			//팩토리패턴 <-> 싱글톤!
			//관계도가 단순한 프로그램이 좋음 => spring!!!
			// 모든 sql문장 작성시 getconnection()을 호출하기 때문에 => 불편 =>spring에서는 sql문장만 사용ok =>공통모듈(매번 반복적으로 사용하는 것을 모아두고 필요시 호출만하여 사용! before&after!!==> AOP기능!OOP를 보완한 기능!!!)&핵심모둘!
			
			
			
			Context init=new InitialContext(); //JNDI reg //탐색기를 열고,
			Context c=(Context)init.lookup("java://comp//env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			
			
			/*
			아래와 같이 1줄만 작성해도 괜찮다!!!
		  //Context c=(Context)init.lookup("java://comp//env"); ==> 삭제해도 ok!
			DataSource ds=(DataSource)c.lookup("java://comp//env/jdbc/oracle");
			*/
			
			
		}catch(Exception ex)
		{
			
			ex.printStackTrace();
			
		}
		
		
		
		
	}
	
	
	// 반환
	public void disConnection()
	{
		
		try
		{
		
			//닫기 ps, conn
			if(ps!=null) ps.close(); // close는 컴파일 익셉션을 가지고 있기때문에 예외처리를 해줘야한다!!
			if(conn!=null) conn.close();
	
		}catch(Exception ex){}
		
	}
	
	
	//DAO를 각 사용자당 한개만 사용이 가능하게 한다.
	//싱클톤: 주소를 주장했다가 같은 주소를 넘겨줌
	//우리는 싱클톤 패턴을 만든 것임! 
	//시작하자마자 map에 저장하면 ..? 
	//여러개 만들고 싶으면 clone=> 프로토타입!
	public static FoodDAO newInstance(){
		
		
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
	
	//기능- 오라클
	public void categoryInsert(CategoryVO vo)
	{
		//열고닫는 속도와 가져오는 속도가 잘 안맞기 때문에=>DBCP가 좋음 =>빠뜨리지 않고 가져옴
		//MYBATIS  GOOD!
		
		
		try
		{
			getConnection();
			
			String sql="INSERT INTO category VALUES(?,?,?,?,?)";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getCateno());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getPoster());
			ps.setString(5, vo.getLink());
			ps.executeUpdate();
					
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			
		}finally
		{
			disConnection();
			
		}
	
	}
	
	
	// 메인페이지 출력하려함! 테마출력!!
	public ArrayList<CategoryVO> categoryAllData(){
		
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		
		try
		{
			getConnection();
			//LINK는 다른 맛집을긁기 위해1!
			//cateno는 목록중 한개를 클릭하면 포함된 리스트를 출력하기 위해!!
			String sql="SELECT cateno, title, subject, poster"
					+ "FROM category";
			
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				//while을 사용하는 이유는.....list이기 때문에!
				CategoryVO vo=new CategoryVO();
				vo.setCateno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
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
