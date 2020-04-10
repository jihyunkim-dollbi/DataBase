package com.sist.dao;

/*

 * 이름        널? 유형            
--------- -- ------------- 
MNO          NUMBER        
RANK         NUMBER        
TITLE        VARCHAR2(500) 
SINGER       VARCHAR2(300) 
ALBUM        VARCHAR2(300) 
POSTER       VARCHAR2(300) 
IDCLIMENT    NUMBER        
STATE        CHAR(6)       
KEY          VARCHAR2(100) 
 */


public class MusicVO {

		private int mno;
		private int rank;
		private String title;
		private String singer;
		private String album;
		private String poster;
		private int idCliment;
		private String state;
		private String key;
		
		public int getMno() {
			return mno;
		}
		public void setMno(int mno) {
			this.mno = mno;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSinger() {
			return singer;
		}
		public void setSinger(String singer) {
			this.singer = singer;
		}
		public String getAlbum() {
			return album;
		}
		public void setAlbum(String album) {
			this.album = album;
		}
		public String getPoster() {
			return poster;
		}
		public void setPoster(String poster) {
			this.poster = poster;
		}
		public int getIdCliment() {
			return idCliment;
		}
		public void setIdCliment(int idCliment) {
			this.idCliment = idCliment;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	

		
		
	
}
