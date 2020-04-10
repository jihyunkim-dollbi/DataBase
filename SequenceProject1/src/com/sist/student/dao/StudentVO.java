package com.sist.student.dao;
import java.util.*;
/*
 * ------ -------- ------------ 
1 HAKBUN  NOT NULL NUMBER       
2 NAME    NOT NULL VARCHAR2(30) 
3 KOR     NOT NULL NUMBER       
4 ENG     NOT NULL NUMBER       
5 MATH    NOT NULL NUMBER       
6 REGDATE          DATE         
7 SEX              VARCHAR2(10) 
 *  
 *  
 *  // 시퀀스로 어떻게 자동증가 번호를 만들것인가?!
 *		마이바티스!에서는 max를 더 선호!
 *
 *	<<<웹은 무엇으로 만드나?>>>
 *
 *	jsp?    화면출력?
 *	servlet?
 *	html?
 *	mvc? 최근의 경우 모두! 
 *	spring 은 mvc프로젝트!!
 *	
 *	db도 마이바티스로 만든다! 우리가 만든 컨트롤러로, 우리 자체로 만든 스프링으로 프로젝트를 만든다..
 *  
 */
public class StudentVO {

	private int hakbun;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private Date regdate;
	private String sex;
	
	
	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
