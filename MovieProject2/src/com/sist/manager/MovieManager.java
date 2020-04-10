package com.sist.manager;

// 실제로 작동-> 데이터긁기/ 처리-> 오라클전송 하는 곳!!!
// dao의 메소드 호출하여 vo 혹은 list를 넣음!!!!!!

import java.text.SimpleDateFormat;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.vo.MovieVO;
import com.sist.vo.NewsVO;
import com.sist.dao.*;
/*
 *  <div id="info_tit"> div#
			<em class="ico_movie ico_15rating">15���̻������</em>
		<a href="/moviedb/main?movieId=122091" class="name_movie #title">������ �����</a>
    </div>
    
    try
    {
        for(;;;;)
        {
            try
            {
               
            }catch(Exception e){} ������
        }
    }catch(Exception e){}
								
 */
public class MovieManager {
	
   	MovieDAO dao=MovieDAO.newInstance(); // 싱글톤 생성
	
	
    public ArrayList<MovieVO> movieListData()
    {
    	ArrayList<MovieVO> list=new ArrayList<MovieVO>();
    	
    	try
    	{
    		/*
    		 *    <div class="a">
    		 *      <p>aaaa</p>
    		 *      <a href="bbb">bbb</a>
    		 *    </div>
    		 *    
    		 *    div.a p   => text()  ========> aaaa
    		 *    div.a a   => attr("href")  ====> bbb
    		 *    div.a     => text()  ==> aaaa bbb 
    		 *                 html()  <p>aaaa</p>
    		 *                         <a href="bbb">bbb</a>
    		 *      
    		 */
    		
    		int k=1; //몇개들어가는지 확인하기 위해
    		
    		
    		//for(int i=1;i<=7;i++)
    		{
    			Document doc=Jsoup.connect("https://movie.daum.net/boxoffice/yearly").get();
    			//Document doc=Jsoup.connect("https://movie.daum.net/premovie/scheduled?opt=reserve&page="+i).get();
    			Elements link=doc.select("div.info_tit a");
    			for(int j=0;j<link.size();j++)
    			{
    			  try
    			  {
    				Element elem=link.get(j);
    				//System.out.println((i)+"-"+elem.attr("href"));
    				String mLink="https://movie.daum.net"+elem.attr("href");
    				// /moviedb/main?movieId=127138
    				Document doc2=Jsoup.connect(mLink).get();
    				//System.out.println(doc2);
    				/*
    				    private String title;
					    private double score;
					    private String genre;
					    private String regdate;
					    private String time;
					    private String grade;
					    private String director;
					    private String actor;
					    private String story;
    				 */
					
					  Element title=doc2.select("div.subject_movie strong.tit_movie").get(0);
					  System.out.println(title.text());
					  Element score=doc2.selectFirst("div.subject_movie em.emph_grade");
					  System.out.println(score.text());
					  
					  
					  Element genre=doc2.select("dl.list_movie dd.txt_main").get(0);
					  System.out.println(genre.text());
					  Element regdate=doc2.select("dl.list_movie dd.txt_main").get(1);
					  System.out.println(regdate.text());
					  
					  Element time=doc2.select("dl.list_movie dd").get(3);
					  System.out.println(time.text());
					  
					  String temp=time.text();
					  StringTokenizer st=new StringTokenizer(temp,",");
					  String strTime=st.nextToken();
					  String strGrade=st.nextToken().trim();
					  
					  System.out.println(strTime);
					  System.out.println(strGrade);
					  
					  //Element time=doc2.selectFirst("div.subject_movie strong.tit_movie");; 
					  //Element grade=doc2.selectFirst("div.subject_movie strong.tit_movie");; 
					  Element director=doc2.select("dl.list_movie dd.type_ellipsis").get(0);
					  System.out.println(director.text());
					  
					  Element actor=doc2.select("dl.list_movie dd.type_ellipsis").get(1);
					  System.out.println(actor.text());
					  //Element actor=doc2.selectFirst("div.subject_movie strong.tit_movie");; 
					  
					  Element story=doc2.selectFirst("div.desc_movie p");
					  System.out.println(story.text());
					  
					  
					  Element poster=doc2.selectFirst("a.area_poster img.img_summary");
					  
					  MovieVO vo=new MovieVO();
		
					  vo.setTitle(title.text());
					  vo.setScore(Double.parseDouble(score.text()));
					  vo.setGrade(strGrade);
					  vo.setTime(strTime);
					  vo.setActor(actor.text());
					  vo.setDirector(director.text());
					  vo.setGenre(genre.text());
					  vo.setRegdate(regdate.text());
					  vo.setStory(story.text());
					  vo.setPoster(poster.attr("src"));
					  vo.setType(5);//현재상영
					  //vo.setType(5); //개봉예정
					  
					  //dao의 movieInsert에 긁어온 vo를 넣음! 
					  //vo에는  title, score, grade, time, actor, director,genre,regdate,story, poster, type 크롤링함!
					  //movieInsert에서 하는 기능은 오라클의 컬럼 순서와 개수가 동일하도록 INSERT sql문장을 만들어서 오라클에 데이터를 넣는 기능을 만들어 놓음!
					  //
					  //오라클에 저장된 컬럼은 아래와 같다!
					  //따라서 dao에서 INSERT SQL문을 작성할 경우 컬럼 갯수와 순서를 동일하게 작성해야한다.
					  // 총 12개의 컬럼이 movie table에 생성되어있다.
					  
					  /*1. MNO      NOT NULL NUMBER(4)      
						2. TITLE    NOT NULL VARCHAR2(1000) 
						3. POSTER   NOT NULL VARCHAR2(2000) 
						4. SCORE             NUMBER(4,2)    
						5. GENRE    NOT NULL VARCHAR2(200)  
						6. REGDATE           VARCHAR2(200)  
						7. TIME              VARCHAR2(50)   
						8. GRADE             VARCHAR2(200)  
						9. DIRECTOR          VARCHAR2(200)  
						10. ACTOR             VARCHAR2(500)  
						11. STORY             CLOB           
						12. TYPE              NUMBER   

					   *따라서 movieInsert에서 INSERT SQL 문장 작성시! 아래와 같이 작성할수 있다.
					   * 1. mno => NVL(MAX(mno)+1, 1) ==> 영화넘버가 0일시 1부터 시작
					   * 2~12번 까지는 
					   * 
					   */
					  
					  /*
					   * String sql="INSERT INTO movie VALUES("
    		 		+ "(SELECT NVL(MAX(mno)+1, 1) FROM movie), "
    		 		+ "?,?,?,?,?,?,?,?,?,?,?)";
    		 		
    		 		NVL(MAX(mno)+1, 1) 해부!
    		 		**NVL(A, B) => A가 NULL 이라면 A값을 B로 바꾸어라
    		 		**MAX(C)+1 => C의 최대값에 +1을 구하라
    		 		==> 먼저 MAX가 아닌 NVL이 먼저 작용한다.
    		 		MNO는 초기값이 주어지지 않았기 때문에 NULL값으로 시작한다=> 따라서 NULL값이 1로 변경되고,
    		 		MAX()+1의 영향을 받아서 1은 2가 된다. 
    		 		이렇게 MNO는 자동증가되어 오라클에 들어가도록 만들어주고,,
    		 		나머지 2~12번의 11개 컬럼은 오라클 순서대로 아래와 같이 작성해준다!!
    		 		
    		 		
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
					   * 
					   *  
					   */
					  dao.movieInsert(vo);
					  
					  System.out.println("k="+k);			  
					  k++;
					  
					  
    			  }catch(Exception ex) {}
					 
    			}
    		}
    		System.out.println("DataBase Insert End...");
    	}catch(Exception ex){}
    	return list;
    }
    
    
  
    
    
