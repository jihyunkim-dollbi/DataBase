package com.sist.dao;

import java.util.*;

//웹이 아니고 애플리케이션!
//웹과 톰캣은 아무 관련이 없다!
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			EmpDAO dao=new EmpDAO();
			ArrayList<EmpVO> list=dao.empAllData();
			for(EmpVO vo:list)
			{
				System.out.println(vo.getEmpno()+" "+vo.getDvo().getDname());
				
			}
			// 톰캣을 사용하지 않는 상태이기때문에 connection pool을 생성할 수 없다. 
			/*
			 * 에러 ! 톰캣이 작동 x
			 * java.lang.NullPointerException
			   at com.sist.dao.EmpDAO.empAllData(EmpDAO.java:117)
			   at com.sist.dao.MainClass.main(MainClass.java:12)
			 */
	}

}
