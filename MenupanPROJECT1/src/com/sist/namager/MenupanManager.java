package com.sist.namager;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.*;

import com.sist.dao.*;
import com.sist.vo.*;


public class MenupanManager {

	
	public ArrayList<MenuCategoryVO> menuCategoryAllData(){
		
		ArrayList<MenuCategoryVO> list=new ArrayList<MenuCategoryVO>();
		
		
		try
		{
			
			//Document doc=Jsoup.connect("http://www.mangoplate.com/").get();
			
			Document doc=Jsoup.connect("https://www.menupan.com/restaurant/bestrest/bestrest.asp?pt=rt&areacode=ss202").get();
			/*
			 private int cateno;
	  		 private String title;
	         private String link;
	  	    */
			
		 /*
	        Elements title=doc.select("div.info_inner_wrap span.title");
			Elements poster=doc.select("ul.list-toplist-slider img.center-croping"); 
			Elements subject=doc.select("div.info_inner_wrap p.desc");
			Elements link=doc.select("ul.list-toplist-slider li a"); //a테그는 중복되어 너무 많음 => 따라서 해당 객체의 a를 가져와야함! a가 클래스 안가지고 있음
		 */
		
			
			
			
			/*
			 * 
			
			 * for(int i=0; i<12; i++)
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
			 
			 */
			
			
			
			
			
			
		
		
			
			
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		
		
		return list;
	}
		
		
		
		
		
		
		
		
	
	
	
	
	
	
}