    public ArrayList<NewsVO> newsAllData()
    {
    	ArrayList<NewsVO> list=new ArrayList<NewsVO>();
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
    	String today=sdf.format(date);
    	
    	try
    	{
    		//페이지 않에 모든 html 코드 저장!
    		
    		/*
    		 * 	private String title;
	private String poster;
	private String link;
	private String author;
	private String regdate;
	private String content;
    		 * 
    		 * 
    		 */
    		  /*// 뉴스
    	     * <ul class="list_line #list">

    								<li>
    					<a href="http://v.movie.daum.net/v/20200218165207877" class="thumb_line bg_noimage2 @1">
    																					<span class="thumb_img" style="background-image:url(//img1.daumcdn.net/thumb/S320x200/?fname=https://t1.daumcdn.net/news/202002/18/starnews/20200218165207830ydsf.jpg);"></span>
    					</a>
    					<span class="cont_line">
    						<strong class="tit_line"><a href="http://v.movie.daum.net/v/20200218165207877" class="link_txt @1">유아인·공효진, 코로나19로 버버리 패션쇼 입장 금지? "사실무근" </a></strong>
    						<a href="http://v.movie.daum.net/v/20200218165207877" class="desc_line @1">
    							[스타뉴스 김미화 기자]유아인, 공효진 / 사진=스타뉴스 배우 유아인 공효진 등이 코로나19 여파로 영국 버버리 패션쇼 참석을 거부당했다는 보도가 나온 가운데 양쪽 배우 모두 "사실이 아니다"라는 입장을 밝혔다. 18일 한 매체는 배우 유아인 공효진이 17일 영국 런던에서 열린 버버리 컬렉션 쇼에 참석할 예정이었으나, 코로나19 안전 대책이 마련되지 않아
    						</a>
    						<span class="state_line">
    							스타뉴스<span class="txt_dot">・</span><span class="screen_out">발행일자</span>20.02.18
    						</span>
    					</span>
    				</li>
    	     * 
    	     * 
    	     * 
    	     * 
    	     * 
    	     */
    		
    		
    		// 
    		//url을 확인하여 변수에 대해서 for문에서 바꿔줘야하는 것 무엇인지 확인하기!!
    		for(int i=1; i<=18; i++) // 18페이지!
    		{
    			
    			Document doc=Jsoup.connect("https://movie.daum.net/magazine/new?tab=nws&regdate="+today+"&page="+i).get();
    		Elements title=doc.select("ul.list_line strong.tit_line a"); //타이들이 들어간 태그들 모두 모음
    		Elements poster=doc.select("ul.list_line a.thumb_line span.thumb_img");
    		Elements link=doc.select("ul.list_line strong.tit_line a");
    	
    		Elements temp=doc.select("span.cont_line span.state_line");
    		Elements content=doc.select("span.cont_line a.desc_line");
    		
    		for(int j=0; j<title.size();j++)
    		{
    			
    		System.out.println(title.get(j).text());
    		String ss=poster.get(j).attr("style");
    		ss=ss.substring(ss.indexOf("(")+1,ss.lastIndexOf(")")); //background () =>사이에 값 가져오기!! 
    		System.out.println(ss);
    		
    		System.out.println(poster.get(j).attr("style"));
    		System.out.println(link.get(j).attr("href"));
    		String str=temp.get(j).text();
    		String author=str.substring(0,str.indexOf("・"));
    		String regdate=str.substring(str.lastIndexOf("자")+1);
    		System.out.println(author);
    		System.out.println(regdate);
    		System.out.println(temp.get(j).text());
    		System.out.println(content.get(j).text());
    		
    		
    		System.out.println("========================================================");
    		
    		
    		NewsVO vo=new NewsVO();
    		vo.setTitle(title.get(j).text());
    		vo.setLink(link.get(j).attr("href"));
    		vo.setContent(content.get(j).text());
    		vo.setPoster(ss);
    		vo.setRegdate(regdate);
    		vo.setAuthor(author);
    		
    		
    		
    		//오라클 전송
    		dao.newsInsert(vo);
    		
    		}
    		
    		}
    		
    		System.out.println("Save End...!");
    		
    	}
    	catch(Exception ex)
    	{
    		
    		System.out.println(ex.getMessage());
    	}
    	return list;
    }
    
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MovieManager m=new MovieManager();
        //m.movieListData();
        
        m.newsAllData();
	}

	
}





