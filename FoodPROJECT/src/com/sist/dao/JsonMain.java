package com.sist.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//json 파서 연습!!!
/*
 *  [{"action_value":1,"count":6},{"action_value":2,"count":20},{"action_value":3,"count":100}] => 배열이므로  어레이로 받아야함@!
    count 값이 필요하다 good, soso, bad  ==>    6, 20 , 100 을 가져올 예정!!
 /*
	JSON PARSER!
	{} => OBJECT
	[{},{},{},{}...] => ARRAY
						
 */
/*
 * object! {}
 * Object=> {"count":9,"action_value":1}
 * 
 * array! [{},{},{},{}...] 
 * JSON=> [{"count":9,"action_value":1},{"count":31,"action_value":2},{"count":94,"action_value":3}]   
 */

public class JsonMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String json= "{\"datas\":[{\"rank\":1,\"title\":\"1987\"},{\"rank\":2,\"title\":\"신과함께-죄와 벌\"},{\"rank\":3,\"title\":\"쥬만지: 새로운 세계\"},{\"rank\":4,\"title\":\"코코\"},{\"rank\":5,\"title\":\"강철비\"},{\"rank\":6,\"title\":\"위대한 쇼맨\"},{\"rank\":7,\"title\":\"다운사이징\"},{\"rank\":8,\"title\":\"페르디난드\"},{\"rank\":9,\"title\":\"원더\"},{\"rank\":10,\"title\":\"쏘아올린 불꽃, 밑에서 볼까? 옆에서 볼까?\"}]}";
		
		
		
		JSONParser jp=new JSONParser();
		JSONObject movie=(JSONObject)jp.parse(json);
		
		System.out.println(movie.toJSONString());
		
		JSONArray arr=(JSONArray)movie.get("datas");
		
		System.out.println(arr.toJSONString());
		
		
		for(int i=0;i<arr.size();i++)
		{
			JSONObject obj=(JSONObject)arr.get(i); 
			long rank=(Long)obj.get("rank");
			String title=(String)obj.get("title");
			System.out.println(rank+" "+title);
		}
	}

}
