package com.sist.manager;

//1000개 이하는 arraylist로 가져오는 것이 good!!! 




// jsoup 를 web-inf- lib에 넣어놓음
//jdk에 놓지 않고 여기에 넣은 이유는?
/*
 *<div class="module_title_wrap">
    <h2 class="title">믿고 보는 맛집 리스트</h2>
      <a class="module_more" href="/top_lists"
         onclick="trackEvent('CLICK_TOPLIST_LIST', {&quot;section_position&quot;:0,&quot;section_title&quot;:&quot;믿고 보는 맛집 리스트&quot;,&quot;more_link&quot;:&quot;/top_lists&quot;,&quot;more_label&quot;:&quot;리스트 더보기&quot;})">
        리스트 더보기
      </a>
  </div>
  <div class="slider-container toplist-slider">
    <button class="btn-nav prev"></button>
    <button class="btn-nav next"></button>

    <div class="top_list_slide">
        <ul class="list-toplist-slider" style="width: 531px;">
            <li>
              <img class="center-croping" alt="분짜 맛집 베스트 35곳 사진"
                   data-lazy="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/ll_ikc83wddw3t9a.png?fit=around|600:400&amp;crop=600:400;*,*&amp;output-format=jpg&amp;output-quality=80"/>

              <a href="/top_lists/1649_bun_cha"
                 onclick="trackEvent('CLICK_TOPLIST', {&quot;section_position&quot;:0,&quot;section_title&quot;:&quot;믿고 보는 맛집 리스트&quot;,&quot;position&quot;:0,&quot;link_key&quot;:&quot;P5AXQ5F&quot;});">
                <figure class="ls-item">
                  <figcaption class="info">
                    <div class="info_inner_wrap">
                      <span class="title">분짜 맛집 베스트 35곳</span>
                      <p class="desc">"갑분짜: 갑자기 분짜 먹고 싶다."</p>
                      <p class="hash">
                          <span>#분짜 </span>
                          <span>#분짜 맛집 </span>
                          <span>#분짜맛집 </span>
                          <span>#베트남 </span>
                          <span>#베트남요리 </span>
                          <span>#베트남 요리 </span>
                          <span>#베트남음식 </span>
                          <span>#베트남 음식 </span>
                      </p>
                    </div>
                  </figcaption>
                </figure>
              </a>
            </li>
 *
 */
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.sist.dao.*; 

//arraylist로 크롤링!
public class FoodManager {

	public ArrayList<CategoryVO> categoryAllData(){
		
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		
		try
		{
			Document doc=Jsoup.connect("http://www.mangoplate.com/").get();
			
			Elements title=doc.select("div.info_inner_wrap span.title");
			Elements poster=doc.select("ul.list-toplist-slider img.center-croping"); 
			Elements subject=doc.select("div.info_inner_wrap p.desc");
			Elements link=doc.select("ul.list-toplist-slider li a"); //a테그는 중복되어 너무 많음 => 따라서 해당 객체의 a를 가져와야함! a가 클래스 안가지고 있음
			
			for(int i=0; i<12; i++)
			{
				System.out.println(title.get(i).text());
				System.out.println(poster.get(i).attr("data-lazy"));
				System.out.println(subject.get(i).text());
				System.out.println(link.get(i).attr("href"));
				
				CategoryVO vo=new CategoryVO();
				vo.setCateno(i+1); // 1~12
				vo.setTitle(title.get(i).text());
				vo.setSubject(subject.get(i).text());
				vo.setLink("http://www.mangoplate.com/"+link.get(i).attr("href"));
				String temp=poster.get(i).attr("data-lazy");
				temp=temp.replace("&", "@");
				vo.setPoster(temp);
				// &를 하게되면  &amp라고 나오기 때문에 '변수를 입력하라는 뜻이다' => 따라서 이미지에서 &빼고 가져옴!
				
				list.add(vo);
			
			}
			
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		return list;
		
		
	}
	
	
	public static void main(String[] args) {
		
		FoodManager fm = new FoodManager();
		ArrayList<CategoryVO>list = fm.categoryAllData();
		//메인이 들어가는 것은 웹이 아니다.=> 웹은 서블릿,jsp을 구동해야하는 것 => 톰캣이 필요! 따라서 메일메소드에서 작동은 톰캣의 작동이 상관없다!
		//메인은 일반 자바 애플릿케이션!
		
		//dao와 vo는 웹에서도 ok, 애플리케이션도 ok!
		
		FoodDAO dao=FoodDAO.newInstance();
		int k=1;
		for(CategoryVO vo:list)
		{
			dao.categoryInsert(vo);
			System.out.println("k="+k);
			k++;
			
		}
				
		System.out.println("save end ...");
		
		
		
/*
* 
* 
* 
분짜 맛집 베스트 35곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/ll_ikc83wddw3t9a.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"갑분짜: 갑자기 분짜 먹고 싶다."
/top_lists/1649_bun_cha
튀김 맛집 베스트 35곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/ilj8wp-u4w2yoyy7.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"바사삭 소리까지 맛있어!"
/top_lists/tempura_top35
강동구 맛집 베스트 50곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/jtbvr9wkqvdjjeig.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"강동구에도 숨은 맛집이 많다구!"
/top_lists/1712_gangdong
즉석떡볶이 맛집 베스트 30곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/1zin_8gaftbptwqv.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"마무리는 볶음밥으로 RGRG?"
/top_lists/1301_tteokbokki
파스타 맛집 베스트 55곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/o1l-hvwzppk0p-wb.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"면 요리라면 다 좋지만 역시 파스타가 최고!"
/top_lists/pasta_top10
데이트 맛집 베스트 60곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/sbpb9gf-pxc9n0be.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"우리 오늘 어디갈까?"
/top_lists/1556_date
초콜릿 디저트 맛집 베스트 30곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/apuvayin7wsc6rs0.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"초콜릿이 들어가면 다 맛있어!"
/top_lists/656_chocolate_dessert
마카롱 맛집 베스트 50곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/iavr6q94xc5wh8us.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"나는 초콜릿보다 마카롱이 더 좋더라!"
/top_lists/1238_macaron
광진구 맛집 베스트 50
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/kp9crqzrj7uwi5sr.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"건대, 어린이대공원으로 맛집 찾아 떠나자!"
/top_lists/2284_gwangjingu
카푸치노 맛집 베스트 15곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/n8w2o-8jmem-yodu.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"앗..입에 거품이..!"
/top_lists/1428_cappucino
쌀국수 맛집 베스트 45곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/vksgbszp-zzdxnta.png?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"진한 국물에서 베트남과 태국이 느껴진다! "
/top_lists/1113_ricenoodle
돌솥밥 맛집 베스트 30곳
https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/qgmd3fl1wi1v2rm7.jpg?fit=around|600:400&crop=600:400;*,*&output-format=jpg&output-quality=80
"누룽지까지 먹을 수 있음"
/top_lists/1044_dolsot
k=1
k=2
k=3
k=4
k=5
k=6
k=7
k=8
k=9
k=10
k=11
k=12
save end ...

		 * 
		 * 
		 */
	}
	
}
