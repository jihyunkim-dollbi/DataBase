package com.sist.vo;

public class MenupanVO {
/*
이름          널?       유형             
----------- -------- -------------- 
NO          NOT NULL NUMBER         
CNO                  NUMBER         
TITLE       NOT NULL VARCHAR2(500)  
TYPE        NOT NULL VARCHAR2(500)  
TEL         NOT NULL VARCHAR2(200)  
ADDRESS     NOT NULL VARCHAR2(500)  
SCORE       NOT NULL NUMBER(2,1)    
THEME       NOT NULL VARCHAR2(500)  
LIKES       NOT NULL VARCHAR2(100)  
OPTIME      NOT NULL VARCHAR2(100)  
ENDTIME     NOT NULL VARCHAR2(100)  
HOLIDAY     NOT NULL VARCHAR2(100)  
SEATS       NOT NULL VARCHAR2(200)  
DRINKS      NOT NULL VARCHAR2(200)  
NONSMOKING  NOT NULL VARCHAR2(200)  
RESERVATION NOT NULL VARCHAR2(100)  
TOILET      NOT NULL VARCHAR2(300)  
TAKEOUT              VARCHAR2(300)  
PARKING     NOT NULL VARCHAR2(500)  
FACILITY             VARCHAR2(300)  
DESCRIPTION NOT NULL VARCHAR2(2000) 
REVIEWS              CLOB      
*/	
	
	private int no;
	private int cno;
	private String title;
	private String type;
	private String tel;
	private String address;
	private double score;
	private String theme;
	private String likes;
	private String opTime;
	private String endTime;
	private String holiday;
	private String seats;
	private String drinks;
	private String nonSmoking;
	private String reservation;
	private String toilet;
	private String takeout;
	private String parking;
	private String facility;
	private String description;
	private String reviews;
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getDrinks() {
		return drinks;
	}
	public void setDrinks(String drinks) {
		this.drinks = drinks;
	}
	public String getNonSmoking() {
		return nonSmoking;
	}
	public void setNonSmoking(String nonSmoking) {
		this.nonSmoking = nonSmoking;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getToilet() {
		return toilet;
	}
	public void setToilet(String toilet) {
		this.toilet = toilet;
	}
	public String getTakeout() {
		return takeout;
	}
	public void setTakeout(String takeout) {
		this.takeout = takeout;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	
		
}
