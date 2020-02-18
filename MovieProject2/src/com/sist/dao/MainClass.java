package com.sist.dao;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		// 도수창에서 띄워보려 메인클래스 만듬
		
		MovieDAO dao=MovieDAO.newInstance();
		//20번까지 현재상영 영화
		//21~부터 개봉예정작 21~33
		
		ArrayList<MovieVO> list=dao.movieListData(1, 2); // 1페이지 , 개봉예정작
		for(MovieVO vo:list)
		{
			System.out.println(vo.getMno()+" "+vo.getTitle());
			
		}
	}

	
	
	
}
